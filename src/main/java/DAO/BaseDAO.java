package DAO;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseDAO<T> {

    protected abstract T mapResultToEntity(ResultSet resultSet) throws SQLException;
    protected abstract String getTableName();
    protected abstract String getInsertQuery();
    protected abstract void setInsertParameters(PreparedStatement preparedStatement, T entity) throws SQLException;

    // Get object by ID
    public Optional<T> getById(long id) throws SQLException {
        String query = "SELECT * FROM " + getTableName() + " WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next()) {
                return Optional.of(mapResultToEntity(rs));
            }
        }
        return Optional.empty();
    }

    // Get all objects
    public List<T> getAll() throws SQLException {
        List<T> list = new ArrayList<>();
        String query = "SELECT * FROM " + getTableName();
        try(Connection connection = DatabaseConnection.getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(query)) {
            while(rs.next()) {
                list.add(mapResultToEntity(rs));
            }
        }
        return list;
    }

    // Save object
    public void save(T entity) throws SQLException {
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(getInsertQuery())) {
            setInsertParameters(statement, entity);
            statement.executeUpdate();
        }
    }

    // Update object (Optionally)
    public abstract void update (T entity) throws SQLException;

    // Delete object by ID
    public void deleteById(long id) throws SQLException {
        String query = "DELETE FROM " + getTableName() + " WHERE id = ?";
        try(Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

}
