/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Transaksi;

import Koneksi.Koneksi;
import Master.Distributor;
import Transaksi.*;
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
    new Object [ ][ ] {},
    new String [ ] {
    "KODE BARANG", "NAMA BARANG","PILIHAN","QTY","HARGA","TOTAL"
    });
    
    public Transaksi() {
        initComponents();
        removeDecoration();
        hidden();
        tgl_sekarang();
        nonaktif();
        kode();
        
    }
    
    void removeDecoration() {
        for (MouseListener listener : ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().getMouseListeners()) {
            ((javax.swing.plaf.basic.BasicInternalFrameUI) this.getUI()).getNorthPane().removeMouseListener(listener);
        }
        BasicInternalFrameTitlePane titlePane = (BasicInternalFrameTitlePane) ((BasicInternalFrameUI) this.getUI()).getNorthPane();
        this.remove(titlePane);
    }
    
    public void kode(){
        try {
            com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
            Statement stat = c.createStatement();
            String sql = "SELECT MAX(right(id_transaksi,9)) AS no FROM transaksi";
            ResultSet rs = stat.executeQuery(sql);
            while(rs.next()){
                if (rs.first()== false)
                {
                    txt_id_transaksi.setText("T000000001");
                } else {
                    rs.last();  
                    int set_id = rs.getInt(1)+1;
                    String no = String.valueOf(set_id);
                    int id_next = no.length();
                    for(int a=0; a < 9 - id_next; a++){
                        no = "0" +no;
                    }
                    txt_id_transaksi.setText("T"+no);
                }
            }
        } catch (SQLException ex) {
            
        }
    }
    
    private void nonaktif()
    {
        btn_hapus.setEnabled(false);
    }
    
    private void tgl_sekarang()
    {
        Date ys=new Date();
        SimpleDateFormat s=new SimpleDateFormat("EEEE, dd MMMM yyyy");
        txt_tanggal.setText(s.format(ys));
    }
    
    private void hidden()
    {
        txt_nama.setVisible(false);
        txt_hrg_eceran.setVisible(false);
    }

    
    private void simpan_ditabel() {    //SIMPAN SEMENTARA
        try{
            //JIKA INTEGER
           
            //JIKA STRING
            String id_barang= String.valueOf(barcode.getText());
            String nama=String.valueOf(txt_nama.getText());
            String default_pilihan = "Eceran";
            
            // Membuat Combobox
            JComboBox cb = new JComboBox();
            cb.addItem("Eceran");
            cb.addItem("Grosir");
            int harga = Integer.parseInt(txt_hrg_eceran.getText());
            
            int qty = 1;
            int total = Integer.parseInt(txt_hrg_eceran.getText());

            daftar_produk.setModel(list_produk);
            // kolom ke 4 => array(3);
            daftar_produk.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(cb));
            list_produk.addRow(new Object[]{id_barang,nama,default_pilihan,qty,harga,total});
            
        }
        catch(NumberFormatException exception)
        {
            System.out.println("Error ss : "+exception);
        }

    }
    
    public void getsum(){  //MENJUMLAHKAN HARGA DATA
        int batas = daftar_produk.getRowCount();
        int sum = 0;
        for(int i=0; i < batas; i++)
        {
            sum = sum + Integer.parseInt(daftar_produk.getValueAt(i,5).toString());
        }
         double angka = (double)sum;
         String mataUang = String.format("%,.0f", angka).replaceAll(",", ".");
         txt_total.setText(mataUang);
        
    } 

    public void getKembalian()
    {
        if(txt_total.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "Masukkan Barang !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            txt_bayar.setText("");
        }
        else if(txt_bayar.getText().equals(""))
        {
            txt_kembalian.setText("0");
        }
        else 
        {
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

        daftar_produk.setBackground(new java.awt.Color(214, 217, 223));
        daftar_produk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "KODE BARANG", "NAMA BARANG", "PILIHAN", "QTY", "HARGA", "TOTAL"
            }
        ));

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, daftar_produk, org.jdesktop.beansbinding.ELProperty.create("false"), daftar_produk, org.jdesktop.beansbinding.BeanProperty.create("showHorizontalLines"));
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, daftar_produk, org.jdesktop.beansbinding.ELProperty.create("false"), daftar_produk, org.jdesktop.beansbinding.BeanProperty.create("showVerticalLines"));
        bindingGroup.addBinding(binding);

        daftar_produk.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                daftar_produkMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(daftar_produk);

        barcode.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                barcodeKeyPressed(evt);
            }
        });

        txt_total.setEditable(false);

        jLabel1.setText("KODE BARANG");

        txt_tanggal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_tanggal.setText("Tanggal");

        jLabel2.setText("Total");

        txt_bayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_bayarKeyReleased(evt);
            }
        });

        txt_kembalian.setEditable(false);

        jLabel3.setText("Bayar");

        jLabel4.setText("Kembalian");

        btn_cariBarang.setText("Cari Barang");
        btn_cariBarang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cariBarangActionPerformed(evt);
            }
        });

        btn_hapus.setText("Hapus");
        btn_hapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hapusActionPerformed(evt);
            }
        });

        txt_id_transaksi.setText("ID TRANSAKSI");

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(142, 142, 142)
                            .addComponent(jLabel3)
                            .addGap(18, 18, 18)
                            .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(32, 32, 32)
                            .addComponent(jLabel2)
                            .addGap(29, 29, 29)
                            .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(33, 33, 33)
                            .addComponent(jLabel4)
                            .addGap(26, 26, 26)
                            .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1314, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txt_hrg_eceran, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(341, 341, 341)
                                .addComponent(jLabel1)
                                .addGap(34, 34, 34))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txt_id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(263, 263, 263)))
                        .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn_cariBarang)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(70, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_tanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_id_transaksi, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(37, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_cariBarang, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(barcode, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(btn_hapus, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_hrg_eceran, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_nama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_total, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(txt_bayar, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_kembalian, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(48, 48, 48))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void barcodeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_barcodeKeyPressed
        // TODO add your handling code here:
        
        if(evt.getKeyCode()==KeyEvent.VK_ENTER) //JIKA ADA BARCODE OTOMATIS IKI!!
        {
            // Mengambil nama,harga,dll
            // di hidden texfield e
            
            
            String id_barang = barcode.getText();
            try {
                //int no=1;
                String sql = "SELECT * FROM barang WHERE id_barang='"+id_barang+"'";
                java.sql.Connection conn=(com.mysql.jdbc.Connection)Koneksi.configDB();
                java.sql.Statement stm=conn.createStatement();
                java.sql.ResultSet res=stm.executeQuery(sql);
                while(res.next()){
                    String nama = res.getString("nm_barang");
                    String harga = res.getString("hrg_eceran");

                    txt_nama.setText(nama);
                    txt_hrg_eceran.setText(harga);

                }
                
            } catch (SQLException e) {
            }
            
            // Simpan nang tabel
            try {
                // TODO add your handling code here:
                com.mysql.jdbc.Connection c = (com.mysql.jdbc.Connection) Koneksi.configDB();
                Statement stat = c.createStatement();
                String sql2 = "SELECT * FROM barang WHERE id_barang='"+id_barang+"'";
                ResultSet rs = stat.executeQuery(sql2);
            if(barcode.getText().equals(""))
            {
                JOptionPane.showMessageDialog(null, "Masukkan kode !","Kesalahan", JOptionPane.ERROR_MESSAGE);
            }
            else if(rs.next() == false)
            {
                JOptionPane.showMessageDialog(null, "Kode barang tidak ada !","Kesalahan", JOptionPane.ERROR_MESSAGE);
                return;       
            }
            else 
            {
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
        getKembalian();
    }//GEN-LAST:event_txt_bayarKeyReleased

    private void btn_cariBarangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cariBarangActionPerformed
        // TODO add your handling code here:
         new CariBarang().setVisible(true);
    }//GEN-LAST:event_btn_cariBarangActionPerformed

    private void btn_hapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hapusActionPerformed
        // TODO add your handling code here:
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
    }//GEN-LAST:event_btn_hapusActionPerformed

    private void daftar_produkMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_daftar_produkMouseClicked
        // TODO add your handling code here:
        btn_hapus.setEnabled(true);
    }//GEN-LAST:event_daftar_produkMouseClicked

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        try {
            Date skrg = new Date();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String tgl_transaksi = format.format(skrg);
            String id_user = "U01";
            
            String totals = txt_total.getText();
            DecimalFormat kursIndonesia = (DecimalFormat) DecimalFormat.getCurrencyInstance();
            DecimalFormatSymbols formatRp = new DecimalFormatSymbols();
            formatRp.setCurrencySymbol("");
            formatRp.setMonetaryDecimalSeparator(' ');
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);
            Number number = kursIndonesia.parse(totals);
            double total = number.doubleValue();
            int total_harga = (int)total;
            
            String kembalians = txt_kembalian.getText();
            formatRp.setCurrencySymbol("");
            formatRp.setMonetaryDecimalSeparator(' ');
            formatRp.setGroupingSeparator('.');
            kursIndonesia.setDecimalFormatSymbols(formatRp);
            Number numbers = kursIndonesia.parse(kembalians);
            double kembalian = numbers.doubleValue();
            int total_kembalian = (int)kembalian;
            
            
            String sql_transaksi = "INSERT INTO transaksi VALUES ('"+txt_id_transaksi.getText()+"','"+id_user+"','"+total_harga+"','"+txt_bayar.getText()+"','"+total_kembalian+"','"+tgl_transaksi+"')";
            java.sql.Connection conn=(Connection)Koneksi.configDB();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql_transaksi);
            pst.execute();
            
            int row = daftar_produk.getRowCount();
            for(int i=0; i < row ; i++)
            {
                String id_barang = daftar_produk.getValueAt(i,0).toString();
                String jenis_beli = daftar_produk.getValueAt(i,2).toString();
                int qty = Integer.parseInt(daftar_produk.getValueAt(i,3).toString());
                int total_hrg= Integer.parseInt(daftar_produk.getValueAt(i,5).toString());

                String sql_detail_transaksi ="insert into detail_transaksi (id_transaksi,id_barang,jenis_beli,qty,total_hrg) values('"+txt_id_transaksi.getText()+"','"+id_barang+"','"+jenis_beli+"','"+qty+"','"+total_hrg+"')";
                java.sql.PreparedStatement pst2=conn.prepareStatement(sql_detail_transaksi);
                pst2.execute();

            }


            JOptionPane.showMessageDialog(null, "Transaksi Berhasil!");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        } catch (ParseException ex) {
            Logger.getLogger(Transaksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btn_simpanActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField barcode;
    private javax.swing.JButton btn_cariBarang;
    private javax.swing.JButton btn_hapus;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JTable daftar_produk;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_bayar;
    private javax.swing.JTextField txt_hrg_eceran;
    private javax.swing.JLabel txt_id_transaksi;
    private javax.swing.JTextField txt_kembalian;
    private javax.swing.JTextField txt_nama;
    private javax.swing.JLabel txt_tanggal;
    private javax.swing.JTextField txt_total;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
