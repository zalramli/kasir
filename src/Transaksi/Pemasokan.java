/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import Master.Distributor;
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
public class Pemasokan extends javax.swing.JInternalFrame {

    /**
     * Creates new form pemasokan
     */
    // Tabel sementara    
    DefaultTableModel list_produk = new DefaultTableModel(
            new Object[][]{},
            new String[]{"KODE BARANG", "NAMA BARANG", "HARGA DISTRIBUTOR", "JUMLAH PEMASOKAN (QTY)", "TOTAL", "isi_pack", "jml_stok"
            }) // BIAR FIELD TABEL TIDAK BISA EDIT
    {
        boolean[] tdk_bisa_edit = new boolean[]{
            false, false, false, true, false, false, false
        };

        public boolean isCellEditable(int row, int column) {
            return tdk_bisa_edit[column];
        }
    };

    public Pemasokan() {
        initComponents();
        removeDecoration();
        tgl_sekarang();
        kode();
        nonaktif();
        ambil_distributor();
    }

    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }

    private void tgl_sekarang() {
        Date ys = new Date();
        SimpleDateFormat s = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        txt_tanggal.setText(s.format(ys));
    }

    public void kode() {
        try {
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_pemasokan,9)) AS no FROM pemasokan";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                if (rs.first() == false) {
                    txt_id_pemasokan.setText("P0000001");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1) + 1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for (int a = 0; a < 7 - id_next; a++) {
                        no = "0" + no;
                    }
                    txt_id_pemasokan.setText("P" + no);
                }
            }
        } catch (SQLException ex) {

        }
    }

    private void nonaktif() {
        btn_hapus.setEnabled(false);
    }

    private void inisialisasi_tabel() {
        daftar_produk.setModel(list_produk);
        TableColumn col1 = daftar_produk.getColumnModel().getColumn(5);
        col1.setMinWidth(0);
        col1.setMaxWidth(0);
        col1.setWidth(0);
        col1.setPreferredWidth(0);
        TableColumn col2 = daftar_produk.getColumnModel().getColumn(6);
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

            int hrg_distributor = Integer.parseInt(txt_hrg_distributor.getText());
            int qty = 1;
            int total = Integer.parseInt(txt_hrg_distributor.getText());
            int isi_pack = Integer.parseInt(txt_isi_pack.getText());
            int jml_stok = Integer.parseInt(txt_jml_stok.getText());

            inisialisasi_tabel();
            // kolom ke 4 => array(3);
            list_produk.addRow(new Object[]{id_barang, nama, hrg_distributor, qty, total, isi_pack, jml_stok});

        } catch (NumberFormatException exception) {
            System.out.println("Error ss : " + exception);
        }

    }

    public void getsum() {  //MENJUMLAHKAN HARGA DATA
        int batas = daftar_produk.getRowCount();
        int sum = 0;
        for (int i = 0; i < batas; i++) {
            sum = sum + Integer.parseInt(daftar_produk.getValueAt(i, 4).toString());
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

    private void ambil_distributor() {
        try {
            //int no=1;
            String sql = "SELECT * FROM distributor ORDER BY id_distributor ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String nama = res.getString("nm_distributor");
                cb_distributor.addItem(nama);
            }
        } catch (SQLException e) {
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

        txt_id_pemasokan = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        daftar_produk = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txt_bayar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txt_total = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_kembalian = new javax.swing.JTextField();
        txt_tanggal = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        barcode = new javax.swing.JTextField();
        btn_cariBarang = new javax.swing.JButton();
        cb_distributor = new javax.swing.JComboBox<>();
        btn_simpan = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        txt_nama = new javax.swing.JTextField();
        txt_hrg_distributor = new javax.swing.JTextField();
        txt_isi_pack = new javax.swing.JTextField();
        txt_jml_stok = new javax.swing.JTextField();
        txt_kode = new javax.swing.JTextField();
        txt_baris = new javax.swing.JTextField();
        txt_qty = new javax.swing.JTextField();
        txt_id_distributor = new javax.swing.JLabel();

        txt_id_pemasokan.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_id_pemasokan.setText("Kode Pemasokan");

        daftar_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Barang", "Nama Barang", "Harga Distributor", "Jumlah Pemasokan (qty)", "Total"
            }
        ));
        jScrollPane1.setViewportView(daftar_produk);

        jLabel2.setText("Distributor");

        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_bayarKeyTyped(evt);
            }
        });

        jLabel3.setText("bayar");

        jLabel4.setText("total");

        jLabel5.setText("kembalian");

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_tanggal.setText("Tanggal Pemasokan");

        jLabel7.setText("Barcode");

        barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeKeyPressed(evt);
            }
        });

        btn_cariBarang.setText("Cari Barang");

        cb_distributor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_distributorActionPerformed(evt);
            }
        });

        btn_simpan.setText("Simpan");

        btn_hapus.setText("Hapus");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addGap(18, 18, 18)
                                .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_cariBarang)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btn_hapus)
                                .addGap(18, 18, 18)
                                .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4)
                                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_hrg_distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_isi_pack, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_jml_stok, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_baris, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(463, 463, 463)
                                .addComponent(txt_id_distributor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel2)
                                .addGap(155, 155, 155))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_id_pemasokan, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(10, 10, 10))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(cb_distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1327, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_simpan)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(27, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(2, 2, 2)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_id_pemasokan, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7)
                        .addComponent(btn_cariBarang)
                        .addComponent(jLabel2)
                        .addComponent(cb_distributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btn_hapus)
                        .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_hrg_distributor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_isi_pack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_jml_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_baris, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txt_id_distributor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(btn_simpan))
                .addContainerGap(59, Short.MAX_VALUE))
        );

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
                    String harga_distributor = res.getString("hrg_beli");
                    String isi_pack = res.getString("isi_pack");
                    String jml_stok = res.getString("jml_stok");

                    txt_nama.setText(nama);
                    txt_hrg_distributor.setText(harga_distributor);
                    txt_isi_pack.setText(isi_pack);
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

    private void cb_distributorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_distributorActionPerformed
        // TODO add your handling code here:
        String tampung = cb_distributor.getSelectedItem().toString();
        try {
            //int no=1;
            String sql = "SELECT * FROM distributor WHERE nm_distributor='" + tampung + "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String id = res.getString("id_distributor");
                txt_id_distributor.setText(id);
            }
        } catch (SQLException e) {
        }
    }//GEN-LAST:event_cb_distributorActionPerformed

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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private javax.swing.JButton btn_cariBarang;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JComboBox<String> cb_distributor;
    private javax.swing.JTable daftar_produk;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_baris;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_hrg_distributor;
    private javax.swing.JLabel txt_id_distributor;
    private javax.swing.JLabel txt_id_pemasokan;
    private javax.swing.JTextField txt_isi_pack;
    private javax.swing.JTextField txt_jml_stok;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_qty;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JTextField txt_total;
    // End of variables declaration//GEN-END:variables
}
