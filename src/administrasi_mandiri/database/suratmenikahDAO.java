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

    public void insert(suratmenikah surat) throws SQLException {
        String insertSuratSQL = "INSERT INTO surat (nomor_surat, nama, nik, tempat_tanggal_lahir, alamat, desa, kecamatan, kabupaten, jenis_surat) " +
                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String insertMenikahSQL = "INSERT INTO surat_menikah (nomor_surat, jenis_kelamin, agama, status, pekerjaan, kewarganegaraan, nama_pasangan, kewarganegaraan_pasangan) " +
                                  "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            PreparedStatement stmt1 = connection.prepareStatement(insertSuratSQL);
            PreparedStatement stmt2 = connection.prepareStatement(insertMenikahSQL)
        ) {
            connection.setAutoCommit(false);

            // Insert ke tabel umum "surat"
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

            // Insert ke tabel khusus "surat_menikah"
            stmt2.setString(1, surat.getNomorSurat());
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

    public suratmenikah getByNomorSurat(String nomorSurat) throws SQLException {
        String sql = "SELECT s.*, m.* " +
                     "FROM surat s JOIN surat_menikah m ON s.nomor_surat = m.nomor_surat " +
                     "WHERE s.nomor_surat = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomorSurat);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    suratmenikah surat = new suratmenikah(
                        rs.getString("nama"),
                        rs.getString("tempat_tanggal_lahir"),
                        rs.getString("nik"),
                        rs.getString("alamat"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("agama"),
                        rs.getString("status"),
                        rs.getString("pekerjaan"),
                        rs.getString("kewarganegaraan"),
                        rs.getString("nama_pasangan"),
                        rs.getString("kewarganegaraan_pasangan")
                    );
                    surat.setNomorSurat(rs.getString("nomor_surat"));
                    return surat;
                }
            }
        }
        return null;
    }

    public void delete(String nomorSurat) throws SQLException {
        String deleteMenikahSQL = "DELETE FROM surat_menikah WHERE nomor_surat = ?";
        String deleteSuratSQL = "DELETE FROM surat WHERE nomor_surat = ?";

        try (
            PreparedStatement stmt1 = connection.prepareStatement(deleteMenikahSQL);
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

    public List<suratmenikah> getAll() throws SQLException {
        List<suratmenikah> list = new ArrayList<>();
        String sql = "SELECT s.*, m.* " +
                     "FROM surat s JOIN surat_menikah m ON s.nomor_surat = m.nomor_surat " +
                     "WHERE s.jenis_surat = 'Surat Keterangan Menikah'";
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suratmenikah surat = new suratmenikah(
                    rs.getString("nama"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("nik"),
                    rs.getString("alamat"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("agama"),
                    rs.getString("status"),
                    rs.getString("pekerjaan"),
                    rs.getString("kewarganegaraan"),
                    rs.getString("nama_pasangan"),
                    rs.getString("kewarganegaraan_pasangan")
                );
                surat.setNomorSurat(rs.getString("nomor_surat"));
                list.add(surat);
            }
        }
        return list;
    }
}
