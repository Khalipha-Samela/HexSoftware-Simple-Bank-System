package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.InputStream;

public class DBConnection {
    private static Connection connection;

    // Get a singleton connection instance
    public static Connection getConnection() {
        if (connection == null || isConnectionInvalid()) {
            try (InputStream input = DBConnection.class.getClassLoader().getResourceAsStream("config.properties")) {
                if (input == null) {
                    throw new RuntimeException("Unable to find config.properties");
                }

                Properties prop = new Properties();
                prop.load(input);

                String url = prop.getProperty("db.url");
                String user = prop.getProperty("db.username");
                String password = prop.getProperty("db.password");

                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connected!");
            } catch (Exception e) {
                System.err.println("Failed to connect to the database.");
                e.printStackTrace();
            }
        }
        return connection;
    }

    // Check if the connection is invalid
    private static boolean isConnectionInvalid() {
        try {
            return connection == null || connection.isClosed() || !connection.isValid(2); // Timeout = 2 seconds
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    // Optionally close the connection when the app exits
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                System.err.println("Failed to close the database connection.");
                e.printStackTrace();
            }
        }
    }
}