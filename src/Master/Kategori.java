/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Master;

import Koneksi.Koneksi;
import com.mysql.jdbc.Connection;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author zakka
 */
public class Kategori extends javax.swing.JInternalFrame {

    /**
     * Creates new form Kategori
     */
    public Kategori() {
        initComponents();
        removeDecoration();
        tampil_data();
        kode();
        button_awal();
        reset_input();
        
        txt_baris.setVisible(false);
    }

    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }

    private void tampil_data() {
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("Nama Kategori");

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM kategori";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2)});
            }
            jTable1.setModel(model);
        } catch (SQLException e) {
        }
    }

    public void kode() {
        try {
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_kategori,2)) AS no FROM kategori";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                if (rs.first() == false) {
                    txt_kode.setText("K01");
                } else {
                    rs.last();
                    int set_id = rs.getInt(1) + 1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for (int a = 0; a < 2 - id_next; a++) {
                        no = "0" + no;
                    }
                    txt_kode.setText("K" + no);
                }
            }
        } catch (SQLException ex) {

        }
    }

    public void button_awal() {
        btn_simpan.setEnabled(true);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(false);
        btn_hapus.setEnabled(false);
    }

    private void reset_input() {
        txt_kategori.setText(null);
        txt_baris.setText(null);
    }

    public void button_tabelklik() {
        btn_simpan.setEnabled(false);
        btn_batal.setEnabled(true);
        btn_update.setEnabled(true);
        btn_hapus.setEnabled(true);

    }
    
    private void setTextData(){
        int baris = Integer.parseInt(txt_baris.getText());
        
        String id = jTable1.getValueAt(baris, 0).toString();
        txt_kode.setText(id);
        String nama = jTable1.getValueAt(baris, 1).toString();
        txt_kategori.setText(nama);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_cari = new javax.swing.JButton();
        txt_cari = new javax.swing.JTextField();
        txt_kode = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_kategori = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_hapus = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txt_baris = new javax.swing.JTextField();

        setPreferredSize(new java.awt.Dimension(1366, 670));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_cari.setText("Cari");
        btn_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariActionPerformed(evt);
            }
        });
        getContentPane().add(btn_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, -1));

        txt_cari.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 100, -1));

        txt_kode.setEditable(false);
        txt_kode.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        getContentPane().add(txt_kode, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 50, 180, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Kode");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 50, -1, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Kategori");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, -1, -1));

        txt_kategori.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txt_kategori.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_kategoriFocusGained(evt);
            }
        });
        txt_kategori.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_kategoriKeyTyped(evt);
            }
        });
        getContentPane().add(txt_kategori, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 80, 180, -1));

        btn_simpan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_simpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, -1, -1));

        btn_update.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_update.setText("UPDATE");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });
        getContentPane().add(btn_update, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 120, -1, -1));

        btn_hapus.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_hapus.setText("HAPUS");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });
        getContentPane().add(btn_hapus, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));

        btn_batal.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btn_batal.setText("BATAL");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });
        getContentPane().add(btn_batal, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 120, -1, -1));

        jTable1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 510, 560));

        txt_baris.setEditable(false);
        getContentPane().add(txt_baris, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 33, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if (txt_kategori.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String sql = "INSERT INTO kategori VALUES ('" + txt_kode.getText() + "','" + txt_kategori.getText() + "')";
            java.sql.Connection conn = (Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "Penyimpanan Data Berhasil");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        tampil_data();
        kode();
        reset_input();
        button_awal();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
        button_tabelklik();
        int baris = jTable1.rowAtPoint(evt.getPoint());
        txt_baris.setText(String.valueOf(baris));

        setTextData();
    }//GEN-LAST:event_jTable1MouseClicked

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed
        // TODO add your handling code here:
        if (txt_kategori.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Masukkan data dengan benar !", "Kesalahan", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            String sql = "UPDATE kategori SET nm_kategori = '" + txt_kategori.getText() + "' WHERE id_kategori = '" + txt_kode.getText() + "'";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.PreparedStatement pst = conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(null, "data berhasil di update");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, "Perubahan Data Gagal" + e.getMessage());
        }
        tampil_data();
        kode();
        reset_input();
        button_awal();
    }//GEN-LAST:event_btn_updateActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
        int konfirmasi = JOptionPane.showConfirmDialog(null, "Apakah yakin dihapus?", "Hapus", JOptionPane.YES_NO_OPTION);
        if (konfirmasi == 0) {
            try {
                String sql = "DELETE FROM kategori WHERE id_kategori='" + txt_kode.getText() + "'";
                java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
                java.sql.PreparedStatement pst = conn.prepareStatement(sql);
                pst.execute();
                JOptionPane.showMessageDialog(this, "Berhasil di hapus");
            } catch (HeadlessException | SQLException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            tampil_data();
            kode();
            reset_input();
            button_awal();
        }
        else
        {
            tampil_data();
            kode();
            reset_input();
            button_awal();
        }
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        tampil_data();
        kode();
        reset_input();
        button_awal();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void btn_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode");
            model.addColumn("Nama Kategori");

            String cari = txt_cari.getText();
            String sql = "SELECT * FROM kategori WHERE id_kategori LIKE '%" + cari + "%' OR nm_kategori LIKE '%" + cari + "%' ORDER BY id_kategori";
            java.sql.Connection conn = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2)});
            }
            jTable1.setModel(model);
            txt_cari.setText(null);
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_btn_cariActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
        int row = this.jTable1.getSelectedRow();
        this.txt_baris.setText(String.valueOf(row));
        setTextData();
    }//GEN-LAST:event_jTable1KeyReleased

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode");
            model.addColumn("Nama Kategori");

            String cari = txt_cari.getText();
            String sql = "SELECT * FROM kategori WHERE id_kategori LIKE '%" + cari + "%' OR nm_kategori LIKE '%" + cari + "%' ORDER BY id_kategori";
            java.sql.Connection conn = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(2)});
            }
            jTable1.setModel(model);
            txt_cari.setText(null);
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_txt_cariActionPerformed

    private void txt_kategoriFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_kategoriFocusGained
        // TODO add your handling code here:
        txt_kategori.setText("");
    }//GEN-LAST:event_txt_kategoriFocusGained

    private void txt_kategoriKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_kategoriKeyTyped
        // TODO add your handling code here:
        char Test = evt.getKeyChar();
        if(!(Character.isAlphabetic(Test)))
            evt.consume();
    }//GEN-LAST:event_txt_kategoriKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_cari;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txt_baris;
    private javax.swing.JTextField txt_cari;
    private javax.swing.JTextField txt_kategori;
    private javax.swing.JTextField txt_kode;
    // End of variables declaration//GEN-END:variables
}
