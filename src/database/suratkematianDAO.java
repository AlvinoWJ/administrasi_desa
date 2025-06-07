package database;

import model.suratkematian;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class suratkematianDAO {
    private Connection connection;

    public suratkematianDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(suratkematian surat) throws SQLException {
        String insertSuratSQL = "INSERT INTO surat (nama, nik, tempat_tanggal_lahir, alamat, jenis_surat) " +
                                "VALUES (?, ?, ?, ?, ?)";
        String updatenomorSuratSQL = "UPDATE surat SET nomor_surat = ? WHERE id_surat = ?";
        String insertKematianSQL = "INSERT INTO surat_kematian (id_surat, jenis_kelamin, agama, pekerjaan, hari_tanggal_meninggal, jam_meninggal, sebab_kematian, yang_menerangkan_kematian, tempat_kematian) " +
                                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement stmt1 = connection.prepareStatement(insertSuratSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmt2 = connection.prepareStatement(updatenomorSuratSQL);
            PreparedStatement stmt3 = connection.prepareStatement(insertKematianSQL)
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
                case "Surat Keterangan Kematian" -> "suketkematian";
                default -> "lainnya";
            };
            String nomorSurat = "XXI-" + idSurat + "/" + kode + "/" + java.time.Year.now().getValue();
            
            stmt2.setString(1, nomorSurat);
            stmt2.setInt(2, idSurat);
            stmt2.executeUpdate();

            // Insert ke tabel surat_kematian
            stmt3.setInt(1, idSurat);
            stmt3.setString(2, surat.getJenisKelamin());
            stmt3.setString(3, surat.getAgama());
            stmt3.setString(4, surat.getPekerjaan());
            stmt3.setString(5, surat.getHariTanggalMeninggal());
            stmt3.setString(6, surat.getJamMeninggal());
            stmt3.setString(7, surat.getSebabKematian());
            stmt3.setString(8, surat.getYangMenerangkanKematian());
            stmt3.setString(9, surat.getTempatKematian());
            stmt3.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public suratkematian getByNomorSurat(String nomorSurat) throws SQLException {
        String sql = "SELECT s.*, k.* FROM surat s JOIN surat_kematian k ON s.id_surat = k.id_surat WHERE s.nomor_surat = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomorSurat);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    suratkematian surat = new suratkematian(
                        rs.getString("nama"),
                        rs.getString("nik"),
                        rs.getString("tempat_tanggal_lahir"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("agama"),
                        rs.getString("pekerjaan"),
                        rs.getString("hari_tanggal_meninggal"),
                        rs.getString("jam_meninggal"),
                        rs.getString("sebab_kematian"),
                        rs.getString("yang_menerangkan_kematian"),
                        rs.getString("tempat_kematian"),
                        rs.getString("alamat")
                    );
                    surat.setNomorSurat(rs.getString("nomor_surat"));
                    return surat;
                }
            }
        }
        return null;
    }

    public List<suratkematian> getAll() throws SQLException {
        List<suratkematian> list = new ArrayList<>();
        String sql = "SELECT s.*, k.* FROM surat s JOIN surat_kematian k ON s.id_surat = k.id_surat WHERE s.jenis_surat = 'Surat Keterangan Kematian'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suratkematian surat = new suratkematian(
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("agama"),
                    rs.getString("pekerjaan"),
                    rs.getString("hari_tanggal_meninggal"),
                    rs.getString("jam_meninggal"),
                    rs.getString("sebab_kematian"),
                    rs.getString("yang_menerangkan_kematian"),
                    rs.getString("tempat_kematian"),
                    rs.getString("alamat")
                );
                surat.setNomorSurat(rs.getString("nomor_surat"));
                list.add(surat);
            }
        }
        return list;
    }

    public void delete(String nomorSurat) throws SQLException {
        String sqlGetId = "SELECT id_surat FROM surat WHERE nomor_surat = ?";
        String sqlDeleteKematian = "DELETE FROM surat_kematian WHERE id_surat = ?";
        String sqlDeleteSurat = "DELETE FROM surat WHERE id_surat = ?";

        try (
            PreparedStatement stmtGetId = connection.prepareStatement(sqlGetId);
            PreparedStatement stmt1 = connection.prepareStatement(sqlDeleteKematian);
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
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
