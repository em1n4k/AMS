package dao;

import database.DatabaseConnection;
import models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {

    private static final Logger logger = Logger.getLogger(StudentDAO.class.getName());

    // Get student data by ID
    public Optional<Student> getById(long id) {
        String query = "SELECT * FROM students WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Student student = extractStudentFromResultSet(resultSet);
                return Optional.of(student);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении данных студента с ID = " + id, e);
        }

        return Optional.empty();
    }

    // Get all students data
    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Student student = extractStudentFromResultSet(resultSet);
                studentList.add(student);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении списка данных студентов", e);
        }
        return studentList;
    }

    // Add student data
    public void add(Student student) {
        String query = "INSERT INTO students (first_name, last_name, patronymic, age, email, faculty_number, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getPatronymic());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getFacultyNumber());
            statement.setString(7, student.getPhoneNumber());

            statement.executeUpdate();
            logger.info("Добавлена информация о новом студенте");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении данных студента", e);
        }
    }

    // Updating certain student data
    public void update(Student student) {
        String query = "UPDATE students SET first_name = ?, last_name = ?, patronymic = ?, age = ?, email = ?, faculty_number = ?, phone_number = ? WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getPatronymic());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getFacultyNumber());
            statement.setString(7, student.getPhoneNumber());

            statement.executeUpdate();
            logger.info("Обновлены данные студента с ID = " + student.getId());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении данных студента", e);
        }
    }

    public void delete(long id) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            statement.executeUpdate();
            logger.info("Удалены данные студента с ID = " + id);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении данных студента", e);
        }
    }

    private Student extractStudentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("patronymic"),
                resultSet.getInt("age"),
                resultSet.getString("email"),
                resultSet.getString("faculty_number"),
                resultSet.getString("phone_number")
        );
    }
}