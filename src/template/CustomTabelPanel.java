package template;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.RowFilter;
import java.awt.*;

public class CustomTabelPanel extends JPanel {
    private JTable table;
    private JTextField searchField;
    private TableRowSorter<TableModel> rowSorter;

    public CustomTabelPanel() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel atas untuk search bar (pakai FlowLayout kiri)
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20)); // margin kiri dan atas 20px
        topPanel.setBackground(Color.WHITE);
        add(topPanel, BorderLayout.NORTH);

        // Custom search field
        searchField = new JTextField(30) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 40, 40);
                super.paintComponent(g);
                g2.dispose();
            }

            @Override
            protected void paintBorder(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.LIGHT_GRAY);
                g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 40, 40);
                g2.dispose();
            }
        };
        searchField.setOpaque(false);
        searchField.setBorder(new EmptyBorder(10, 15, 10, 15));
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setToolTipText("Cari data...");
        topPanel.add(searchField);

        // Tabel
        DefaultTableModel model = new DefaultTableModel(
        new Object[][] {
        { "1", "Surat Kematian", "111", "Budi", "Malang, 01-06-2005", "02-06-2025", "" },
        { "2", "Surat Usaha", "222", "Charis", "Sidoarjo, 08-06-2005", "15-06-2025", "" },
        { "3", "Surat Domisili", "333", "Ani", "Malang, 02-06-2003", "16-06-2025", "" },
        { "4", "Surat Menikah", "444", "Tasya", "Malang, 03-06-2002", "21-06-2025", "" }
        },
            new Object[] { "No Surat", "Jenis Surat", "NIK", "Nama", "Tempat tgl Lahir", "Tgl Pengajuan", "Dokumen" }
        );

        table = new JTable(model);
        table.setFont(new Font("SansSerif", Font.PLAIN, 16));
        table.setRowHeight(24);
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 18));

        rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20)); // spacing tabel
        add(scrollPane, BorderLayout.CENTER);

        // Filter data saat ketik
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            @Override
            public void insertUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            @Override
            public void removeUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }

            @Override
            public void changedUpdate(javax.swing.event.DocumentEvent e) {
                filterTable();
            }
        });
    }

    private void filterTable() {
        String search = searchField.getText();
        if (search.trim().length() == 0) {
            rowSorter.setRowFilter(null);
        } else {
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + search));
        }
    }

    public void setTableModel(TableModel model) {
        table.setModel(model);
        rowSorter = new TableRowSorter<>(model);
        table.setRowSorter(rowSorter);
    }

    public JTable getTable() {
        return table;
    }

    public JTextField getSearchField() {
        return searchField;
    }
}
