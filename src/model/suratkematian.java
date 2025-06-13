package model;

public class suratkematian extends surat {
    private String jenisKelamin;
    private String Pekerjaan;
    private String agama;
    private String hariTanggalMeninggal;
    private String jamMeninggal;
    private String sebabKematian;
    private String yangMenerangkanKematian;
    private String tempatKematian;
    private String kewarganegaraan;
    private String namaPemohon;
    
    public suratkematian(String nama, String nik, String tempatTanggalLahir,
                    String alamat, String jenisKelamin, String Pekerjaan, 
                    String agama, String hariTanggalMeninggal, String jamMeninggal,
                    String sebabKematian, String yangMenerangkanKematian, String tempatKematian) {
        super(nama, nik, tempatTanggalLahir, alamat);
        this.jenisKelamin = jenisKelamin;
        this.Pekerjaan = Pekerjaan;
        this.agama = agama;
        this.hariTanggalMeninggal = hariTanggalMeninggal;
        this.jamMeninggal = jamMeninggal;
        this.sebabKematian = sebabKematian;
        this.yangMenerangkanKematian = yangMenerangkanKematian;
        this.tempatKematian = tempatKematian;
    }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
    
    public String getPekerjaan() { return Pekerjaan; }
    public void setPekerjaan(String Pekerjaan) { this.Pekerjaan = Pekerjaan; }

    public String getAgama() { return agama; }
    public void setAgama(String agama) { this.agama = agama; }

    public String getHariTanggalMeninggal() { return hariTanggalMeninggal; }
    public void setHariTanggalMeninggal(String hariTanggalMeninggal) { this.hariTanggalMeninggal = hariTanggalMeninggal; }

    public String getJamMeninggal() { return jamMeninggal; }
    public void setJamMeninggal(String jamMeninggal) { this.jamMeninggal = jamMeninggal; }

    public String getSebabKematian() { return sebabKematian; }
    public void setSebabKematian(String sebabKematian) { this.sebabKematian = sebabKematian; }

    public String getYangMenerangkanKematian() { return yangMenerangkanKematian; }
    public void setYangMenerangkanKematian(String yangMenerangkanKematian) { this.yangMenerangkanKematian = yangMenerangkanKematian; }

    public String getTempatKematian() { return tempatKematian; }
    public void setTempatKematian(String tempatKematian) { this.tempatKematian = tempatKematian; }

    public String getNamaPemohon() {
    return this.yangMenerangkanKematian;}

    
    @Override
    public String getJenisSurat() {return "Surat Keterangan Kematian";}

    @Override
    public String generateIsiSurat() {return utils.templatesurat.suratkematian(this);}
}
