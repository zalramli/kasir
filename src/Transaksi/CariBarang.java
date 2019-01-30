/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import Login.SetGet;
import java.awt.Component;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class CariBarang extends javax.swing.JFrame {

    /**
     * Creates new form CariBarang
     */
    public CariBarang() {
        initComponents();
        tampil_data();
        this.setLocationRelativeTo(null);//membuat tampilan di tengah
    }

    private void tampil_data() {
        // membuat tampilan model tabel
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("Nama");
        model.addColumn("Kategori");
        model.addColumn("Jenis Satuan");
        model.addColumn("Jumlah Stok");
        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM barang b ,kategori k , satuan s where b.id_kategori = k.id_kategori && b.id_satuan = s.id_satuan ORDER BY nm_barang ASC";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(4), res.getString("k.nm_kategori"), res.getString("s.nm_satuan"), res.getString(5)});
            }
            tbl_barang.setModel(model);
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_barang = new javax.swing.JTable();
        txt_cari = new javax.swing.JTextField();
        btn_pilih = new javax.swing.JButton();
        kode_barang = new javax.swing.JLabel();
        nama_barang = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setType(java.awt.Window.Type.POPUP);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_barang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_barang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_barangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_barang);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 69, 830, 380));

        txt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cariActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cari, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 30, 136, 23));

        btn_pilih.setText("PILIH");
        btn_pilih.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pilihActionPerformed(evt);
            }
        });
        getContentPane().add(btn_pilih, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 30, -1, -1));

        kode_barang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        kode_barang.setText("kd_barang");
        getContentPane().add(kode_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 10, -1, -1));

        nama_barang.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        nama_barang.setText("nama");
        getContentPane().add(nama_barang, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setText("KODE BARANG  : ");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setText("NAMA BARANG :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cariActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("Kode");
            model.addColumn("Nama");
            model.addColumn("Kategori");
            model.addColumn("Jenis Satuan");
            model.addColumn("Jumlah Stok");

            String cari = txt_cari.getText();
            String sql = "SELECT * FROM barang b JOIN kategori k ON b.id_kategori=k.id_kategori JOIN satuan s ON b.id_satuan = s.id_satuan "
                    + "WHERE b.id_barang LIKE '%" + cari + "%' OR b.nm_barang LIKE '%" + cari + "%' OR k.nm_kategori LIKE '%" + cari
                    + "%' OR b.jml_stok LIKE '%" + cari + "%' OR s.nm_satuan LIKE '%" + cari + "%' ORDER BY b.nm_barang ASC";
            java.sql.Connection conn = (java.sql.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                model.addRow(new Object[]{res.getString(1), res.getString(4), res.getString("k.nm_kategori"), res.getString("s.nm_satuan"), res.getString(5)});
            }
            tbl_barang.setModel(model);
            txt_cari.setText(null);
        } catch (Exception ex) {
            Component rootPane = null;
            JOptionPane.showMessageDialog(rootPane, "Data yang dicari tidak ada !!!!");

        }
    }//GEN-LAST:event_txt_cariActionPerformed

    private void tbl_barangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_barangMouseClicked
        // TODO add your handling code here:
        int baris = tbl_barang.rowAtPoint(evt.getPoint());
        String id = tbl_barang.getValueAt(baris, 0).toString();
        kode_barang.setText(id);
        String nama = tbl_barang.getValueAt(baris, 1).toString();
        nama_barang.setText(nama);
    }//GEN-LAST:event_tbl_barangMouseClicked

    private void btn_pilihActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_pilihActionPerformed
        // TODO add your handling code here:
        String a = kode_barang.getText();
        SetGet.setId_barang(a);
        Pemasokan.barcode.setText(a);
//        new DashboardKasir().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_pilihActionPerformed

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
            java.util.logging.Logger.getLogger(CariBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CariBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CariBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CariBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CariBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_pilih;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel kode_barang;
    private javax.swing.JLabel nama_barang;
    private javax.swing.JTable tbl_barang;
    private javax.swing.JTextField txt_cari;
    // End of variables declaration//GEN-END:variables
}
