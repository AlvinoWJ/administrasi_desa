package administrasi_mandiri.models;

import java.time.LocalDate;

public abstract class surat {
    protected String nomorSurat;
    protected String nama;
    protected String nik;
    protected String tempatTanggalLahir;
    protected String alamat;
    protected String desa = "";             
    protected String kecamatan = "";   
    protected String kabupaten = "";   
    protected LocalDate tanggalPengajuan;

    public surat(String nomorSurat, String nama, String tempatTanggalLahir, String nik,
                 String alamat, LocalDate tanggalPengajuan) {
        this.nomorSurat = nomorSurat;
        this.nama = nama;
        this.tempatTanggalLahir = tempatTanggalLahir;
        this.nik = nik;
        this.alamat = alamat;
        this.tanggalPengajuan = tanggalPengajuan;
    }

    public String getNomorSurat() {return nomorSurat;}
    public void setNomorSurat(String nomorSurat) {this.nomorSurat = nomorSurat;}

    public String getNama() {return nama;}
    public void setNama(String nama) {this.nama = nama;}
    
    public String getTempatTanggalLahir() {return tempatTanggalLahir;}
    public void setTempatTanggalLahir(String TempatTanggalLahir) {this.tempatTanggalLahir = TempatTanggalLahir;}

    public String getNik() {return nik;}
    public void setNik(String nik) {this.nik = nik;}

    public String getAlamat() {return alamat;}
    public void setAlamat(String alamat) {this.alamat = alamat;}

    public LocalDate getTanggalPengajuan() {return tanggalPengajuan;}
    public void setTanggalPengajuan(LocalDate tanggalPengajuan) {this.tanggalPengajuan = tanggalPengajuan;}
    
    public String getDesa() {return desa;}
    public void setDesa(String desa) {this.desa = desa;}

    public String getKecamatan() {return kecamatan;}
    public void setKecamatan(String kecamatan) {this.kecamatan = kecamatan;}

    public String getKabupaten() {return kabupaten;}
    public void setKabupaten(String kabupaten) {this.kabupaten = kabupaten;}

    public abstract String getJenisSurat();
    public abstract String generateIsiSurat();
}
