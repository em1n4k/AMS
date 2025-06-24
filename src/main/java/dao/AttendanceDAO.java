package dao;

import database.DatabaseConnection;
import models.Attendance;
import models.AttendanceStatus;

import javax.xml.crypto.Data;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AttendanceDAO {

    private static final Logger logger = Logger.getLogger(AttendanceDAO.class.getName());

    // Getting student attendance status by ID
    public Optional<Attendance> getById(long id) {
        String query = "SELECT * FROM attendance WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Attendance attendance = extractAttendanceFromResultSet(resultSet);
                return Optional.of(attendance);
            }

        }   catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении посещаемости по ID = " + id, e);
        }
        return Optional.empty();
    }

    // Getting the entire student attendance status list
    public List<Attendance> getAll() {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Attendance attendance = extractAttendanceFromResultSet(resultSet);
                attendanceList.add(attendance);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении списка посещаемости", e);
        }
        return attendanceList;
    }

    // Adding a new attendance status record to the DB
    public void add(Attendance attendance) {
        String query = "INSERT INTO attendance (student_id, teacher_id, subject_id, date, status, comment) VALUES (?,?,?,?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, attendance.getStudentId());
            statement.setLong(2, attendance.getTeacherId());
            statement.setLong(3, attendance.getSubjectId());
            statement.setDate(4, Date.valueOf(attendance.getDate()));
            statement.setString(5, attendance.getStatus().name());
            statement.setString(6, attendance.getComment());

            statement.executeUpdate();
            logger.info("Добавлена новая запись посещаемости");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении записи посещаемости", e);
        }
    }

    // Updating the attendance status record
    public void update(Attendance attendance) {
        String query = "UPdATE attendance SET student_id = ?, teacher_id = ?, subject_id = ?, date = ?, status = ?, comment = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, attendance.getStudentId());
            statement.setLong(2, attendance.getTeacherId());
            statement.setLong(3, attendance.getSubjectId());
            statement.setDate(4, Date.valueOf(attendance.getDate()));
            statement.setString(5, attendance.getStatus().name());
            statement.setString(6, attendance.getComment());
            statement.setLong(7, attendance.getId());

            statement.executeUpdate();
            logger.info("Обновлена запись посещаемости по ID = " + attendance.getId());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении посещаемости ID = " + attendance.getId(), e);
        }
    }

    // Delete the attendance status record
    public void delete(long id) {
        String query = "DELETE FROM attendance WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            statement.executeUpdate();
            logger.info("Удалена запись посещаемости ID = " + id);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении посещаеомсти ID = " + id, e);
        }
    }

    /**
    * TODO: A method for getting all student visits for a subject.
     * Currently not connected because there is no Subject table in the project.
    *  */
    public List<Attendance> getAttendanceByStudentAndSubject(long studentId, long subjectId) {
        List<Attendance> attendanceList = new ArrayList<>();
        String query = "SELECT * FROM attendance WHERE student_id = ? AND subject_id = ? ORDER BY date DESC";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, studentId);
            statement.setLong(2, subjectId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Attendance attendance = extractAttendanceFromResultSet(resultSet);
                attendanceList.add(attendance);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении посещаемости студента ID = " + studentId, e);
        }
        return attendanceList;
    }

    private Attendance extractAttendanceFromResultSet(ResultSet resultSet) throws SQLException {
        return new Attendance(
                resultSet.getLong("id"),
                resultSet.getLong("student_id"),
                resultSet.getLong("teacher_id"),
                resultSet.getLong("subject_id"),
                resultSet.getDate("date").toLocalDate(),
                AttendanceStatus.valueOfStatus(resultSet.getString("status")),
                resultSet.getString("comment")
        );
    }
}