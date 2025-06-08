package database;

import model.suratdomisili;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class suratdomisiliDAO {
    private Connection connection;

    public suratdomisiliDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert data ke dalam dua tabel: surat dan surat_domisili
    public void insert(suratdomisili surat) throws SQLException {
    String insertSuratSQL = "INSERT INTO surat (nomor_surat, nama, nik, tempat_tanggal_lahir, alamat, jenis_surat, statusSurat) VALUES (?, ?, ?, ?, ?, ?, ?)";
    String insertDomisiliSQL = "INSERT INTO surat_domisili (id_surat, jenis_kelamin, agama, pekerjaan) VALUES (?, ?, ?, ?)";

    boolean defaultAutoCommit = connection.getAutoCommit();
    connection.setAutoCommit(false);

    try (
        PreparedStatement stmt1 = connection.prepareStatement(insertSuratSQL, Statement.RETURN_GENERATED_KEYS);
        PreparedStatement stmt3 = connection.prepareStatement(insertDomisiliSQL)
    ) {
        //  Buat nomor surat unik
        String kode = switch (surat.getJenisSurat()) {
            case "Surat Keterangan Kematian" -> "suketkematian";
            case "Surat Keterangan Domisili" -> "domisili";
            default -> "lainnya";
        };
        String nomorSurat = "XXI-" + System.currentTimeMillis() + "/" + kode + "/" + java.time.Year.now().getValue();

        // Isi data ke tabel surat
        stmt1.setString(1, nomorSurat);
        stmt1.setString(2, surat.getNama());
        stmt1.setString(3, surat.getNik());
        stmt1.setString(4, surat.getTempatTanggalLahir());
        stmt1.setString(5, surat.getAlamat());
        stmt1.setString(6, surat.getJenisSurat());
        stmt1.setString(7, "menunggu"); 

        stmt1.executeUpdate();

        // 🔁 Ambil ID yang baru di-generate
        ResultSet generatedKeys = stmt1.getGeneratedKeys();
        if (!generatedKeys.next()) throw new SQLException("Gagal mengambil ID surat.");
        int idSurat = generatedKeys.getInt(1);

        // Insert ke surat_domisili
        stmt3.setInt(1, idSurat);
        stmt3.setString(2, surat.getJenisKelamin());
        stmt3.setString(3, surat.getAgama());
        stmt3.setString(4, surat.getPekerjaan());

        stmt3.executeUpdate();

        
        connection.commit();

    } catch (SQLException e) {
        if (connection != null) connection.rollback();
        throw e;
    } finally {
        connection.setAutoCommit(defaultAutoCommit); // Kembalikan ke nilai awal
    }
}



    // Ambil data surat berdasarkan nomor_surat
    public suratdomisili getByNomorSurat(String nomorSurat) throws SQLException {
        String sql = "SELECT s.*, d.jenis_kelamin, d.agama, d.pekerjaan " +
                     "FROM surat s JOIN surat_domisili d ON s.id_surat = d.id_surat " +
                     "WHERE s.nomor_surat = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomorSurat);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    suratdomisili surat = new suratdomisili(
                        rs.getString("nama"),
                        rs.getString("nik"),
                        rs.getString("tempat_tanggal_lahir"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("agama"),
                        rs.getString("pekerjaan"),
                        rs.getString("alamat")
                    );
                    surat.setNomorSurat(rs.getString("nomor_surat"));
                    return surat;
                }
            }
        }
        return null;
    }

    // Hapus surat berdasarkan nomor_surat
    public void delete(String nomorSurat) throws SQLException {
        String sqlGetId = "SELECT id_surat FROM surat WHERE nomor_surat = ?";
        String sqlDeleteDomisili = "DELETE FROM surat_domisili WHERE id_surat = ?";
        String sqlDeleteSurat = "DELETE FROM surat WHERE id_surat = ?";

        try (
            PreparedStatement stmtGetId = connection.prepareStatement(sqlGetId);
            PreparedStatement stmt1 = connection.prepareStatement(sqlDeleteDomisili);
            PreparedStatement stmt2 = connection.prepareStatement(sqlDeleteSurat)
        ) {
            connection.setAutoCommit(false);

            stmtGetId.setString(1, nomorSurat);
            int idSurat = -1;
            try (ResultSet rs = stmtGetId.executeQuery()) {
                if (rs.next()) {
                    idSurat = rs.getInt("id_surat");
                } else {
                    throw new SQLException("Data tidak ditemukan.");
                }
            }

            stmt1.setInt(1, idSurat);
            stmt1.executeUpdate();

            stmt2.setInt(1, idSurat);
            stmt2.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            if (connection != null) connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Ambil semua data surat domisili
    public List<suratdomisili> getAll() throws SQLException {
        List<suratdomisili> list = new ArrayList<>();
        String sql = "SELECT s.*, d.jenis_kelamin, d.agama, d.pekerjaan " +
                     "FROM surat s JOIN surat_domisili d ON s.id_surat = d.id_surat " +
                     "WHERE s.jenis_surat = 'Surat Keterangan Domisili'";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suratdomisili surat = new suratdomisili(
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("agama"),
                    rs.getString("pekerjaan"),
                    rs.getString("alamat")
                );
                surat.setNomorSurat(rs.getString("nomor_surat"));
                list.add(surat);
            }
        }
        return list;
    }
}
