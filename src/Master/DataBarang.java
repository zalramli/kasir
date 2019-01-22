/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import Master.*;
import Koneksi.Koneksi;
import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ZAKKAA
 */
public class DataBarang extends javax.swing.JInternalFrame {

    Kategori var_kategori;

    /**
     * Creates new form Barang
     */
    public DataBarang() {
        initComponents();
        removeDecoration();
        tampil_data();
        button_awal();
        ambil_kategori();
    }

    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }

    public void button_awal() {
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(false);
        btn_hapus.setEnabled(false);

    }

    public void button_tabelklik() {
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(true);
        btn_hapus.setEnabled(true);

    }

    private void reset_input() {
        txt_kode.setText(null);
        txt_nama.setText(null);
        cb_kategori.setSelectedIndex(0);
        txt_stok.setText(null);
        txt_isi_pack.setText(null);
        txt_hrg_grosir.setText(null);
        txt_hrg_eceran.setText(null);
        txt_hrg_beli.setText(null);
        txt_cari.setText(null);
    }

    private void tampil_data() {
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("Nama");
        model.addColumn("Kategori");
        model.addColumn("Jumlah Stok");
        model.addColumn("Isi Pack");
        model.addColumn("Harga Grosir");
        model.addColumn("Harga Eceran");
        model.addColumn("Harga Beli");

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM barang b,kategori k where b.id_kategori = k.id_kategori ORDER BY nm_barang ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(3), res.getString("k.nm_kategori"), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)});
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
        }
    }

    private void ambil_kategori() {
        try {
            //int no=1;
            String sql = "SELECT * FROM kategori ORDER BY id_kategori ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String nama = res.getString("nm_kategori");
                cb_kategori.addItem(nama);
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

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_kode = new javax.swing.JTextField();
        txt_nama = new javax.swing.JTextField();
        txt_stok = new javax.swing.JTextField();
        txt_isi_pack = new javax.swing.JTextField();
        txt_hrg_grosir = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_hrg_eceran = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_hrg_beli = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        btn_cari = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        cb_kategori = new javax.swing.JComboBox<>();
        id_kategori = new javax.swing.JLabel();

        jLabel1.setText("MENU BARANG");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        txt_stok.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_stokKeyTyped(evt);
            }
        });

        txt_isi_pack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_isi_packKeyTyped(evt);
            }
        });

        jLabel2.setText("Kode");

        jLabel3.setText("Nama ");

        jLabel4.setText("Stok");

        jLabel5.setText("Isi Pack");

        jLabel6.setText("Harga Grosir");

        jLabel7.setText("Harga Eceran");

        jLabel8.setText("Harga Beli");

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });

        jLabel9.setText("Kategori");

        cb_kategori.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_kategoriActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_cari, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_simpan)
                                .addGap(18, 18, 18)
                                .addComponent(btn_update)
                                .addGap(18, 18, 18)
                                .addComponent(btn_hapus)
                                .addGap(18, 18, 18)
                                .addComponent(btn_batal))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel9))
                                .addGap(57, 57, 57)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_kode, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                                    .addComponent(txt_nama)
                                    .addComponent(txt_stok)
                                    .addComponent(txt_hrg_eceran)
                                    .addComponent(txt_hrg_beli)
                                    .addComponent(txt_isi_pack)
                                    .addComponent(txt_hrg_grosir)
                                    .addComponent(cb_kategori, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(id_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(370, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_cari, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_cari))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_kode, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addGap(16, 16, 16)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(cb_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(id_kategori, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_stok, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_isi_pack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_hrg_grosir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txt_hrg_eceran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(txt_hrg_beli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_simpan)
                            .addComponent(btn_update)
                            .addComponent(btn_hapus)
                            .addComponent(btn_batal))))
                .addContainerGap(135, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        String kode_barang = txt_kode.getText();
        try {
            // TODO add your handling code here:
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql2 = "SELECT * FROM barang WHERE id_barang='" + kode_barang + "'";
            ResultSet rs = stat.executeQuery(sql2);
            if (txt_kode.getText().equals("") || txt_nama.getText().equals("") || txt_stok.getText().equals("") || txt_isi_pack.getText().equals("") || txt_hrg_grosir.getText().equals("") || txt_hrg_eceran.getText().equals("") || txt_hrg_beli.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            } else if (rs.next() == true) {
                JOptionPane.showMessageDialog(null, "Data Barang Sudah ada !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                try {
                    String sql = "INSERT INTO barang VALUES ('" + kode_barang + "','" + id_kategori.getText() + "','" + txt_nama.getText() + "','" + txt_stok.getText() + "','" + txt_isi_pack.getText() + "','" + txt_hrg_grosir.getText() + "','" + txt_hrg_eceran.getText() + "','" + txt_hrg_beli.getText() + "')";
                    java.sql.Connection conn = (Connection) Koneksi.configDB();
                    java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                    pst.execute();
                    JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
                } catch (HeadlessException | SQLException e) {
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
            }
            tampil_data();
            reset_input();
            button_awal();
            txt_kode.setEditable(true);
        } catch (SQLException ex) {
            Logger.getLogger(Distributor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        button_tabelklik();
        txt_kode.setEditable(false);
        int baris = jTable1.rowAtPoint(evt.getPoint());
        String kode = jTable1.getValueAt(baris, 0).toString();
        txt_kode.setText(kode);
        String nama = jTable1.getValueAt(baris, 1).toString();
        txt_nama.setText(nama);
        String kategori = jTable1.getValueAt(baris, 2).toString();
        cb_kategori.setSelectedItem(kategori);
        String stok = jTable1.getValueAt(baris, 3).toString();
        txt_stok.setText(stok);
        String isi_pack = jTable1.getValueAt(baris, 4).toString();
        txt_isi_pack.setText(isi_pack);
        String hrg_grosir = jTable1.getValueAt(baris, 5).toString();
        txt_hrg_grosir.setText(hrg_grosir);
        String hrg_eceran = jTable1.getValueAt(baris, 6).toString();
        txt_hrg_eceran.setText(hrg_eceran);
        String hrg_beli = jTable1.getValueAt(baris, 7).toString();
        txt_hrg_beli.setText(hrg_beli);
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        tampil_data();
        reset_input();
        button_awal();
        txt_kode.setEditable(true);
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        try {
            String sql = "UPDATE barang SET id_kategori = '" + id_kategori.getText() + "', nm_barang = '" + txt_nama.getText() + "',jml_stok = '" + txt_stok.getText() + "',isi_pack = '" + txt_isi_pack.getText() + "',hrg_grosir = '" + txt_hrg_grosir.getText() + "',hrg_eceran = '" + txt_hrg_eceran.getText() + "',hrg_beli = '" + txt_hrg_beli.getText() + "' WHERE id_barang = '" + txt_kode.getText() + "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di update");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        tampil_data();
        reset_input();
        button_awal();
        txt_kode.setEditable(true);
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah yakin dihapus?", "Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == 0) {
            try {
                String sql = "DELETE FROM barang WHERE id_barang='" + txt_kode.getText() + "'";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Berhasil di hapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            tampil_data();
            reset_input();
            button_awal();
            txt_kode.setEditable(true);
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode");
            model.addColumn("Nama");
            model.addColumn("Kategori");
            model.addColumn("Jumlah Stok");
            model.addColumn("Isi Pack");
            model.addColumn("Harga Grosir");
            model.addColumn("Harga Eceran");
            model.addColumn("Harga Beli");

            String cari = txt_cari.getText();
            String sql = "SELECT * FROM barang b JOIN kategori k ON b.id_kategori=k.id_kategori "
                    + "WHERE b.id_barang LIKE '%" + cari + "%' OR b.nm_barang LIKE '%" + cari + "%' OR k.nm_kategori LIKE '%" + cari
                    + "%' OR b.jml_stok LIKE '%" + cari + "%' OR b.isi_pack LIKE '%" + cari + "%' OR b.hrg_grosir LIKE '%" + cari
                    + "%' OR b.hrg_eceran LIKE '%" + cari + "%' OR b.hrg_beli LIKE '%" + cari + "%' ORDER BY b.nm_barang ASC";
            java.sql.Connection conn = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(3), res.getString("k.nm_kategori"), res.getString(4), res.getString(5), res.getString(6), res.getString(7), res.getString(8)});
            }
            jTable1.setModel(model);
            txt_cari.setText(null);
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void txt_stokKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_stokKeyTyped
        // TODO add your handling code here:
        char Test = evt.getKeyChar();
        if (!(Character.isDigit(Test))) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_stokKeyTyped

    private void txt_isi_packKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_isi_packKeyTyped
        // TODO add your handling code here:
        char Test = evt.getKeyChar();
        if (!(Character.isDigit(Test))) {
            evt.consume();
        }
    }//GEN-LAST:event_txt_isi_packKeyTyped

    private void cb_kategoriActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_kategoriActionPerformed
        // TODO add your handling code here:
        String tampung = cb_kategori.getSelectedItem().toString();
        try {
            //int no=1;
            String sql = "SELECT * FROM kategori WHERE nm_kategori='" + tampung + "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                String id = res.getString("id_kategori");
                id_kategori.setText(id);
            }
        } catch (SQLException e) {
        }
    }//GEN-LAST:event_cb_kategoriActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JComboBox<String> cb_kategori;
    private javax.swing.JLabel id_kategori;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_hrg_beli;
    private javax.swing.JTextField txt_hrg_eceran;
    private javax.swing.JTextField txt_hrg_grosir;
    private javax.swing.JTextField txt_isi_pack;
    private javax.swing.JTextField txt_kode;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JTextField txt_stok;
    // End of variables declaration//GEN-END:variables
}
