package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksidatabase {
    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/administrasi_mandiri";
        String USER = "root";
        String PASSWORD = "";

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
