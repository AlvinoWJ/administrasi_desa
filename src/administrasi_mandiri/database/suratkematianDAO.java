package administrasi_mandiri.database;

import administrasi_mandiri.models.suratkematian;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class suratkematianDAO {
    private Connection connection;

    public suratkematianDAO(Connection connection) {
        this.connection = connection;
    }

    public void insert(suratkematian surat) throws SQLException {
        String insertSuratSQL = "INSERT INTO surat (nomor_surat, nama, nik, tempat_tanggal_lahir, alamat, desa, kecamatan, kabupaten, jenis_surat) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertKematianSQL = "INSERT INTO surat_kematian (nomor_surat, jenis_kelamin, pekerjaan, status, agama, hari_tanggal_meninggal, jam_meninggal, sebab_kematian, yang_menerangkan_kematian, tempat_kematian) " +
                                   "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement stmt1 = connection.prepareStatement(insertSuratSQL);
            PreparedStatement stmt2 = connection.prepareStatement(insertKematianSQL)
        ) {
            connection.setAutoCommit(false);

            // Tabel surat (umum)
            stmt1.setString(1, surat.getNomorSurat());
            stmt1.setString(2, surat.getNama());
            stmt1.setString(3, surat.getNik());
            stmt1.setString(4, surat.getTempatTanggalLahir());
            stmt1.setString(5, surat.getAlamat());
            stmt1.setString(6, surat.getDesa());
            stmt1.setString(7, surat.getKecamatan());
            stmt1.setString(8, surat.getKabupaten());
            stmt1.setString(9, surat.getJenisSurat());
            stmt1.executeUpdate();

            // Tabel surat_kematian (khusus)
            stmt2.setString(1, surat.getNomorSurat());
            stmt2.setString(2, surat.getJenisKelamin());
            stmt2.setString(3, surat.getPekerjaan());
            stmt2.setString(4, surat.getStatus());
            stmt2.setString(5, surat.getAgama());
            stmt2.setString(6, surat.getHariTanggalMeninggal());
            stmt2.setString(7, surat.getJamMeninggal());
            stmt2.setString(8, surat.getSebabKematian());
            stmt2.setString(9, surat.getYangMenerangkanKematian());
            stmt2.setString(10, surat.getTempatKematian());
            stmt2.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public suratkematian getByNomorSurat(String nomorSurat) throws SQLException {
        String sql = "SELECT s.*, k.* " +
                     "FROM surat s JOIN surat_kematian k ON s.nomor_surat = k.nomor_surat " +
                     "WHERE s.nomor_surat = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomorSurat);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    suratkematian surat = new suratkematian(
                        rs.getString("nama"),
                        rs.getString("tempat_tanggal_lahir"),
                        rs.getString("nik"),
                        rs.getString("alamat"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("pekerjaan"),
                        rs.getString("status"),
                        rs.getString("agama"),
                        rs.getString("hari_tanggal_meninggal"),
                        rs.getString("jam_meninggal"),
                        rs.getString("sebab_kematian"),
                        rs.getString("yang_menerangkan_kematian"),
                        rs.getString("tempat_kematian")
                    );
                    surat.setNomorSurat(rs.getString("nomor_surat"));
                    return surat;
                }
            }
        }
        return null;
    }

    public void delete(String nomorSurat) throws SQLException {
        String deleteKematianSQL = "DELETE FROM surat_kematian WHERE nomor_surat = ?";
        String deleteSuratSQL = "DELETE FROM surat WHERE nomor_surat = ?";

        try (
            PreparedStatement stmt1 = connection.prepareStatement(deleteKematianSQL);
            PreparedStatement stmt2 = connection.prepareStatement(deleteSuratSQL)
        ) {
            connection.setAutoCommit(false);

            stmt1.setString(1, nomorSurat);
            stmt1.executeUpdate();

            stmt2.setString(1, nomorSurat);
            stmt2.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    public List<suratkematian> getAll() throws SQLException {
        List<suratkematian> list = new ArrayList<>();
        String sql = "SELECT s.*, k.* " +
                     "FROM surat s JOIN surat_kematian k ON s.nomor_surat = k.nomor_surat " +
                     "WHERE s.jenis_surat = 'Surat Keterangan Kematian'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suratkematian surat = new suratkematian(
                    rs.getString("nama"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("nik"),
                    rs.getString("alamat"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("pekerjaan"),
                    rs.getString("status"),
                    rs.getString("agama"),
                    rs.getString("hari_tanggal_meninggal"),
                    rs.getString("jam_meninggal"),
                    rs.getString("sebab_kematian"),
                    rs.getString("yang_menerangkan_kematian"),
                    rs.getString("tempat_kematian")
                );
                surat.setNomorSurat(rs.getString("nomor_surat"));
                list.add(surat);
            }
        }
        return list;
    }
}
