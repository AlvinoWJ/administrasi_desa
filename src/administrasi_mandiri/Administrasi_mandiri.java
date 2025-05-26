/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package administrasi_mandiri;

import administrasi_mandiri.database.koneksidatabase;
import java.sql.Connection;
/**
 *
 * @author hp
 */
public class Administrasi_mandiri {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Connection conn = koneksidatabase.getConnection();
            System.out.println("✅ Koneksi ke database berhasil!");
            conn.close();
        } catch (Exception e) {
            System.out.println("❌ Koneksi gagal: " + e.getMessage());
        }
    }
    
}
