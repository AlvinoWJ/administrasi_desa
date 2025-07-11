package utils;

import model.*;

public class templatesurat {
    public static String suratdomisili(suratdomisili surat) {
        return String.format("""
                             SURAT KETERANGAN DOMISILI
                             
                             Yang bertanda tangan di bawah ini, Kepala Desa %s, Kecamatan %s, Kabupaten %s,
                             dengan ini menerangkan bahwa:
                             
                             Nama              : %s
                             Jenis Kelamin     : %s
                             Tempat/Tgl Lahir  : %s
                             NIK               : %s
                             Pekerjaan         : %s
                             Alamat            : %s
                             
                             Adalah benar berdomisili di wilayah Desa %s.
                             
                             Demikian surat ini dibuat untuk dipergunakan sebagaimana mestinya.
                             """,
            
            surat.getNama(), surat.getJenisKelamin(), surat.getTempatTanggalLahir(),
            surat.getNik(), surat.getPekerjaan(), surat.getAlamat()
            
        );
    }

    public static String suratkematian(suratkematian surat) {
        return String.format("""
                             SURAT KETERANGAN KEMATIAN
                             
                             Yang bertanda tangan di bawah ini, Kepala Desa %s, Kecamatan %s, Kabupaten %s,
                             dengan ini menerangkan bahwa:
                             
                             Nama              : %s
                             Jenis Kelamin     : %s
                             Tempat/Tgl Lahir  : %s
                             NIK               : %s
                             Agama             : %s
                             Status Perkawinan : %s
                             Pekerjaan         : %s
                             Alamat            : %s
                             
                             Telah meninggal dunia pada:
                             Hari/Tanggal      : %s
                             Jam               : %s
                             Tempat            : %s
                             Sebab             : %s
                             Yang Menerangkan  : %s
                             
                             Demikian surat ini dibuat dengan sebenarnya untuk dipergunakan sebagaimana mestinya.""",
            surat.getNama(), surat.getJenisKelamin(), surat.getTempatTanggalLahir(),
            surat.getNik(), surat.getAgama(), surat.getPekerjaan(),
            surat.getAlamat(), surat.getHariTanggalMeninggal(), surat.getJamMeninggal(),
            surat.getTempatKematian(), surat.getSebabKematian(), surat.getYangMenerangkanKematian()
        );
    }
    
    public static String suratusaha(suratusaha surat) {
    return String.format("""
                         SURAT KETERANGAN USAHA
                         
                            Yang bertanda tangan di bawah ini, Kepala Desa %s, Kecamatan %s, Kabupaten %s,
                         dengan ini menerangkan bahwa:
                         
                         Nama              : %s
                         Tempat/Tgl Lahir  : %s
                         NIK               : %s
                         Jenis Kelamin     : %s
                         Agama             : %s
                         Status Perkawinan : %s
                         Pekerjaan         : %s
                         Alamat            : %s
                         
                         Orang tersebut di atas benar-benar mempunyai usaha:
                         Nama Usaha        : %s
                         Jenis Usaha       : %s
                         Alamat Usaha      : %s                
                         
                            Demikian surat keterangan ini kami buat dengan sebenar-benarnya dan untuk
                         dipergunakan sebagaimana mestinya.""",
        surat.getNama(), surat.getTempatTanggalLahir(), surat.getNik(), surat.getJenisKelamin(),
        surat.getAgama(), surat.getStatusPerkawinan(), surat.getAlamat(),
        surat.getNamaUsaha(), surat.getJenisUsaha(), surat.getAlamatUsaha()
    );
}
    
    public static String suratmenikah(suratmenikah surat) {
    return String.format("""
                         SURAT KETERANGAN MENIKAH
                         Nomor : %s
                         
                         Yang bertanda tangan di bawah ini Kepala Desa %s Kecamatan %s Kota %s, dengan ini menerangkan bahwa :
                         
                         Nama              : %s
                         Jenis Kelamin     : %s
                         Tempat / Tgl. Lahir : %s
                         Agama             : %s
                         Status            : %s
                         Pekerjaan         : %s
                         Kewarganegaraan   : %s
                         Alamat            : %s
                         
                         Adalah Penduduk Asli Desa %s Kecamatan %s Kota %s dan sepanjang pengetahuan kami,
                         serta catatan pada kami bahwa yang bersangkutan memang benar Telah Menikah dengan :
                         
                         Nama              : %s
                         Kewarganegaraan   : %s
                         
                         Demikianlah Surat Keterangan ini kami buat dengan sebenarnya untuk dapat dipergunakan sebagaimana mestinya.
                         """,
        surat.getNomorSurat(),
        surat.getNama(), surat.getJenisKelamin(), surat.getTempatTanggalLahir(),
        surat.getAgama(), surat.getStatus(), surat.getKewarganegaraan(),
        surat.getAlamat(), surat.getNamaPasangan(), surat.getKewarganegaraanPasangan()
        );
    }
}
