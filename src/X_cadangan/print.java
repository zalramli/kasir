/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package X_cadangan;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Lenovo
 */
public class print extends javax.swing.JFrame {

    /**
     * Creates new form print
     */
    DefaultTableModel tableModel = new DefaultTableModel(
    new Object [ ][ ] {},
    new String [ ] {
    "Barang", "Jumlah","Harga"
    });
    private int row;
    
    public print() {
        initComponents();
    }
    
    private void simpan_ditabel() {    //SIMPAN SEMENTARA
        try{

          
          String brgs= String.valueOf(brg.getText());
          String jmls=String.valueOf(jml.getText());
          String hrgs=String.valueOf(hrg.getText());
        tableModel.addRow(new Object[]{brgs,jmls,hrgs});
        jTable1.setModel(tableModel);
    }
        catch(NumberFormatException exception)
    {
    System.out.println("Error ss : "+exception);
    }

    }
    
    public PageFormat getPageFormat(PrinterJob pj)
    {

        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();    

        double middleHeight =8.0;  
        double headerHeight = 2.0;                  
        double footerHeight = 2.0;                  
        double width = convert_CM_To_PPI(8);      //printer know only point per inch.default value is 72ppi
        double height = convert_CM_To_PPI(headerHeight+middleHeight+footerHeight); 
        paper.setSize(width, height);
        paper.setImageableArea(                    
            0,
            10,
            width,            
            height - convert_CM_To_PPI(1)
        );   //define boarder size    after that print area width is about 180 points

        pf.setOrientation(PageFormat.PORTRAIT);           //select orientation portrait or landscape but for this time portrait
        pf.setPaper(paper);    

        return pf;
    }
    
    protected static double convert_CM_To_PPI(double cm) 
    {            
	return toPPI(cm * 0.393600787);            
    }
 
    protected static double toPPI(double inch) 
    {            
        return inch * 72d;            
    }
    
    public class BillPrintable implements Printable 
    {
    
  
        public int print(Graphics graphics, PageFormat pageFormat,int pageIndex) 
        throws PrinterException 
        {    
      
            
            int result = NO_SUCH_PAGE;    
              if (pageIndex == 0) 
                {                    

                  Graphics2D g2d = (Graphics2D) graphics;
                  double width = pageFormat.getImageableWidth();                    
                  g2d.translate((int) pageFormat.getImageableX(),(int) pageFormat.getImageableY()); 

                  
              try{
                  /*Draw Header*/
                  int y=20;
                  int yShift = 10;
                  int headerRectHeight=15;
                  int headerRectHeighta=40;

                  g2d.setFont(new Font("Monospaced",Font.PLAIN,7));
                  g2d.drawString("        SUMBER REJEKI        ",12,y);y+=yShift;
                  g2d.setFont(new Font("Monospaced",Font.PLAIN,6));
                  g2d.drawString("      Jl. Raya Tongas No.189        ",12,y);y+=yShift;
                  g2d.drawString("         Telp 082234568912          ",12,y);y+=yShift;
                  g2d.drawString("                                     ",10,y);y+=yShift;
                  g2d.drawString("ID  : T0000000001    Kasir : Sapri   ",10,y);y+=yShift;
                  g2d.drawString("Tgl : 23/01/2019 22:20:12         ",10,y);y+=yShift;
                  g2d.drawString("-------------------------------------",10,y);y+=yShift;
                  g2d.drawString("                                     ",10,y);y+=yShift;
                  int t = jTable1.getRowCount();
                  for(int i=0; i < t ; i++)
                  {
                  String brg= jTable1.getValueAt(i,0).toString();
                  String jml= jTable1.getValueAt(i,1).toString();
                  String hrg= jTable1.getValueAt(i,2).toString();
                  g2d.drawString(" "+brg+"                            ",10,y);y+=yShift;
                  g2d.drawString("         "+jml+"",10,y);
                  g2d.drawString("              x",10,y);
                  g2d.drawString("                  "+hrg+"",10,y);
                  g2d.drawString("                           "+hrg+"",10,y);y+=yShift;
                  }
                  g2d.drawString("-------------------------------------",10,y);y+=yShift;                  
                  g2d.drawString("                           10000",10,y);y+=yShift;
                  g2d.drawString("-------------------------------------",10,y);y+=yShift;
                  g2d.drawString("          Free Home Delivery         ",10,y);y+=yShift;
                  g2d.drawString("             03111111111             ",10,y);y+=yShift;
                  g2d.drawString("*************************************",10,y);y+=yShift;
                  g2d.drawString("    THANKS TO VISIT OUR RESTUARANT   ",10,y);y+=yShift;
                  g2d.drawString("*************************************",10,y);y+=yShift;
                   }
                   catch(Exception r){
                   r.printStackTrace();
                   }

                   result = PAGE_EXISTS;    
                }    
                    return result;    
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

        brg = new javax.swing.JTextField();
        jml = new javax.swing.JTextField();
        hrg = new javax.swing.JTextField();
        btn_simpan = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        print = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        hrg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hrgActionPerformed(evt);
            }
        });

        btn_simpan.setText("SIMPAN");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

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
        jScrollPane1.setViewportView(jTable1);

        print.setText("PRINT");
        print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                printActionPerformed(evt);
            }
        });

        jLabel1.setText("BARANG");

        jLabel2.setText("JUMLAH");

        jLabel3.setText("HArga");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(brg, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jml, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(hrg, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btn_simpan, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel3))))
                .addContainerGap(185, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(555, 555, 555)
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(74, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(brg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(hrg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jml, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_simpan))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(128, 128, 128)
                    .addComponent(print, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(381, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void hrgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hrgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hrgActionPerformed

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        simpan_ditabel();
        brg.setText("");        
        jml.setText("");
                hrg.setText("");


    }//GEN-LAST:event_btn_simpanActionPerformed

    private void printActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printActionPerformed
        // TODO add your handling code here:
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new BillPrintable(),getPageFormat(pj));
        try {
            pj.print();

        }
        catch (PrinterException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_printActionPerformed

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
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(print.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new print().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField brg;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JTextField hrg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jml;
    private javax.swing.JButton print;
    // End of variables declaration//GEN-END:variables
}
