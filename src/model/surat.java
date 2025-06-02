package model;

import java.time.LocalDate;

public abstract class surat {
    protected String nama;
    protected String nik;
    protected String tempatTanggalLahir;
    protected String alamat; 
    protected String nomorSurat;
    protected String statusSurat;
    protected LocalDate tanggalBuat;
    
    public surat(String nama, String nik, String tempatTanggalLahir,
                 String alamat) {
        this.nama = nama;
        this.nik = nik;
        this.tempatTanggalLahir = tempatTanggalLahir;
        this.alamat = alamat;
        this.statusSurat = "Diproses";
        this.tanggalBuat = LocalDate.now();
    }

    public String getNama() {return nama;}
    public void setNama(String nama) {this.nama = nama;}
    
    public String getNik() {return nik;}
    public void setNik(String nik) {this.nik = nik;}
    
    public String getTempatTanggalLahir() {return tempatTanggalLahir;}
    public void setTempatTanggalLahir(String TempatTanggalLahir) {this.tempatTanggalLahir = TempatTanggalLahir;}

    public String getAlamat() {return alamat;}
    public void setAlamat(String alamat) {this.alamat = alamat;}
    
    public String getNomorSurat() { return nomorSurat; }
    public void setNomorSurat(String nomorSurat) { this.nomorSurat = nomorSurat; }
    
    public String getstatusSurat() { return statusSurat; }
    public void setstatusSurat(String statusSurat) { this.statusSurat = statusSurat; }
    
    public LocalDate getTanggalBuat() { return tanggalBuat; }
    public void generateNomorSurat(int urutan) {
        String jenis = getJenisSurat().toLowerCase();
        int tahun = tanggalBuat.getYear();
        this.nomorSurat = String.format("%03d/%s/%d", urutan, jenis, tahun);
    }

    public abstract String getJenisSurat();
    public abstract String generateIsiSurat();
}
