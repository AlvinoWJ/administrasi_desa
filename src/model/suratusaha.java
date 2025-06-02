package model;

public class suratusaha extends surat {
    private String jenisKelamin;
    private String agama;
    private String statusPerkawinan;
    private String pekerjaan;
    private String namaUsaha;
    private String jenisUsaha;
    private String alamatUsaha;

public suratusaha(String nama, String tempatTanggalLahir, String nik,
                String alamat, String jenisKelamin, String agama, 
                String statusPerkawinan, String pekerjaan, String namaUsaha, String jenisUsaha,
                String alamatUsaha) {
        super(nama, tempatTanggalLahir, nik, alamat);
        this.jenisKelamin = jenisKelamin;
        this.agama = agama;
        this.statusPerkawinan = statusPerkawinan;
        this.pekerjaan = pekerjaan;
        this.namaUsaha = namaUsaha;
        this.jenisUsaha = jenisUsaha;
        this.alamatUsaha = alamatUsaha;
    }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
    
    public String getAgama() { return agama; }
    public void setAgama(String agama) { this.agama = agama; }
    
    public String getStatusPerkawinan() { return statusPerkawinan; }
    public void setStatusPerkawinan(String statusPerkawinan) { this.statusPerkawinan = statusPerkawinan; }
    
    public String getPekerjaan() { return pekerjaan; }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }

    public String getNamaUsaha() { return namaUsaha; }
    public void setNamaUsaha(String namaUsaha) { this.namaUsaha = namaUsaha; }

    public String getJenisUsaha() { return jenisUsaha; }
    public void setJenisUsaha(String jenisUsaha) { this.jenisUsaha = jenisUsaha; }

    public String getAlamatUsaha() { return alamatUsaha; }
    public void setAlamatUsaha(String alamatUsaha) { this.alamatUsaha = alamatUsaha; }

    @Override
    public String getJenisSurat() {return "Surat Keterangan Usaha";}

    @Override
    public String generateIsiSurat() {return utils.templatesurat.suratusaha(this);}
}
    