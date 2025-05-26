package administrasi_mandiri.database;

import administrasi_mandiri.models.suratdomisili;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class suratdomisiliDAO {

    public static boolean simpanSurat(suratdomisili surat) {
        String sqlSurat = "INSERT INTO surat (nomor_surat, nama, tempat_tanggal_lahir, nik, alamat, tanggal_pengajuan) VALUES (?, ?, ?, ?, ?, ?)";
        String sqlDetail = "INSERT INTO surat_domisili (nomor_surat, jenis_kelamin, agama, status_perkawinan, pekerjaan, kewarganegaraan) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = koneksidatabase.getConnection()) {
            conn.setAutoCommit(false);

            try (
                PreparedStatement stmtSurat = conn.prepareStatement(sqlSurat);
                PreparedStatement stmtDetail = conn.prepareStatement(sqlDetail);
            ) {
                // Insert ke tabel surat (umum)
                stmtSurat.setString(1, surat.getNomorSurat());
                stmtSurat.setString(2, surat.getNama());
                stmtSurat.setString(3, surat.getTempatTanggalLahir());
                stmtSurat.setString(4, surat.getNik());
                stmtSurat.setString(5, surat.getAlamat());
                stmtSurat.setDate(6, java.sql.Date.valueOf(surat.getTanggalPengajuan()));
                stmtSurat.executeUpdate();

                // Insert ke tabel surat_domisili (khusus)
                stmtDetail.setString(1, surat.getNomorSurat());
                stmtDetail.setString(2, surat.getJenisKelamin());
                stmtDetail.setString(3, surat.getAgama());
                stmtDetail.setString(5, surat.getPekerjaan());
                stmtDetail.executeUpdate();

                conn.commit();
                return true;

            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Gagal menyimpan surat domisili: " + e.getMessage());
                return false;
            }

        } catch (SQLException e) {
            System.out.println("Koneksi gagal: " + e.getMessage());
            return false;
        }
    }
}
