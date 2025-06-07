package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class adminDAO {
    private Connection connection;

    public adminDAO(Connection connection) {
        this.connection = connection;
    }

    public boolean login(String username, String password) {
        try {
            String sql = "SELECT * FROM admin WHERE username=? AND password=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();

            return rs.next(); // login sukses jika ada data
        } catch (SQLException e) {
            return false;
        }
    }

    public boolean tambahAdmin(String username, String password) {
        try {
            String sql = "INSERT INTO admin (username, password) VALUES (?, ?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, password);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }

    public List<String> getAllAdminUsernames() {
        List<String> adminList = new ArrayList<>();
        try {
            String sql = "SELECT username FROM admin";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                adminList.add(rs.getString("username"));
            }
        } catch (SQLException e) {}
        return adminList;
    }
    
    public boolean hapusAdmin(String username) {
        try {
            String sql = "DELETE FROM admin WHERE username = ?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1, username);
            return pst.executeUpdate() > 0;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public List<String[]> getAllAdmins() {
        List<String[]> list = new ArrayList<>();
        try {
            String sql = "SELECT username, password FROM admin";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new String[]{
                    rs.getString("username"),
                    rs.getString("password")
                });
            }
        } catch (SQLException e) {}
        return list;
    }
}
