package dao;

import database.DatabaseConnection;
import models.Admin;
import util.PasswordUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO {

    private static final Logger logger = Logger.getLogger(AdminDAO.class.getName());

    // Get Admin data by ID
    public Optional<Admin> getById(long id) {
        String query = "SELECT * FROM admins WHERE id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1,id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                Admin admin = extractAdminFromResultSet(resultSet);
                return Optional.of(admin);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении данных администратора с ID = " + id, e);
        }

        return Optional.empty();
    }

    // Get all Admins data
    public List<Admin> getAll() {
        List<Admin> adminList = new ArrayList<>();
        String query = "SELECT * FROM admins";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Admin admin = extractAdminFromResultSet(resultSet);
                adminList.add(admin);
            }

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при получении списка данных администраторов", e);
        }
        return adminList;
    }

    // Add Admin data (with password hashing)
    public void add(Admin admin) {
        String query = "INSERT INTO admins (username, password) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1,admin.getUsername());
            String hashedPassword = PasswordUtil.hashPassword(admin.getPassword());
            statement.setString(2, hashedPassword);

            statement.executeUpdate();
            logger.info("Добавлена информация о новом администраторе");

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при добавлении данных администратора", e);
        }
    }

    // Updating certain Admin data
    public void update(Admin admin) {
        String query = "UPDATE admins SET username = ?, password WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, admin.getUsername());
            String hashedPassword = PasswordUtil.hashPassword(admin.getPassword());
            statement.setString(2, hashedPassword);
            statement.setLong(3, admin.getId());

            statement.executeUpdate();
            logger.info("Обновлены данные администратора с ID = " + admin.getId());

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при обновлении данных администратора", e);
        }
    }

    public void delete(long id) {
        String query = "DELETE FROM admins WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setLong(1, id);
            statement.executeUpdate();
            logger.info("Удалены данные администратора с ID = " + id);

        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при удалении данных администратора", e);
        }
    }

    // Checking the administrator's login
    public boolean checkLogin(String username, String plainPassword) {
        String query = "SELECT password FROM admins WHERE username = ?";

        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String storedHash = resultSet.getString("password");
                return PasswordUtil.checkPassword(plainPassword, storedHash);
            }

        }   catch (SQLException e) {
            logger.log(Level.SEVERE, "Ошибка при проверке логина администратора", e);
        }
        return false;
    }

    // Method for extracting the administrator from the ResultSet
    private Admin extractAdminFromResultSet(ResultSet resultSet) throws SQLException {
        Admin admin = new Admin();
        admin.setId(resultSet.getLong("id"));
        admin.setUsername(resultSet.getString("username"));
        admin.setPassword(resultSet.getString("password"));
        return admin;
    }
}