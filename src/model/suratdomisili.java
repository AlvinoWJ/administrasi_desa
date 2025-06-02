package model;

public class suratdomisili extends surat {
    private String jenisKelamin;
    private String Pekerjaan;
    private String Agama;

    public suratdomisili(String nama, String nik, String tempatTanggalLahir,
                        String jenisKelamin, String Agama, String Pekerjaan, 
                        String alamat) {
        super(nama, nik, tempatTanggalLahir, alamat);
        this.jenisKelamin = jenisKelamin;
        this.Pekerjaan = Pekerjaan;
        this.Agama = Agama;
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
    public String generateIsiSurat() {return utils.templatesurat.suratdomisili(this);}
}
