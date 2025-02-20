package dao;

import database.DatabaseConnection;
import models.Student;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class StudentDAO {

    private final Map<Long, Student> studentCache = new HashMap<>();

    // Importing all students into cache at startup
    public void loadAllStudents() throws SQLException {
        String query = "SELECT * FROM student";
        try (Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query)) {

            studentCache.clear(); // Cleaning expired cache
            while (resultSet.next()) {
                Student student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("faculty_number"),
                        resultSet.getString("attendance")
                );
                studentCache.put(student.getId(), student);
            }
        }
    }

    // Getting a student from the cache (or from the database if the required student is not in the cache)
    public Student getStudentById(long id) throws SQLException {
        if (studentCache.containsKey(id)) {
            return studentCache.get(id);
        }

        String query = "SELECT * FROM student WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Student student = new Student(
                        resultSet.getLong("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("faculty_number"),
                        resultSet.getString("attendance")
                );
                studentCache.put(id, student); // Adding to the cache after query execution
                return student;
            }
        }
        return null;
    }

    // Updating student in database and cache
    public void updateStudent(Student student) throws SQLException {
        String query = "UPDATE student SET first_name = ?, last_name = ?, email = ?, faculty_number = ?, attendance = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getEmail());
            statement.setString(4, student.getFacultyNumber());
            statement.setString(5, student.getAttendance());
            statement.setLong(6, student.getId());

            statement.executeUpdate(); // Исправлено, добавлен вызов executeUpdate()
            studentCache.put(student.getId(), student); // Обновляем кэш
        }
    }
}
