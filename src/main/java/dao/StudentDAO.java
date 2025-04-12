package dao;

import database.DatabaseConnection;
import models.AttendanceStatus;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StudentDAO {

    private final Map<Long, Student> studentCache = new ConcurrentHashMap<>();

    // Инициализация кэша при создании DAO
    public StudentDAO() {
        try {
            loadAllStudents();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при инициализации кэша студентов", e);
        }
    }

    // Загрузка всех студентов из БД в кэш
    public void loadAllStudents() throws SQLException {
        String query = "SELECT id, first_name, last_name, email, faculty_number, attendance FROM student";
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            studentCache.clear();
            while (resultSet.next()) {
                Student student = mapResultSetToStudent(resultSet);
                studentCache.put(student.getId(), student);
            }
        }
    }

    // Добавление нового студента
    public void addStudent(Student student) throws SQLException {
        String query = "INSERT INTO student (first_name, last_name, email, faculty_number, attendance) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getFacultyNumber());
            statement.setString(5, student.getAttendance().getDisplayName());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Создание студента не удалось, ни одна запись не была изменена.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    student.setId(generatedKeys.getLong(1));
                    studentCache.put(student.getId(), student);
                } else {
                    throw new SQLException("Создание студента не удалось, ID не был получен.");
                }
            }
        }
    }

    // Получение студента по ID
    public Student getStudentById(long id) throws SQLException {
        if (studentCache.containsKey(id)) {
            return studentCache.get(id);
        }

        String query = "SELECT id, first_name, last_name, email, faculty_number, attendance FROM student WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Student student = mapResultSetToStudent(resultSet);
                    studentCache.put(id, student);
                    return student;
                }
            }
        }
        return null;
    }

    // Обновление данных студента
    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE student SET first_name = ?, last_name = ?, email = ?, faculty_number = ?, attendance = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getFacultyNumber());
            statement.setString(5, student.getAttendance().getDisplayName());
            statement.setLong(6, student.getId());

            statement.executeUpdate();
            studentCache.put(student.getId(), student);
        }
    }

    // Удаление студента
    public boolean deleteStudent(long id) throws SQLException {
        String query = "DELETE FROM student WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            int rowsAffected = statement.executeUpdate();
            if (rowsAffected > 0) {
                studentCache.remove(id);
                return true;
            }
        }
        return false;
    }

    // Получение всех студентов
    public List<Student> getAllStudents() throws SQLException {
        if (studentCache.isEmpty()) {
            loadAllStudents();
        }
        return new ArrayList<>(studentCache.values());
    }

    // Маппинг ResultSet в объект Student
    private Student mapResultSetToStudent(ResultSet resultSet) throws SQLException {
        // Конвертация строки из БД в AttendanceStatus
        String attendanceStatus = resultSet.getString("attendance");
        AttendanceStatus attendance = AttendanceStatus.fromDisplayName(attendanceStatus);

        return new Student(
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("faculty_number"),
                attendance,
                resultSet.getString("grade"),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getString("phone_number"),
                resultSet.getLong("id")
                );
    }
}