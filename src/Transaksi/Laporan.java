/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Font;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import javax.swing.plaf.basic.BasicInternalFrameUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author ZAKKAA
 */
public class Laporan extends javax.swing.JInternalFrame {

    /**
     * Creates new form Laporan
     */
    public Laporan() {
        initComponents();
        removeDecoration();
        tampil_data();
        DefaultTableCellRenderer right = new DefaultTableCellRenderer();
        right.setHorizontalAlignment(JLabel.RIGHT);
        tbl_laporan.getColumnModel().getColumn(3).setCellRenderer(right);
        tbl_laporan.getColumnModel().getColumn(4).setCellRenderer(right);
        tbl_laporan.getColumnModel().getColumn(5).setCellRenderer(right);
        DefaultTableCellRenderer center = new DefaultTableCellRenderer();
        center.setHorizontalAlignment(JLabel.CENTER);
        tbl_laporan.getColumnModel().getColumn(0).setCellRenderer(center);
        tbl_laporan.getColumnModel().getColumn(1).setCellRenderer(center);
        tbl_laporan.getColumnModel().getColumn(2).setCellRenderer(center);


    }
    
    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }
    
    private void tampil_data()
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("NAMA KASIR");        
        model.addColumn("TANGGAL TRANSAKSI");
        model.addColumn("TOTAL HARGA");
        model.addColumn("TOTAL BAYAR");
        model.addColumn("KEMBALIAN");
        JTableHeader Theader = tbl_laporan.getTableHeader();
        Theader.setFont(new Font("Tahoma",Font.BOLD,14));
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM transaksi JOIN user USING(id_user)";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int harga = Integer.parseInt(res.getString("total_hrg"));
                double angka = (double)harga;
                String total_harga = String.format("%,.0f", angka).replaceAll(",", ".");
                
                int bayar = Integer.parseInt(res.getString("bayar"));
                double angka2 = (double)bayar;
                String total_bayar = String.format("%,.0f", angka2).replaceAll(",", ".");
                
                int kembalian = Integer.parseInt(res.getString("kembalian"));
                double angka3 = (double)kembalian;
                String total_kembalian = String.format("%,.0f", angka3).replaceAll(",", ".");
                
                Date ys = new Date();
                SimpleDateFormat s = new SimpleDateFormat("dd MMMM yyyy");
                String tanggal = s.format(ys);
                model.addRow(new Object[]{res.getString("id_transaksi"), 
                    res.getString("nm_user"), 
                    tanggal,
                    total_harga, 
                    total_bayar, 
                    total_kembalian});
            }
            tbl_laporan.setModel(model);
        } catch (SQLException e) {
        }
    }
    
    private void tampil_data_filter()
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Kode");
        model.addColumn("NAMA KASIR");        
        model.addColumn("TANGGAL TRANSAKSI");
        model.addColumn("TOTAL HARGA");
        model.addColumn("TOTAL BAYAR");
        model.addColumn("KEMBALIAN");
        JTableHeader Theader = tbl_laporan.getTableHeader();
        Theader.setFont(new Font("Tahoma",Font.BOLD,14));
        ((DefaultTableCellRenderer)Theader.getDefaultRenderer())
                .setHorizontalAlignment(JLabel.CENTER);

        //menampilkan data database kedalam tabel
        try {
            //int no=1;
            String sql = "SELECT * FROM transaksi JOIN user USING(id_user) WHERE tgl_transaksi ";
            java.sql.Connection conn = (com.mysql.jdbc.Connection) Koneksi.configDB();
            java.sql.Statement stm = conn.createStatement();
            java.sql.ResultSet res = stm.executeQuery(sql);
            while (res.next()) {
                int harga = Integer.parseInt(res.getString("total_hrg"));
                double angka = (double)harga;
                String total_harga = String.format("%,.0f", angka).replaceAll(",", ".");
                
                int bayar = Integer.parseInt(res.getString("bayar"));
                double angka2 = (double)bayar;
                String total_bayar = String.format("%,.0f", angka2).replaceAll(",", ".");
                
                int kembalian = Integer.parseInt(res.getString("kembalian"));
                double angka3 = (double)kembalian;
                String total_kembalian = String.format("%,.0f", angka3).replaceAll(",", ".");
                
                Date ys = new Date();
                SimpleDateFormat s = new SimpleDateFormat("dd MMMM yyyy");
                String tanggal = s.format(ys);
                model.addRow(new Object[]{res.getString("id_transaksi"), 
                    res.getString("nm_user"), 
                    tanggal,
                    total_harga, 
                    total_bayar, 
                    total_kembalian});
            }
            tbl_laporan.setModel(model);
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
        tbl_laporan = new javax.swing.JTable();
        btn_tampil = new javax.swing.JButton();
        akhir = new com.toedter.calendar.JDateChooser();
        awal = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        btn_refresh = new javax.swing.JButton();

        tbl_laporan.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_laporan);

        btn_tampil.setText("TAMPIL");
        btn_tampil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tampilActionPerformed(evt);
            }
        });

        jLabel1.setText("SAMPAI");

        btn_refresh.setText("Refresh");
        btn_refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_refreshActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_tampil, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1260, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(1072, Short.MAX_VALUE)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(176, 176, 176)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_tampil, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(akhir, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(awal, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(51, 51, 51)
                    .addComponent(btn_refresh, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(543, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_tampilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tampilActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btn_tampilActionPerformed

    private void btn_refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_refreshActionPerformed
        // TODO add your handling code here:
        tampil_data();

    }//GEN-LAST:event_btn_refreshActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser akhir;
    private com.toedter.calendar.JDateChooser awal;
    private javax.swing.JButton btn_refresh;
    private javax.swing.JButton btn_tampil;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_laporan;
    // End of variables declaration//GEN-END:variables
}
