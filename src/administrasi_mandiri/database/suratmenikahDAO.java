package administrasi_mandiri.database;

import administrasi_mandiri.models.suratmenikah;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class suratmenikahDAO {
    private Connection connection;

    public suratmenikahDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(suratmenikah surat, int idAdmin) throws SQLException {
        String insertSuratSQL = "INSERT INTO surat (nama, nik, tempat_tanggal_lahir, alamat, jenis_surat, tanggal_buat, statusSurat, nomor_surat, id_admin) " +
                                "VALUES (?, ?, ?, ?, ?, NOW(), 'Diproses', ?, ?)";
        String insertMenikahSQL = "INSERT INTO surat_menikah (id_surat, jenis_kelamin, agama, status, pekerjaan, kewarganegaraan, nama_pasangan, kewarganegaraan_pasangan) " +
                                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement stmt1 = connection.prepareStatement(insertSuratSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmt2 = connection.prepareStatement(insertMenikahSQL)
        ) {
            connection.setAutoCommit(false);

            // Insert ke tabel surat
            stmt1.setString(1, surat.getNama());
            stmt1.setString(2, surat.getNik());
            stmt1.setString(3, surat.getTempatTanggalLahir());
            stmt1.setString(4, surat.getAlamat());
            stmt1.setString(5, surat.getJenisSurat()); // Harusnya 'Surat Keterangan Menikah'
            stmt1.setString(6, surat.getNomorSurat());
            stmt1.setInt(7, idAdmin);
            stmt1.executeUpdate();

            // Ambil ID surat yang baru saja dibuat
            ResultSet generatedKeys = stmt1.getGeneratedKeys();
            if (!generatedKeys.next()) throw new SQLException("Gagal mengambil ID surat.");
            int idSurat = generatedKeys.getInt(1);

            // Insert ke surat_menikah
            stmt2.setInt(1, idSurat);
            stmt2.setString(2, surat.getJenisKelamin());
            stmt2.setString(3, surat.getAgama());
            stmt2.setString(4, surat.getStatus());
            stmt2.setString(5, surat.getPekerjaan());
            stmt2.setString(6, surat.getKewarganegaraan());
            stmt2.setString(7, surat.getNamaPasangan());
            stmt2.setString(8, surat.getKewarganegaraanPasangan());
            stmt2.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<suratmenikah> getAll() throws SQLException {
        List<suratmenikah> list = new ArrayList<>();
        String sql = "SELECT s.*, m.* FROM surat s JOIN surat_menikah m ON s.id_surat = m.id_surat WHERE s.jenis_surat = 'Surat Keterangan Menikah'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suratmenikah surat = new suratmenikah(
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("agama"),
                    rs.getString("status"),
                    rs.getString("pekerjaan"),
                    rs.getString("kewarganegaraan"),
                    rs.getString("nama_pasangan"),
                    rs.getString("kewarganegaraan_pasangan"),
                    rs.getString("alamat")
                );
                surat.setNomorSurat(rs.getString("nomor_surat"));
                surat.setIdSurat(rs.getInt("id_surat"));
                list.add(surat);
            }
        }
        return list;
    }
}
