package dao;

import database.DatabaseConnection;
import models.User;
import models.Role;

import java.sql.*;
import java.util.Optional;

public class UserDAO {

    // Find user by email
    public Optional<User> findByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(extractUser(resultSet));
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    // Add new user
    public void addUser(User user) {
        String sql = "INSERT INTO users (email, password_hash, role) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, CallableStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1,user.getEmail());
            statement.setString(2, user.getPasswordHash());
            statement.setString(3, user.getRole().name());
            statement.executeUpdate();

            ResultSet keys = statement.getGeneratedKeys();
            if (keys.next()) {
                user.setId(keys.getLong(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Extract user from ResultSet
    private User extractUser (ResultSet resultSet) throws SQLException {
        return new User(
                resultSet.getLong("id"),
                resultSet.getString("email"),
                resultSet.getString("password_hash"),
                Role.valueOf(resultSet.getString("role"))
        );
    }
}
