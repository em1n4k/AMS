package dao;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseDAO<T> {

    protected abstract T mapResultSetToEntity(ResultSet resultSet) throws SQLException;

    protected List<T> executeQuery(String query, Object... parameters) {

        List<T> resultList = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement,parameters);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                resultList.add(mapResultSetToEntity(resultSet));
            }
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }

    protected int executeUpdate(String query, Object... parameters) {
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement, parameters);
            return statement.executeUpdate();
        }   catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
}