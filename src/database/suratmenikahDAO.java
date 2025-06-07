package database;

import model.suratmenikah;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class suratmenikahDAO {
    private Connection connection;

    public suratmenikahDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert data ke tabel surat & surat_menikah
    public void insert(suratmenikah surat) throws SQLException {
        String insertSuratSQL = "INSERT INTO surat (nama, nik, tempat_tanggal_lahir, alamat, jenis_surat) " +
                                "VALUES (?, ?, ?, ?, ?)";
        String updatenomorSuratSQL = "UPDATE surat SET nomor_surat = ? WHERE id_surat = ?";
        String insertMenikahSQL = "INSERT INTO surat_menikah (id_surat, nama_pasangan, jenis_kelamin, agama, status, kewarganegaraan, kewarganegaraan_pasangan) " +
                                  "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (
            PreparedStatement stmt1 = connection.prepareStatement(insertSuratSQL, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stmt2 = connection.prepareStatement(updatenomorSuratSQL);
            PreparedStatement stmt3 = connection.prepareStatement(insertMenikahSQL)
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
                case "Surat Keterangan Menikah" -> "suketmenikah";
                default -> "lainnya";
            };
            String nomorSurat = "XXI-" + idSurat + "/" + kode + "/" + java.time.Year.now().getValue();

            stmt2.setString(1, nomorSurat);
            stmt2.setInt(2, idSurat);
            stmt2.executeUpdate();
            
            // Simpan ke surat_menikah
            stmt3.setInt(1, idSurat);
            stmt3.setString(2, surat.getNamaPasangan());
            stmt3.setString(3, surat.getJenisKelamin());
            stmt3.setString(4, surat.getAgama());
            stmt3.setString(5, surat.getStatus());
            stmt3.setString(6, surat.getKewarganegaraan());
            stmt3.setString(7, surat.getKewarganegaraanPasangan());
            stmt3.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            connection.setAutoCommit(true);
        }
    }

    // Ambil berdasarkan nomor surat
    public suratmenikah getByNomorSurat(String nomorSurat) throws SQLException {
        String sql = "SELECT s.*, m.nama_pasangan, m.jenis_kelamin, m.agama, m.pekerjaan, m.status, m.kewarganegaraan, m.kewarganegaraan_pasangan " +
                     "FROM surat s JOIN surat_menikah m ON s.id_surat = m.id_surat " +
                     "WHERE s.nomor_surat = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, nomorSurat);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    suratmenikah surat = new suratmenikah(
                        rs.getString("nama"),
                        rs.getString("nik"),
                        rs.getString("tempat_tanggal_lahir"),
                        rs.getString("alamat"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("agama"),
                        rs.getString("status"),
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

    // Ambil semua surat menikah
    public List<suratmenikah> getAll() throws SQLException {
        List<suratmenikah> list = new ArrayList<>();
        String sql = "SELECT s.*, m.nama_pasangan, m.jenis_kelamin, m.agama, m.pekerjaan, m.status, m.kewarganegaraan, m.kewarganegaraan_pasangan " +
                     "FROM surat s JOIN surat_menikah m ON s.id_surat = m.id_surat " +
                     "WHERE s.jenis_surat = 'Surat Keterangan Menikah'";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                suratmenikah surat = new suratmenikah(
                    rs.getString("nama"),
                    rs.getString("nik"),
                    rs.getString("tempat_tanggal_lahir"),
                    rs.getString("alamat"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("agama"),
                    rs.getString("status"),
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

    // Hapus surat menikah
    public void delete(String nomorSurat) throws SQLException {
        String sqlGetId = "SELECT id_surat FROM surat WHERE nomor_surat = ?";
        String sqlDeleteMenikah = "DELETE FROM surat_menikah WHERE id_surat = ?";
        String sqlDeleteSurat = "DELETE FROM surat WHERE id_surat = ?";

        try (
            PreparedStatement stmtGetId = connection.prepareStatement(sqlGetId);
            PreparedStatement stmt1 = connection.prepareStatement(sqlDeleteMenikah);
            PreparedStatement stmt2 = connection.prepareStatement(sqlDeleteSurat)
        ) {
            connection.setAutoCommit(false);

            stmtGetId.setString(1, nomorSurat);
            int idSurat;
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
