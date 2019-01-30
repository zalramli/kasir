/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;

import Transaksi.CariBarang;
import Koneksi.Koneksi;
import Login.SetGet;
import Master.Distributor;
import Transaksi.Transaksi;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.TableColumn;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

/**
 *
 * @author Lenovo
 */
public class DashboardKasir extends javax.swing.JFrame {

    /**
     * Creates new form DashboardKasir
     */
    boolean maximized = true;

    DefaultTableModel list_produk = new DefaultTableModel(
            new Object[][]{},
            new String[]{"KODE BARANG", "NAMA BARANG", "SATUAN", "QTY", "HARGA", "TOTAL", "jml_stok"
            }) // BIAR FIELD TABEL TIDAK BISA EDIT
    {
        boolean[] tdk_bisa_edit = new boolean[]{
            false, false, false, true, false, false, false
        };

        public boolean isCellEditable(int row, int column) {
            return tdk_bisa_edit[column];
        }
    };

    public DashboardKasir() {
        initComponents();
        fullscreen();
        tgl_sekarang();
        nonaktif();
        kode();
        kosongkan();
        hidden();
        String id = SetGet.getId_user();
        id_users.setText(id);
        String namaa = SetGet.getNama();
        nama.setText(namaa);
        String kd_barang = SetGet.getId_barang();
        barcode.setText(kd_barang);
        nama_barang.setText(" ");
        txt_total.setText("0");
        txt_kembalian.setText("0");

        // MENGATUR FORMAT FONT TABLE
        JTableHeader Theader = daftar_produk.getTableHeader();
        Theader.setFont(new Font("Tahoma", Font.BOLD, 14));
        ((DefaultTableCellRenderer) Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

    }

    private void fullscreen() {
        if (maximized) {
            DashboardKasir.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            DashboardKasir.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }

    private void kosongkan() {
        txt_nama.setText(null);
        txt_hrg.setText(null);
        nama_satuan.setText(null);
        txt_jml_stok.setText(null);
        txt_kode.setText(null);
        txt_qty.setText(null);
        txt_baris.setText(null);
        txt_bayar.setText(null);
        txt_total.setText("0");
        txt_kembalian.setText("0");
        DefaultTableModel model = (DefaultTableModel) daftar_produk.getModel();
        model.setRowCount(0);

    }

    public void kode() {
        try {
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_transaksi,9)) AS no FROM transaksi";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                if (rs.first() == false) {
                    txt_id_transaksi.setText("T000000001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1) + 1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for (int a = 0; a < 9 - id_next; a++) {
                        no = "0" + no;
                    }
                    txt_id_transaksi.setText("T" + no);
                }
            }
        } catch (SQLException ex) {

        }
    }

    private void nonaktif() {
        btn_hapus.setEnabled(false);
        btn_batal.setEnabled(false);
    }

    private void tgl_sekarang() {
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        txt_tanggal.setText(s.format(ys));
    }

    private void hidden() {
        txt_nama.setVisible(false);
        txt_hrg.setVisible(false);
        nama_satuan.setVisible(false);
        txt_jml_stok.setVisible(false);
        txt_kode.setVisible(false);
        txt_baris.setVisible(false);
        txt_qty.setVisible(false);
        txt_id_transaksi.setVisible(false);
        id_users.setVisible(false);
    }

    private void inisialisasi_tabel() {
        daftar_produk.setModel(list_produk);
        TableColumn col1 = daftar_produk.getColumnModel().getColumn(6);
        col1.setMinWidth(0);
        col1.setMaxWidth(0);
        col1.setWidth(0);
        col1.setPreferredWidth(0);
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.RIGHT);
        daftar_produk.getColumnModel().getColumn(4).setCellRenderer(right);
        daftar_produk.getColumnModel().getColumn(5).setCellRenderer(right);
    }

    private void simpan_ditabel() {    //SIMPAN SEMENTARA
        try {
            //JIKA INTEGER

            //JIKA STRING
            String id_barang = String.valueOf(barcode.getText());
            String nama = String.valueOf(txt_nama.getText());
            int harga_jual = Integer.parseInt(txt_hrg.getText());
            String satuan = String.valueOf(nama_satuan.getText());

            int qty = 1;
            int total = Integer.parseInt(txt_hrg.getText());
            int jml_stok = Integer.parseInt(txt_jml_stok.getText());

            inisialisasi_tabel();
            list_produk.addRow(new Object[]{id_barang, nama, satuan, qty, harga_jual, total, jml_stok});

        } catch (NumberFormatException exception) {
            System.out.println("Error ss : " + exception);
        }

    }

    public void getsum() {  //MENJUMLAHKAN HARGA DATA
        int batas = daftar_produk.getRowCount();
        int sum = 0;
        for (int i = 0; i < batas; i++) {
            sum = sum + Integer.parseInt(daftar_produk.getValueAt(i, 5).toString());
        }
        double angka = (double) sum;
        String mataUang = String.format("%,.0f", angka).replaceAll(",", ".");
        txt_total.setText(mataUang);

    }

    public void getKembalian() {
        if (txt_total.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan Barang !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            txt_bayar.setText("");
        } else if (txt_bayar.getText().equals("")) {
            txt_kembalian.setText("0");
        } else {
            // MENGUBAH DARI FORMAT RUPIAH KE NUMBER
            String total = txt_total.getText();
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();

            formatRp.setCurrencySymbol("");
            formatRp.setMonetaryDecimalSeparator(' ');
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);
            try {
                Number number = kursIndonesia.parse(total);
                double nilai = number.doubleValue();
                int bayar = Integer.parseInt(txt_bayar.getText());
                double kembalian = bayar - nilai;
                String kembalians = String.format("%,.0f", kembalian).replaceAll(",", ".");
                txt_kembalian.setText(kembalians);

            } catch (ParseException ex) {
                System.out.println("Kesalahan Parsing");
            }

        }
    }

    public void updateHarga() {

        int baris = Integer.parseInt(txt_baris.getText());
        String kode = daftar_produk.getValueAt(baris, 0).toString();
        txt_kode.setText(kode);
        String qty = daftar_produk.getValueAt(baris, 3).toString();
        txt_qty.setText(qty);
        String harga = daftar_produk.getValueAt(baris, 4).toString();
        txt_hrg.setText(harga);

        int total = (Integer.parseInt(qty)) * (Integer.parseInt(harga));
        list_produk.setValueAt(total, baris, 5);

        getsum();
        if (txt_bayar.getText().length() > 0) {
            getKembalian();
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        nama = new javax.swing.JLabel();
        txt_nama = new javax.swing.JTextField();
        txt_hrg = new javax.swing.JTextField();
        nama_satuan = new javax.swing.JTextField();
        txt_jml_stok = new javax.swing.JTextField();
        txt_kode = new javax.swing.JTextField();
        txt_baris = new javax.swing.JTextField();
        txt_qty = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        barcode = new javax.swing.JTextField();
        id_users = new javax.swing.JLabel();
        btn_cariBarang = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        txt_id_transaksi = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        daftar_produk = new javax.swing.JTable();
        btn_simpan = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JLabel();
        txt_total = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JLabel();
        nama_barang = new javax.swing.JLabel();
        judul_aplikasi = new javax.swing.JLabel();
        btn_batal = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setPreferredSize(new java.awt.Dimension(1366, 783));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("KASIR :");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, -1, -1));

        nama.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        nama.setText("nama_user");
        getContentPane().add(nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, -1, -1));
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 611, -1, -1));
        getContentPane().add(txt_hrg, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 611, -1, -1));
        getContentPane().add(nama_satuan, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 611, -1, -1));
        getContentPane().add(txt_jml_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(59, 611, -1, -1));
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(71, 611, 8, -1));
        getContentPane().add(txt_baris, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 611, -1, -1));
        getContentPane().add(txt_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(101, 611, -1, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel4.setText("KODE BARANG :");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 113, -1, -1));

        barcode.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeKeyPressed(evt);
            }
        });
        getContentPane().add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(194, 107, 167, 33));

        id_users.setText("id_user");
        getContentPane().add(id_users, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 614, 51, -1));

        btn_cariBarang.setText("Cari Barang");
        btn_cariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariBarangActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cariBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 109, -1, 33));

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(474, 109, -1, 33));

        txt_id_transaksi.setText("ID TRANSAKSI");
        getContentPane().add(txt_id_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 614, 142, -1));

        daftar_produk.setBackground(new java.awt.Color(214, 217, 223));
        daftar_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KODE BARANG", "NAMA BARANG", "SATUAN", "QTY", "HARGA", "TOTAL"
            }
        ));
        daftar_produk.setAlignmentX(5.0F);
        daftar_produk.setAlignmentY(5.0F);
        daftar_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftar_produkMouseClicked(evt);
            }
        });
        daftar_produk.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                daftar_produkKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(daftar_produk);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 153, 1324, 452));

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(1226, 654, 117, 37));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel5.setText("BAYAR");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(984, 656, -1, -1));

        txt_bayar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayarKeyTyped(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(1072, 652, 117, 37));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel6.setText("TOTAL : ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 117, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        jLabel7.setText("KEMBALI : ");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1077, 117, -1, -1));

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_tanggal.setText("Tanggal");
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(996, 11, 360, 31));

        txt_total.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txt_total.setText("total");
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(1002, 117, -1, -1));

        txt_kembalian.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txt_kembalian.setText("Kembalian");
        getContentPane().add(txt_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1217, 117, -1, -1));

        nama_barang.setFont(new java.awt.Font("Tahoma", 1, 48)); // NOI18N
        nama_barang.setText("jLabel2");
        getContentPane().add(nama_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(28, 11, -1, -1));

        judul_aplikasi.setFont(new java.awt.Font("Gill Sans Ultra Bold", 0, 36)); // NOI18N
        judul_aplikasi.setForeground(new java.awt.Color(153, 153, 255));
        judul_aplikasi.setText("SUMBER REJEKI");
        getContentPane().add(judul_aplikasi, new org.netbeans.lib.awtextra.AbsoluteConstraints(454, 646, 390, 60));

        btn_batal.setText("Batal");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(543, 109, -1, 33));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeKeyPressed
        // TODO add your handling code here:

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) //JIKA ADA BARCODE OTOMATIS IKI!!
        {
            // Mengambil nama,harga,dll
            // di hidden texfield e

            String id_barang = barcode.getText();
            try {
                //int no=1;
                String sql = "SELECT * FROM barang JOIN satuan USING(id_satuan) WHERE id_barang='" + id_barang + "'";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    String nama = res.getString("nm_barang");
                    String harga_jual = res.getString("hrg_jual");
                    String satuan = res.getString("nm_satuan");
                    String isi_pack = res.getString("hrg_beli");
                    String jml_stok = res.getString("jml_stok");

                    txt_nama.setText(nama);
                    nama_barang.setText(nama);
                    txt_hrg.setText(harga_jual);
                    nama_satuan.setText(satuan);
                    txt_jml_stok.setText(jml_stok);
                    txt_kode.setText(id_barang);
                    txt_baris.setText(String.valueOf(list_produk.getRowCount()));
                    txt_qty.setText("1");

                }

            } catch (SQLException e) {
            }

            // Simpan nang tabel
            try {
                // TODO add your handling code here:
                com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
                Statement stat = c.createStatement();
                String sql2 = "SELECT * FROM barang JOIN satuan USING(id_satuan) WHERE id_barang='" + id_barang + "'";
                ResultSet rs = stat.executeQuery(sql2);
                if (barcode.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Masukkan kode !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } else if (rs.next() == false) {
                    JOptionPane.showMessageDialog(null, "Kode barang tidak ada !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                    return;
                } else {
                    simpan_ditabel();
                    getsum();
                    barcode.setText("");
                    getKembalian();

                }
            } catch (SQLException ex) {
                Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
            }
            btn_batal.setEnabled(true);
        }
    }//GEN-LAST:event_barcodeKeyPressed

    private void btn_cariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariBarangActionPerformed
        // TODO add your handling code here:
        new CariBarang().setVisible(true);
    }//GEN-LAST:event_btn_cariBarangActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        list_produk.removeRow(daftar_produk.getSelectedRow());
        btn_hapus.setEnabled(false);
        getsum();
        txt_bayar.setText("");
        txt_kembalian.setText("0");
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void daftar_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftar_produkMouseClicked
        // TODO add your handling code here:
        btn_hapus.setEnabled(true);

        int baris = daftar_produk.rowAtPoint(evt.getPoint());
        txt_baris.setText(String.valueOf(baris));
        updateHarga();
    }//GEN-LAST:event_daftar_produkMouseClicked

    private void daftar_produkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_daftar_produkKeyReleased
        // TODO add your handling code here:
        int row = this.daftar_produk.getSelectedRow();
        this.txt_baris.setText(String.valueOf(row));

        updateHarga();
    }//GEN-LAST:event_daftar_produkKeyReleased

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if (txt_bayar.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukan pembayaran", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        } else {
            try {
                Date skrg = new Date();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                String tgl_transaksi = format.format(skrg);
                String id_user = id_users.getText();

                String totals = txt_total.getText();
                DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
                DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
                formatRp.setCurrencySymbol("");
                formatRp.setMonetaryDecimalSeparator(' ');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                Number number = kursIndonesia.parse(totals);
                double total = number.doubleValue();
                int total_harga = (int) total;

                String kembalians = txt_kembalian.getText();
                formatRp.setCurrencySymbol("");
                formatRp.setMonetaryDecimalSeparator(' ');
                formatRp.setGroupingSeparator('.');
                kursIndonesia.setDecimalFormatSymbols(formatRp);
                Number numbers = kursIndonesia.parse(kembalians);
                double kembalian = numbers.doubleValue();
                int total_kembalian = (int) kembalian;

                if (total_kembalian < 0) {
                    JOptionPane.showMessageDialog(null, "Kembalian tidak boleh minus", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Insert Transaksi
                    String sql_transaksi = "INSERT INTO transaksi VALUES ('" + txt_id_transaksi.getText() + "','" + id_user + "','" + total_harga + "','" + txt_bayar.getText() + "','" + total_kembalian + "','" + tgl_transaksi + "')";
                    java.sql.Connection conn = (Connection) Koneksi.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql_transaksi);
                    pst.execute();

                    // Insert Detail Transaksi
                    int row = daftar_produk.getRowCount();
                    for (int i = 0; i < row; i++) {
                        String id_barang = daftar_produk.getValueAt(i, 0).toString();
                        int qty = Integer.parseInt(daftar_produk.getValueAt(i, 3).toString());
                        int total_hrg = Integer.parseInt(daftar_produk.getValueAt(i, 5).toString());
                        int stok = Integer.parseInt(daftar_produk.getValueAt(i, 6).toString());

                        int akhir_stok = stok - qty;

                        String sql_detail_transaksi = "insert into detail_transaksi (id_transaksi,id_barang,qty,total_hrg) values('" + txt_id_transaksi.getText() + "','" + id_barang + "','" + qty + "','" + total_hrg + "')";
                        java.sql.PreparedStatement pst2 = conn.prepareStatement(sql_detail_transaksi);
                        pst2.execute();

                        String sql_update_stok = "UPDATE barang SET jml_stok = '" + akhir_stok + "' WHERE id_barang = '" + id_barang + "'";
                        java.sql.PreparedStatement pst3 = conn.prepareStatement(sql_update_stok);
                        pst3.execute();

                    }
                    JOptionPane.showMessageDialog(null, "Transaksi Berhasil!");
                    hidden();
                    tgl_sekarang();
                    nonaktif();
                    kode();
                    kosongkan();
                    nama_barang.setText(" ");
                }

            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            } catch (ParseException ex) {
                Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_bayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyReleased
        // TODO add your handling code here:
        int max = 9;
        int len = txt_bayar.getText().length();
        if (len > max) {
            JOptionPane.showMessageDialog(null, "Maximal 9 digit !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            txt_bayar.setText("");
        }
        getKembalian();
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void txt_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyTyped
        // TODO add your handling code here:
        char Test = evt.getKeyChar();
        if (!(Character.isDigit(Test))) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_bayarKeyTyped

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        kosongkan();
        btn_batal.setEnabled(false);
        btn_hapus.setEnabled(false);
        nama_barang.setText(" ");
    }//GEN-LAST:event_btn_batalActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardKasir.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardKasir().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cariBarang;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JTable daftar_produk;
    private javax.swing.JLabel id_users;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel judul_aplikasi;
    private javax.swing.JLabel nama;
    private javax.swing.JLabel nama_barang;
    private javax.swing.JTextField nama_satuan;
    private javax.swing.JTextField txt_baris;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_hrg;
    private javax.swing.JLabel txt_id_transaksi;
    private javax.swing.JTextField txt_jml_stok;
    private javax.swing.JLabel txt_kembalian;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JLabel txt_total;
    // End of variables declaration//GEN-END:variables
}
