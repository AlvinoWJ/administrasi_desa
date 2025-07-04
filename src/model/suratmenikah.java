package model;

public class suratmenikah extends surat {
    private String jenisKelamin;
    private String agama;
    private String status;
    private String kewarganegaraan;
    private String namaPasangan;
    private String kewarganegaraanPasangan;

public suratmenikah(String nama, String nik, String tempatTanggalLahir,
                    String alamat, String jenisKelamin, String agama,
                    String status, String kewarganegaraan,
                    String namaPasangan, String kewarganegaraanPasangan) {
        super(nama, nik, tempatTanggalLahir, alamat);
        this.jenisKelamin = jenisKelamin;
        this.agama = agama;
        this.status = status;
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
