package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static Connection connection;

    private DatabaseConnection() {

    }

    public static Connection getInstance() throws SQLException {
        if(connection == null) {
            String url = "jdbc:mysql://localhost:3306/pao";
            String userName = "root";
            String password = "Parola123!";

            connection = DriverManager.getConnection(url, userName, password);
        }
        return connection;
    }
}