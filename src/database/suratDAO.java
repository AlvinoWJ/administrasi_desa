package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class suratDAO {
    private Connection connection;

    public suratDAO(Connection connection) {
        this.connection = connection;
    }

    // ✅ Update status surat (oleh admin)
    public void updateStatusSurat(String nomorSurat, String statusBaru) throws SQLException {
        String sql = "UPDATE surat SET status = ? WHERE nomor_surat = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, statusBaru);
            stmt.setString(2, nomorSurat);
            stmt.executeUpdate();
        }
    }

    // ✅ Ambil status surat berdasarkan nomor
    public String getStatusSurat(String nomorSurat) throws SQLException {
        String sql = "SELECT status FROM surat WHERE nomor_surat = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomorSurat);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return rs.getString("status");
            }
        }
        return null;
    }

    // ✅ Ambil semua nomor surat berdasarkan status tertentu (misalnya: "Menunggu")
    public List<String> getNomorSuratDenganStatus(String status) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT nomor_surat FROM surat WHERE status = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("nomor_surat"));
                }
            }
        }
        return list;
    }

    // ✅ Ambil semua surat (data umum)
    public List<SuratDataUmum> getAllSurat() throws SQLException {
        List<SuratDataUmum> list = new ArrayList<>();
        String sql = "SELECT * FROM surat ORDER BY id_surat DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                SuratDataUmum surat = new SuratDataUmum(
                    rs.getInt("id_surat"),
                    rs.getString("nomor_surat"),
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("alamat"),
                    rs.getString("jenis_surat"),
                    rs.getString("status")
                );
                list.add(surat);
            }
        }
        return list;
    }

    // ✅ Cek apakah surat dengan nomor tertentu ada
    public boolean suratExists(String nomorSurat) throws SQLException {
        String sql = "SELECT 1 FROM surat WHERE nomor_surat = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomorSurat);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    // Inner class opsional sebagai DTO sederhana
    public static class SuratDataUmum {
        private int idSurat;
        private String nomorSurat;
        private String nama;
        private String nik;
        private String tempat_tanggal_lahir;
        private String alamat;
        private String jenisSurat;
        private String status;

        public SuratDataUmum(int idSurat, String nomorSurat, String nama, String nik,
                             String tempat_tanggal_lahir, String alamat, String jenisSurat, String status) {
            this.idSurat = idSurat;
            this.nomorSurat = nomorSurat;
            this.nama = nama;
            this.nik = nik;
            this.tempat_tanggal_lahir = tempat_tanggal_lahir;
            this.alamat = alamat;
            this.jenisSurat = jenisSurat;
            this.status = status;
        }

        // Getter methods
        public int getIdSurat() { return idSurat; }
        public String getNomorSurat() { return nomorSurat; }
        public String getNama() { return nama; }
        public String getNik() { return nik; }
        public String gettempat_tanggal_lahir() { return tempat_tanggal_lahir; }
        public String getAlamat() { return alamat; }
        public String getJenisSurat() { return jenisSurat; }
        public String getStatus() { return status; }
    }
}
