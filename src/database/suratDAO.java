package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class suratDAO {
    private Connection connection;

    public suratDAO(Connection connection) {
        this.connection = connection;
    }
    
    public void insertSurat(String nomorSurat, String nama, String nik, String ttl, String alamat, String jenisSurat) throws SQLException {
    String statusSurat = "Menunggu"; // default
    String sql = "INSERT INTO surat (nomor_surat, nama, nik, tempat_tanggal_lahir, alamat, jenis_surat, statusSurat) VALUES (?, ?, ?, ?, ?, ?, ?)";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, nomorSurat);
        stmt.setString(2, nama);
        stmt.setString(3, nik);
        stmt.setString(4, ttl);
        stmt.setString(5, alamat);
        stmt.setString(6, jenisSurat);
        stmt.setString(7, statusSurat);
        stmt.executeUpdate();
    }
}
    
    // ✅ Update status surat (oleh admin)
    public void updateStatusSurat(String nomorSurat, String statusBaru) throws SQLException {
        String sql = "UPDATE surat SET statusSurat = ? WHERE nomor_surat = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, statusBaru);
            stmt.setString(2, nomorSurat);
            stmt.executeUpdate();
        }
    }

    // Tambahkan method baru di suratDAO
    public List<SuratDataUmum> getSuratPengajuan() throws SQLException {
        List<SuratDataUmum> list = new ArrayList<>();
        String query = "SELECT * FROM surat WHERE statusSurat IN ('Menunggu', 'Disetujui', 'Ditolak')";
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);

        while (rs.next()) {
            SuratDataUmum surat = new SuratDataUmum(
                rs.getInt("id_surat"),
                rs.getString("nomor_surat"),
                rs.getString("jenis_surat"),
                rs.getString("statusSurat")
            );
            list.add(surat);
        }
        return list;
    }


    // ✅ Ambil semua nomor surat berdasarkan status tertentu (misalnya: "Menunggu")
    public List<String> getNomorSuratDenganStatus(String status) throws SQLException {
        List<String> list = new ArrayList<>();
        String sql = "SELECT nomor_surat FROM surat WHERE statusSurat = ?";
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
                    rs.getString("statusSurat")
                );
                list.add(surat);
            }
        }
        return list;
    }
    
        // ✅ Ambil semua data surat berdasarkan status tertentu
    public List<SuratDataUmum> getSuratByStatus(String status) throws SQLException {
        List<SuratDataUmum> list = new ArrayList<>();
        String sql = "SELECT * FROM surat WHERE statusSurat = ? ORDER BY id_surat DESC";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    SuratDataUmum surat = new SuratDataUmum(
                        rs.getInt("id_surat"),
                        rs.getString("nomor_surat"),
                        rs.getString("nama"),
                        rs.getString("nik"),
                        rs.getString("tempat_tanggal_lahir"),
                        rs.getString("alamat"),
                        rs.getString("jenis_surat"),
                        rs.getString("statusSurat")
                    );
                    list.add(surat);
                }
            }
        }
    return list;
}
    public List<SuratDataUmum> getSuratRiwayat() throws SQLException {
    List<SuratDataUmum> list = new ArrayList<>();
    String sql = "SELECT * FROM surat WHERE statusSurat IN ('Disetujui', 'Ditolak')";
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
                rs.getString("statusSurat")
            );
            list.add(surat);
        }
    }
    return list;
}
    
    public List<SuratDataUmum> getSuratBelumDiverifikasi() throws SQLException {
    List<SuratDataUmum> list = new ArrayList<>();
    String query = "SELECT * FROM surat WHERE statusSurat = 'menunggu'";
    Statement stmt = connection.createStatement();
    ResultSet rs = stmt.executeQuery(query);

    while (rs.next()) {
        SuratDataUmum surat = new SuratDataUmum(
            rs.getInt("id_surat"),
            rs.getString("nomor_surat"),
            rs.getString("jenis_surat"),
            rs.getString("statusSurat")
        );
        list.add(surat);
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
    
    public SuratDataUmum getSuratByNomor(String nomorSurat) throws SQLException {
    String sql = "SELECT * FROM surat WHERE nomor_surat = ?";
    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        stmt.setString(1, nomorSurat);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return new SuratDataUmum(
                    rs.getInt("id_surat"),
                    rs.getString("nomor_surat"),
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("alamat"),
                    rs.getString("jenis_surat"),
                    rs.getString("statusSurat")
                );
            }
        }
    }
    return null;
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
        private String statusSurat;

        public SuratDataUmum(int idSurat, String nomorSurat, String nama, String nik,
                             String tempat_tanggal_lahir, String alamat, String jenisSurat, String statusSurat) {
            this.idSurat = idSurat;
            this.nomorSurat = nomorSurat;
            this.nama = nama;
            this.nik = nik;
            this.tempat_tanggal_lahir = tempat_tanggal_lahir;
            this.alamat = alamat;
            this.jenisSurat = jenisSurat;
            this.statusSurat = statusSurat;
        }
        
        public SuratDataUmum(int idSurat, String nomorSurat, String jenisSurat, String statusSurat) {
            this.idSurat = idSurat;
            this.nomorSurat = nomorSurat;
            this.jenisSurat = jenisSurat;
            this.statusSurat = statusSurat;
        }

        
        // Getter methods
        public int getIdSurat() { return idSurat; }
        public String getNomorSurat() { return nomorSurat; }
        public String getNama() { return nama; }
        public String getNik() { return nik; }
        public String gettempat_tanggal_lahir() { return tempat_tanggal_lahir; }
        public String getAlamat() { return alamat; }
        public String getJenisSurat() { return jenisSurat; }
        public String getstatusSurat() { return statusSurat; }
    }
}
