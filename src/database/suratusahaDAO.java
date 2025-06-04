package database;

import model.suratusaha;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class suratusahaDAO {
    private Connection connection;

    public suratusahaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(suratusaha surat) throws SQLException {
        String insertSuratSQL = "INSERT INTO surat (nama, nik, tempat_tanggal_lahir, alamat, jenis_surat) " +
                                "VALUES (?, ?, ?, ?, ?)";
        String updatenomorSuratSQL = "UPDATE surat SET nomor_surat = ? WHERE id_surat = ?";
        String insertUsahaSQL = "INSERT INTO surat_usaha (id_surat, jenis_kelamin, agama, status_perkawinan, nama_usaha, jenis_usaha, alamat_usaha) " +
                                 "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement stmt1 = connection.prepareStatement(insertSuratSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmt2 = connection.prepareStatement(updatenomorSuratSQL);
            PreparedStatement stmt3 = connection.prepareStatement(insertUsahaSQL)
        ) {
            connection.setAutoCommit(false);
            stmt1.setString(1, surat.getNama());
            stmt1.setString(2, surat.getNik());
            stmt1.setString(3, surat.getTempatTanggalLahir());
            stmt1.setString(4, surat.getAlamat());
            stmt1.setString(5, surat.getJenisSurat());
            stmt1.executeUpdate();

            // Ambil id_surat yang baru di-generate
            ResultSet generatedKeys = stmt1.getGeneratedKeys();
            if (!generatedKeys.next()) throw new SQLException("Gagal mengambil ID surat.");
            int idSurat = generatedKeys.getInt(1);

            String kode = switch(surat.getJenisSurat()) {
                case "Surat Keterangan Usaha" -> "suketusaha";
                default -> "lainnya";
            };
            String nomorSurat = "XXI/" + kode + "/" + java.time.Year.now().getValue() + "-" + idSurat;

            
            stmt2.setString(1, nomorSurat);
            stmt2.setInt(2, idSurat);
            stmt2.executeUpdate();
            
            // Simpan ke surat_usaha
            stmt3.setInt(1, idSurat);
            stmt3.setString(2, surat.getJenisKelamin());
            stmt3.setString(3, surat.getAgama());
            stmt3.setString(4, surat.getStatusPerkawinan());
            stmt3.setString(5, surat.getNamaUsaha());
            stmt3.setString(6, surat.getJenisUsaha());
            stmt3.setString(7, surat.getAlamatUsaha());
            stmt3.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<suratusaha> getAll() throws SQLException {
        List<suratusaha> list = new ArrayList<>();
        String sql = "SELECT s.*, u.* FROM surat s JOIN surat_usaha u ON s.nomor_surat = u.nomor_surat WHERE s.jenis_surat = 'Surat Keterangan Usaha'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suratusaha surat = new suratusaha(
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("agama"),
                    rs.getString("status_perkawinan"),
                    rs.getString("nama_usaha"),
                    rs.getString("jenis_usaha"),
                    rs.getString("alamat_usaha"),
                    rs.getString("alamat")
                );
                surat.setNomorSurat(rs.getString("nomor_surat"));
                list.add(surat);
            }
        }
        return list;
    }
}
