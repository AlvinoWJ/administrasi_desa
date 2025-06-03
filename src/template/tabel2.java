package template;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Sebuah panel yang berisi JTable kustom yang selnya tidak bisa diedit (non-editable)
 * tetapi barisnya bisa diseleksi.
 */
public class tabel2 extends JPanel {

    private JTable table;
    private DefaultTableModel model;

    public tabel2() {
        // 1. Mengatur layout untuk panel ini
        super(new BorderLayout());

        // 2. Mendefinisikan nama-nama kolom
        String[] namaKolom = {"ID", "Nama", "Kota"};

        model = new DefaultTableModel(
        new Object[][] {
        { "1", "Surat Kematian", "111", "Budi", "Malang, 01-06-2005", "02-06-2025", "" },
        { "2", "Surat Usaha", "222", "Charis", "Sidoarjo, 08-06-2005", "15-06-2025", "" },
        { "3", "Surat Domisili", "333", "Ani", "Malang, 02-06-2003", "16-06-2025", "" },
        { "4", "Surat Menikah", "444", "Tasya", "Malang, 03-06-2002", "21-06-2025", "" }
        },
            new Object[] { "No Surat", "Jenis Surat", "NIK", "Nama", "Tempat tgl Lahir", "Tgl Pengajuan", "Dokumen" }
        ){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        

        // 4. Membuat JTable dengan model yang sudah dikustomisasi
        table = new JTable(model);

        // 5. Mengatur properti tabel agar lebih baik
        table.setRowHeight(28); // Mengatur tinggi baris
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Mengatur font isi tabel
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14)); // Mengatur font header
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Hanya bisa menyeleksi satu baris

        // 6. Menambahkan tabel ke dalam JScrollPane agar bisa di-scroll
        JScrollPane scrollPane = new JScrollPane(table);

        // 7. Menambahkan scrollPane ke panel ini
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Metode untuk mendapatkan referensi ke model tabel.
     * Berguna untuk menambah atau menghapus data dari luar kelas ini.
     * @return DefaultTableModel dari tabel
     */
    public DefaultTableModel getModel() {
        return model;
    }

    /**
     * Metode untuk mendapatkan objek JTable-nya secara langsung.
     * @return JTable yang ada di dalam panel
     */
    public JTable getTable() {
        return table;
    }

    /**
     * Helper method untuk mendapatkan nomor baris yang sedang diseleksi.
     * Mengembalikan -1 jika tidak ada baris yang dipilih.
     * @return indeks baris model yang diseleksi
     */
    public int getSelectedRow() {
        int barisTerpilih = table.getSelectedRow();
        if (barisTerpilih >= 0) {
            // Konversi dari indeks view ke indeks model (penting jika ada sorting)
            return table.convertRowIndexToModel(barisTerpilih);
        }
        return -1; // Tidak ada baris yang dipilih
    }
}