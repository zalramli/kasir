/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package X_cadangan;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import javax.swing.JFormattedTextField;
import javax.swing.text.NumberFormatter;

/**
 *
 * @author Lenovo
 */
public class Test extends javax.swing.JFrame {

    /**
     * Creates new form Test
     */
    JFormattedTextField hrg_beli;    
    JFormattedTextField hrg_jual;
    NumberFormat numformat = NumberFormat.getInstance();
    NumberFormatter numformatter;
    public Test() {
        initComponents();
        custom_currency();

    }
    void setCurrencyNow()
    {
        //  set banyaknya angka akhir bilangan
        numformat.setMaximumFractionDigits(0);
        //  Deklarasikan NumberFormatter
        numformatter = new NumberFormatter(numformat);
        numformatter.setAllowsInvalid(false);
         
    }
     void custom_currency()
     {
        setCurrencyNow();
        hrg_beli = new JFormattedTextField(numformatter);         
        hrg_jual = new JFormattedTextField(numformatter); 
        add(hrg_beli);
        hrg_beli.setBounds(200, 50, 160, 50); 
        hrg_beli.setLocation(20, 50);
        hrg_beli.setValue(0);
        add(hrg_jual);
        hrg_jual.setBounds(200, 250, 110, 50); 
        hrg_jual.setLocation(200, 100);
        hrg_jual.setValue(0);
        setTypingHarga();
     }
     
     void setTypingHarga()
    {
        //  Key Listener
        hrg_jual.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                //  Jika terjadi penekanan tombol BACK_SPACE
                if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                {
                    String text = hrg_jual.getText().toString();
                    if(text.length() == 1)
                    {
                        hrg_jual.setValue(0);
                    }
                }
            }
        });
        
        hrg_beli.addKeyListener(new KeyAdapter()
        {
            public void keyPressed(KeyEvent ke)
            {
                //  Jika terjadi penekanan tombol BACK_SPACE
                if(ke.getKeyCode() == KeyEvent.VK_BACK_SPACE)
                {
                    String text = hrg_beli.getText().toString();
                    if(text.length() == 1)
                    {
                        hrg_beli.setValue(0);
                    }
                }
            }
        });
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Test.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Test().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
