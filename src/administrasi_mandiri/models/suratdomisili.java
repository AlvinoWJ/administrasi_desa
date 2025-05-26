package administrasi_mandiri.models;

import java.time.LocalDate;

public class suratdomisili extends surat {
    private String jenisKelamin;
    private String Pekerjaan;
    private String Agama;

    public suratdomisili(String nomorSurat, String nama, String tempatTanggalLahir,
                                String nik, String alamat, LocalDate tanggalPengajuan, String jenisKelamin,
                                String Pekerjaan, String Agama, String desa, String kecamatan, String kabupaten) {
        super(nomorSurat, nama, tempatTanggalLahir, nik, alamat, tanggalPengajuan);
        this.jenisKelamin = jenisKelamin;
        this.Pekerjaan = Pekerjaan;
        this.Agama = Agama;
        this.desa = desa;
        this.kecamatan = kecamatan;
        this.kabupaten = kabupaten;
    }

    public String getJenisKelamin() {return jenisKelamin;}
    public void setJenisKelamin(String jenisKelamin) {this.jenisKelamin = jenisKelamin;}
    
    public String getPekerjaan() {return Pekerjaan;}
    public void setPekerjaan(String Pekerjaan) {this.Pekerjaan = Pekerjaan;}
    
    public String getAgama() {return Agama;}
    public void setAgama(String Agama) {this.Agama = Agama;}
    
    @Override
    public String getJenisSurat() {return "Surat Keterangan Domisili";}

    @Override
    public String generateIsiSurat() {return administrasi_mandiri.utils.templatesurat.suratdomisili(this);}
}
