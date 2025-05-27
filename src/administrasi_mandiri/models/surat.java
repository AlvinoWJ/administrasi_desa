package administrasi_mandiri.models;

import java.time.LocalDate;

public abstract class surat {
    protected String nama;
    protected String nik;
    protected String tempatTanggalLahir;
    protected String alamat;
    protected String desa = "Sugihwaras";             
    protected String kecamatan = "Candi";   
    protected String kabupaten = "Sidoarjo";   
    protected String nomorSurat;

    public surat(String nama, String nik, String tempatTanggalLahir,
                 String alamat) {
        this.nama = nama;
        this.nik = nik;
        this.tempatTanggalLahir = tempatTanggalLahir;
        this.alamat = alamat;
    }

    public String getNama() {return nama;}
    public void setNama(String nama) {this.nama = nama;}
    
    public String getTempatTanggalLahir() {return tempatTanggalLahir;}
    public void setTempatTanggalLahir(String TempatTanggalLahir) {this.tempatTanggalLahir = TempatTanggalLahir;}

    public String getNik() {return nik;}
    public void setNik(String nik) {this.nik = nik;}

    public String getAlamat() {return alamat;}
    public void setAlamat(String alamat) {this.alamat = alamat;}
    
    public String getNomorSurat() {return nomorSurat;}
    public void setNomorSurat(String nomorSurat) {this.nomorSurat = nomorSurat;}
    
    public String getDesa() { return desa; }
    
    public String getKecamatan() { return kecamatan; }
    
    public String getKabupaten() { return kabupaten; }

    public abstract String getJenisSurat();
    public abstract String generateIsiSurat();
}
