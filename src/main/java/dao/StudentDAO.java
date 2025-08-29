package dao;

import database.DatabaseConnection;
import models.Student;
import org.mindrot.jbcrypt.BCrypt;

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
            logger.log(Level.SEVERE, "Error when receiving student data with ID = " + id, e);
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
            logger.log(Level.SEVERE, "Error when getting the list of student data", e);
        }
        return studentList;
    }

    // Add student data
    public void add(Student student) {
        String query = "INSERT INTO students (first_name, last_name, patronymic, birth_date, email, faculty_number, password) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getPatronymic());
            statement.setDate(4, Date.valueOf(student.getBirthDate()));
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getFacultyNumber());
            statement.setString(7, hashPassword(student.getPassword()));

            statement.executeUpdate();
            logger.info("Added information about a new student");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when adding student data", e);
        }
    }

    // Updating certain student data
    public void update(Student student) {
        String query = "UPDATE students SET first_name = ?, last_name = ?, patronymic = ?, birth_date = ?, email = ?, faculty_number = ?, phone_number = ?, password = ? WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setString(3, student.getPatronymic());
            statement.setDate(4, Date.valueOf(student.getBirthDate()));
            statement.setString(5, student.getEmail());
            statement.setString(6, student.getFacultyNumber());

            // Phone number can be null
            if (student.getPhoneNumber() != null && !student.getPhoneNumber().isEmpty()) {
                statement.setString(7, student.getPhoneNumber());
            } else {
                statement.setNull(7, Types.VARCHAR);
            }
            statement.setString(8, hashPassword(student.getPassword()));
            statement.setLong(9, student.getId());

            statement.executeUpdate();
            logger.info("The student's ID data has been updated = " + student.getId());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating student data", e);
        }
    }

    // Deleting students data
    public void delete(long id) {
        String query = "DELETE FROM students WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            statement.executeUpdate();
            logger.info("Student's data with ID has been deleted = " + id);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error when deleting student data", e);
        }
    }

    // Password updating method
    public void updatePassword(long id, String newPassword) {
        String query = "UPDATE students SET password = ? WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, hashPassword(newPassword));
            statement.setLong(2, id);

            statement.executeUpdate();
            logger.info ("Password updated for student with ID = " + id);
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Error updating password", e);
        }
    }

    private Student extractStudentFromResultSet(ResultSet resultSet) throws SQLException {
        return new Student(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("patronymic"),
                resultSet.getDate("birth_date").toLocalDate(),
                resultSet.getString("email"),
                resultSet.getString("faculty_number"),
                resultSet.getString("phone_number"),
                resultSet.getString("password")
        );
    }

    private String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
}