package model;

public class suratmenikah extends surat {
    private String jenisKelamin;
    private String agama;
    private String status;
    private String pekerjaan;
    private String kewarganegaraan;
    private String namaPasangan;
    private String kewarganegaraanPasangan;

public suratmenikah(String nama, String tempatTanggalLahir, String nik,
                                   String alamat, String jenisKelamin, String agama,
                                   String status, String pekerjaan, String kewarganegaraan,
                                   String namaPasangan, String kewarganegaraanPasangan) {
        super(nama, tempatTanggalLahir, nik, alamat);
        this.jenisKelamin = jenisKelamin;
        this.agama = agama;
        this.status = status;
        this.pekerjaan = pekerjaan;
        this.kewarganegaraan = kewarganegaraan;
        this.namaPasangan = namaPasangan;
        this.kewarganegaraanPasangan = kewarganegaraanPasangan;
    }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public String getAgama() { return agama; }
    public void setAgama(String agama) { this.agama = agama; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPekerjaan() { return pekerjaan; }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }

    public String getKewarganegaraan() { return kewarganegaraan; }
    public void setKewarganegaraan(String kewarganegaraan) { this.kewarganegaraan = kewarganegaraan; }

    public String getNamaPasangan() { return namaPasangan; }
    public void setNamaPasangan(String namaPasangan) { this.namaPasangan = namaPasangan; }

    public String getKewarganegaraanPasangan() { return kewarganegaraanPasangan; }
    public void setKewarganegaraanPasangan(String kewarganegaraanPasangan) { this.kewarganegaraanPasangan = kewarganegaraanPasangan; }

    @Override
    public String getJenisSurat() {return "Surat Keterangan Menikah";}

    @Override
    public String generateIsiSurat() {return utils.templatesurat.suratmenikah(this);}
}
