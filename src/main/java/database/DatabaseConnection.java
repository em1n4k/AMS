package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection;

    static {
        try {
            // Чтение из переменной окружения
            String url = System.getenv("DB_URL");
            String user = System.getenv("DB_USER");
            String password = System.getenv("DB_PASSWORD");

            if (url == null || user == null || password == null) {
                throw new RuntimeException("Переменные окружения для базы данных не заданы!");
            }

            connection = DriverManager.getConnection(url, user, password);
            System.out.println("✔ Подключение успешно!");
        } catch (SQLException e) {
            System.err.println("Ошибка подключения к базе данных: " + e.getMessage());
        }
    }

    public static Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()) {
                throw new RuntimeException("Подключение к базе данных закрыто!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Соединение с базой данных закрыто.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
