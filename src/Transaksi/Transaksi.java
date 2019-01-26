/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import Login.SetGet;
import Master.Distributor;
import com.mysql.jdbc.Connection;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author ZAKKAA
 */
public class Transaksi extends javax.swing.JInternalFrame {

    /**
     * Creates new form transaksi
     */
    // Tabel sementara    
    DefaultTableModel list_produk = new DefaultTableModel(
            new Object[][]{},
            new String[]{"KODE BARANG", "NAMA BARANG", "PILIHAN", "QTY", "HARGA ECERAN", "HARGA GROSIR", "TOTAL", "isi_pack", "jml_stok"
            }) // BIAR FIELD TABEL TIDAK BISA EDIT
    {
        boolean[] tdk_bisa_edit = new boolean[]{
            false, false, true, true, false, false, false, false, false
        };

        public boolean isCellEditable(int row, int column) {
            return tdk_bisa_edit[column];
        }
    };

    public Transaksi() {
        initComponents();
        removeDecoration();
        hidden();
        tgl_sekarang();
        nonaktif();
        kode();
        kosongkan();
        String id = SetGet.getId_user();
        id_users.setText(id);
    }

    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }

    private void kosongkan() {
        txt_nama.setText(null);
        txt_hrg_eceran.setText(null);
        txt_hrg_grosir.setText(null);
        txt_isi_pack.setText(null);
        txt_jml_stok.setText(null);
        txt_kode.setText(null);
        txt_pilihan.setText(null);
        txt_qty.setText(null);
        txt_baris.setText(null);
        txt_bayar.setText(null);
        txt_total.setText(null);
        txt_kembalian.setText(null);
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
    }

    private void tgl_sekarang() {
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        txt_tanggal.setText(s.format(ys));
    }

    private void hidden() {
//        txt_nama.setVisible(false);
//        txt_hrg_eceran.setVisible(false);
//        txt_hrg_grosir.setVisible(false);
//        txt_isi_pack.setVisible(false);
//        txt_jml_stok.setVisible(false);
//        txt_kode.setVisible(false);
//        txt_pilihan.setVisible(false);
//        txt_baris.setVisible(false);
//        txt_qty.setVisible(false);
    }

    private void inisialisasi_tabel() {
        daftar_produk.setModel(list_produk);
        TableColumn col1 = daftar_produk.getColumnModel().getColumn(7);
        col1.setMinWidth(0);
        col1.setMaxWidth(0);
        col1.setWidth(0);
        col1.setPreferredWidth(0);
        TableColumn col2 = daftar_produk.getColumnModel().getColumn(8);
        col2.setMinWidth(0);
        col2.setMaxWidth(0);
        col2.setWidth(0);
        col2.setPreferredWidth(0);
    }

    private void simpan_ditabel() {    //SIMPAN SEMENTARA
        try {
            //JIKA INTEGER

            //JIKA STRING
            String id_barang = String.valueOf(barcode.getText());
            String nama = String.valueOf(txt_nama.getText());
            String default_pilihan = "Eceran";

            // Membuat Combobox
            JComboBox cb = new JComboBox();
            cb.addItem("Eceran");
            cb.addItem("Grosir");
            int harga_eceran = Integer.parseInt(txt_hrg_eceran.getText());
            int harga_grosir = Integer.parseInt(txt_hrg_grosir.getText());

            int qty = 1;
            int total = Integer.parseInt(txt_hrg_eceran.getText());
            int isi_pack = Integer.parseInt(txt_isi_pack.getText());
            int jml_stok = Integer.parseInt(txt_jml_stok.getText());

            inisialisasi_tabel();
            // kolom ke 4 => array(3);
            daftar_produk.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cb));
            list_produk.addRow(new Object[]{id_barang, nama, default_pilihan, qty, harga_eceran, harga_grosir, total, isi_pack, jml_stok});

        } catch (NumberFormatException exception) {
            System.out.println("Error ss : " + exception);
        }

    }

    public void getsum() {  //MENJUMLAHKAN HARGA DATA
        int batas = daftar_produk.getRowCount();
        int sum = 0;
        for (int i = 0; i < batas; i++) {
            sum = sum + Integer.parseInt(daftar_produk.getValueAt(i, 6).toString());
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
        String pilihan = daftar_produk.getValueAt(baris, 2).toString();
        txt_pilihan.setText(pilihan);
        String qty = daftar_produk.getValueAt(baris, 3).toString();
        txt_qty.setText(qty);
        String eceran = daftar_produk.getValueAt(baris, 4).toString();
        txt_hrg_eceran.setText(eceran);
        String grosir = daftar_produk.getValueAt(baris, 5).toString();
        txt_hrg_grosir.setText(grosir);

        if (pilihan == "Eceran") {
            int total = (Integer.parseInt(qty)) * (Integer.parseInt(eceran));
            list_produk.setValueAt(total, baris, 6);
        }
        if (pilihan == "Grosir") {
            int total = (Integer.parseInt(qty)) * (Integer.parseInt(grosir));
            list_produk.setValueAt(total, baris, 6);
        }

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
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jScrollPane1 = new javax.swing.JScrollPane();
        daftar_produk = new javax.swing.JTable();
        barcode = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_hrg_eceran = new javax.swing.JTextField();
        txt_total = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txt_tanggal = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        txt_kembalian = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_cariBarang = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        txt_id_transaksi = new javax.swing.JLabel();
        btn_simpan = new javax.swing.JButton();
        txt_hrg_grosir = new javax.swing.JTextField();
        txt_isi_pack = new javax.swing.JTextField();
        txt_jml_stok = new javax.swing.JTextField();
        txt_kode = new javax.swing.JTextField();
        txt_pilihan = new javax.swing.JTextField();
        txt_baris = new javax.swing.JTextField();
        txt_qty = new javax.swing.JTextField();
        id_users = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1366, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        daftar_produk.setBackground(new java.awt.Color(214, 217, 223));
        daftar_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KODE BARANG", "NAMA BARANG", "PILIHAN", "QTY", "HARGA ECERAN", "HARGA GROSIR", "TOTAL"
            }
        ));
        daftar_produk.setAlignmentX(5.0F);
        daftar_produk.setAlignmentY(5.0F);

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, daftar_produk, org.jdesktop.beansbinding.ELProperty.create("false"), daftar_produk, org.jdesktop.beansbinding.BeanProperty.create("showHorizontalLines"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, daftar_produk, org.jdesktop.beansbinding.ELProperty.create("false"), daftar_produk, org.jdesktop.beansbinding.BeanProperty.create("showVerticalLines"));
        bindingGroup.addBinding(binding);

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

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 94, 1314, 452));

        barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeKeyPressed(evt);
            }
        });
        getContentPane().add(barcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(551, 61, 167, 33));

        txt_nama.setEditable(false);
        getContentPane().add(txt_nama, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 67, 44, -1));

        txt_hrg_eceran.setEditable(false);
        getContentPane().add(txt_hrg_eceran, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 67, 42, -1));

        txt_total.setEditable(false);
        getContentPane().add(txt_total, new org.netbeans.lib.awtextra.AbsoluteConstraints(948, 557, 175, 37));

        jLabel1.setText("KODE BARANG");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(446, 70, -1, -1));

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_tanggal.setText("Tanggal");
        getContentPane().add(txt_tanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1103, 0, 244, 38));

        jLabel2.setText("Total");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(895, 568, -1, -1));

        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayarKeyTyped(evt);
            }
        });
        getContentPane().add(txt_bayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(746, 557, 117, 37));

        txt_kembalian.setEditable(false);
        getContentPane().add(txt_kembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1230, 557, 117, 37));

        jLabel3.setText("Bayar");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 568, -1, -1));

        jLabel4.setText("Kembalian");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1156, 568, -1, -1));

        btn_cariBarang.setText("Cari Barang");
        btn_cariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariBarangActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cariBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 61, -1, 33));

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(831, 61, 81, 33));

        txt_id_transaksi.setText("ID TRANSAKSI");
        getContentPane().add(txt_id_transaksi, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 7, 108, 31));

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(469, 557, 89, 37));

        txt_hrg_grosir.setEditable(false);
        getContentPane().add(txt_hrg_grosir, new org.netbeans.lib.awtextra.AbsoluteConstraints(155, 67, 41, -1));

        txt_isi_pack.setEditable(false);
        getContentPane().add(txt_isi_pack, new org.netbeans.lib.awtextra.AbsoluteConstraints(214, 67, 30, -1));

        txt_jml_stok.setEditable(false);
        getContentPane().add(txt_jml_stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 67, 27, -1));

        txt_kode.setEditable(false);
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(287, 67, 26, -1));

        txt_pilihan.setEditable(false);
        getContentPane().add(txt_pilihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(319, 67, 27, -1));

        txt_baris.setEditable(false);
        getContentPane().add(txt_baris, new org.netbeans.lib.awtextra.AbsoluteConstraints(352, 67, 26, -1));

        txt_qty.setEditable(false);
        getContentPane().add(txt_qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(384, 67, 25, -1));

        id_users.setText("jLabel5");
        getContentPane().add(id_users, new org.netbeans.lib.awtextra.AbsoluteConstraints(647, 29, 51, -1));

        bindingGroup.bind();

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
                String sql = "SELECT * FROM barang WHERE id_barang='" + id_barang + "'";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.Statement stm = conn.createStatement();
                java.sql.ResultSet res = stm.executeQuery(sql);
                while (res.next()) {
                    String nama = res.getString("nm_barang");
                    String harga_eceran = res.getString("hrg_eceran");
                    String harga_grosir = res.getString("hrg_grosir");
                    String isi_pack = res.getString("isi_pack");
                    String jml_stok = res.getString("jml_stok");

                    txt_nama.setText(nama);
                    txt_hrg_eceran.setText(harga_eceran);
                    txt_hrg_grosir.setText(harga_grosir);
                    txt_isi_pack.setText(isi_pack);
                    txt_jml_stok.setText(jml_stok);
                    txt_kode.setText(id_barang);
                    txt_pilihan.setText("Eceran");
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
                String sql2 = "SELECT * FROM barang WHERE id_barang='" + id_barang + "'";
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
        }
    }//GEN-LAST:event_barcodeKeyPressed

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

    private void btn_cariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariBarangActionPerformed
        // TODO add your handling code here:
        new CariBarang().setVisible(true);
    }//GEN-LAST:event_btn_cariBarangActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        if (txt_bayar.getText().length() > 0) {
            list_produk.removeRow(daftar_produk.getSelectedRow());
            btn_hapus.setEnabled(false);
            getsum();
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
        else{
            JOptionPane.showMessageDialog(null, "Isikan Bayar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_btn_hapusActionPerformed

    private void daftar_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftar_produkMouseClicked
        // TODO add your handling code here:
        btn_hapus.setEnabled(true);

        int baris = daftar_produk.rowAtPoint(evt.getPoint());
        txt_baris.setText(String.valueOf(baris));
        updateHarga();
    }//GEN-LAST:event_daftar_produkMouseClicked

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
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
                    String jenis_beli = daftar_produk.getValueAt(i, 2).toString();
                    int qty = Integer.parseInt(daftar_produk.getValueAt(i, 3).toString());
                    int total_hrg = Integer.parseInt(daftar_produk.getValueAt(i, 5).toString());
                    int isi_pack = Integer.parseInt(daftar_produk.getValueAt(i, 7).toString());
                    int stok = Integer.parseInt(daftar_produk.getValueAt(i, 8).toString());
                    if (jenis_beli == "Grosir") {
                        int jumlah = qty * isi_pack;
                        int akhir_stok = stok - jumlah;

                        String sql_detail_transaksi = "insert into detail_transaksi (id_transaksi,id_barang,jenis_beli,qty,total_hrg) values('" + txt_id_transaksi.getText() + "','" + id_barang + "','" + jenis_beli + "','" + qty + "','" + total_hrg + "')";
                        java.sql.PreparedStatement pst2 = conn.prepareStatement(sql_detail_transaksi);
                        pst2.execute();

                        String sql_update_stok = "UPDATE barang SET jml_stok = '" + akhir_stok + "' WHERE id_barang = '" + id_barang + "'";
                        java.sql.PreparedStatement pst3 = conn.prepareStatement(sql_update_stok);
                        pst3.execute();
                    } else {
                        int akhir_stok = stok - qty;

                        String sql_detail_transaksi = "insert into detail_transaksi (id_transaksi,id_barang,jenis_beli,qty,total_hrg) values('" + txt_id_transaksi.getText() + "','" + id_barang + "','" + jenis_beli + "','" + qty + "','" + total_hrg + "')";
                        java.sql.PreparedStatement pst2 = conn.prepareStatement(sql_detail_transaksi);
                        pst2.execute();

                        String sql_update_stok = "UPDATE barang SET jml_stok = '" + akhir_stok + "' WHERE id_barang = '" + id_barang + "'";
                        java.sql.PreparedStatement pst3 = conn.prepareStatement(sql_update_stok);
                        pst3.execute();
                    }

                }
                JOptionPane.showMessageDialog(null, "Transaksi Berhasil!");
                hidden();
                tgl_sekarang();
                nonaktif();
                kode();
                kosongkan();
            }

        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void txt_bayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_bayarKeyTyped
        // TODO add your handling code here:
        char Test = evt.getKeyChar();
        if (!(Character.isDigit(Test))) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_bayarKeyTyped

    private void daftar_produkKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_daftar_produkKeyReleased
        // TODO add your handling code here:
        int row = this.daftar_produk.getSelectedRow();
        this.txt_baris.setText(String.valueOf(row));

        updateHarga();
    }//GEN-LAST:event_daftar_produkKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private javax.swing.JButton btn_cariBarang;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JTable daftar_produk;
    private javax.swing.JLabel id_users;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_baris;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_hrg_eceran;
    private javax.swing.JTextField txt_hrg_grosir;
    private javax.swing.JLabel txt_id_transaksi;
    private javax.swing.JTextField txt_isi_pack;
    private javax.swing.JTextField txt_jml_stok;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_pilihan;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JTextField txt_total;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
