package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksidatabase {
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Koneksi ke database berhasil!");
                conn.close(); // Selalu tutup koneksi setelah digunakan
            }
        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/administrasi_mandiri";
        String USER = "root";
        String PASSWORD = "";

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
