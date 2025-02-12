package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class DAO<T> {

    // Метод для добавления объекта в БД
    public abstract void add(T entity) throws SQLException;

    // Метод для обновления объекта в БД
    public abstract void update(T entity) throws SQLException;

    // Метод для удаления объекта из БД
    public abstract void delete(T entity) throws SQLException;

    // Метод для получения объекта по ID
    public abstract void getById(int id) throws SQLException;

    // Метод для получения всех объектов из БД
    public abstract List<T> getAll() throws SQLException;

    // Утилитарный (утилита/вспомогательный) метод для закрытия ресурсов (закрытие Connection Pool)
    protected void closeResources(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Общий метод для выполнения запросов с параметрами
    protected int executeUpdate(String query, Object... parameters) throws SQLException {
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)) {

            setParameters(statement,parameters);
            return statement.executeUpdate();
        }
    }

    // Общий метод для выполнения SELECT-запросов
    protected ResultSet executeQuery(String query, Object... parameters) throws SQLException {
        Connection connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(query);

        setParameters(statement, parameters);
        return statement.executeQuery();
    }

    // Утилитарный метод для установки параметров в PreparedStatement
    private void setParameters(PreparedStatement statement, Object... parameters) throws SQLException {
        for (int i = 0; i < parameters.length; i++) {
            statement.setObject(i + 1, parameters[i]);
        }
    }
}
