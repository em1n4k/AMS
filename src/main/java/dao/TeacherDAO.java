package dao;

import database.DatabaseConnection;
import models.Teacher;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeacherDAO {

    private static final Logger logger = Logger.getLogger(TeacherDAO.class.getName());

    // Get Teacher data by ID
    public Optional<Teacher> getById(long id) {
        String query = "SELECT * FROM teachers WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Teacher teacher = extractTeacherFromResultSet(resultSet);
                return Optional.of(teacher);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении данных преподавателя по ID = " + id, e);
        }

        return Optional.empty();
    }

    // Get all Teachers data
    public List<Teacher> getAll() {
        List<Teacher> teacherList = new ArrayList<>();
        String query = "SELECT * FROM teachers";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Teacher teacher = extractTeacherFromResultSet(resultSet);
                teacherList.add(teacher);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении списка данных преподавателей", e);
        }
        return teacherList;
    }

    // Add Teacher data
    public void add(Teacher teacher) {
        String query = "INSERT INTO teachers (first_name, last_name, patronymic, age, email, faculty_number, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getPatronymic());
            statement.setString(4, teacher.getEmail());
            statement.setString(5, teacher.getPhoneNumber());
            statement.setString(6, teacher.getSubject());

            statement.executeUpdate();
            logger.info("Добавлена информация о новом преподавателе");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении данных преподавателя", e);
        }
    }

    // Updating certain Teacher data
    public void update(Teacher teacher) {
        String query = "UPDATE teachers SET first_name = ?, last_name = ?, patronymic = ?, email = ?, phone_number = ?, subject = ? WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, teacher.getFirstName());
            statement.setString(2, teacher.getLastName());
            statement.setString(3, teacher.getPatronymic());
            statement.setString(4, teacher.getEmail());
            statement.setString(5, teacher.getPhoneNumber());
            statement.setString(6, teacher.getSubject());

            statement.executeUpdate();
            logger.info("Обновлены данные преподавателя с ID = " + teacher.getId());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении данных преподавателя", e);
        }
    }

    public void delete(long id) {
        String query = "DELETE FROM teachers WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            statement.executeUpdate();
            logger.info("Удалены данные преподавателя с ID = " + id);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении данных преподавателя", e);
        }
    }

    private Teacher extractTeacherFromResultSet(ResultSet resultSet) throws SQLException {
        return new Teacher(
                resultSet.getLong("id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name"),
                resultSet.getString("patronymic"),
                resultSet.getDate("birth_date").toLocalDate(),
                resultSet.getString("email"),
                resultSet.getString("phone_number"),
                resultSet.getString("subject"),
                resultSet.getString("password")
        );
    }
}