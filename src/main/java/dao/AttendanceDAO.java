package dao;

import database.DatabaseConnection;
import models.Attendance;
import models.AttendanceStatus;
import util.CacheConfig;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttendanceDAO {

    private static final Logger logger = Logger.getLogger(AttendanceDAO.class.getName());
    private final Map<Long, List<Attendance>> attendanceCache = new HashMap<>();
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public AttendanceDAO() {
        // Процесс запуска фоновой задачи для очистки кэша, используя настроенный интервал из конфигурационного файла cache-config.properties
        int interval = CacheConfig.getCacheCleanUpInterval();
        logger.info("Инициализация AttendanceDAO. Интервал очистки кэша: " + interval + " минут.");
        startCacheCleaner(interval);
    }

    public List<Attendance> getAttendanceByStudentAndSubject(long studentId, long subjectId) {
        logger.info("Получение посещаемости студента с ID = " + studentId + ", предмет с ID = " + subjectId);

        if (attendanceCache.containsKey(studentId)) {
            return attendanceCache.get(studentId);
        }

        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT id, student_id, teacher_id, subject_id, date, status, comment FROM attendance WHERE student_id = ? AND subject_id = ? ORDER BY date DESC";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, studentId);
            statement.setLong(2, subjectId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Attendance attendance = new Attendance (
                        resultSet.getLong("id"),
                        resultSet.getLong("student_id"),
                        resultSet.getLong("teacher_id"),
                        resultSet.getLong("subject_id"),
                        resultSet.getDate("date").toLocalDate(),
                        AttendanceStatus.valueOfStatus(resultSet.getString("status")),
                        resultSet.getString("comment")
                );
                attendanceList.add(attendance);
            }

            if (attendanceList.isEmpty()) {
                logger.warning("Записи о посещаемости не найдены для студента с ID = " + studentId);
            } else {
                attendanceCache.put(studentId, attendanceList);
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при получении посещаемости: " + e.getMessage());
        }
        return attendanceList;
    }

    public void updateAttendance(Attendance attendance) throws SQLException {
        logger.info("Обновление записи о посещаемости ID = " + attendance.getId());

        String query = "UPDATE attendance SET status = ?, comment = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, attendance.getStatus().name());
            statement.setString(2, attendance.getComment());
            statement.setLong(3, attendance.getId());
            statement.executeUpdate();

            attendanceCache.compute(attendance.getStudentId(), (key, cachedAttendance) ->  {
               if (cachedAttendance == null) {
                   cachedAttendance = new ArrayList<>();
               }
               cachedAttendance.replaceAll(a -> a.getId() == attendance.getId() ? attendance : a);
               return cachedAttendance;
            });

            logger.fine("Запись посещаемости обновлена успешно!");
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Ошибка при обновлении посещаемости с ID = " + attendance.getId(), e);
        }

    }

    private void startCacheCleaner(int interval) {
        scheduler.scheduleAtFixedRate(this::clearCache, interval, interval, TimeUnit.MINUTES);
        logger.info("Запущен процесс очистки кэша каждые " + interval + "минут");
    }

    private void clearCache() {
    logger.info("Очистка кэша посещаемости...");
    attendanceCache.clear();
    }

}


