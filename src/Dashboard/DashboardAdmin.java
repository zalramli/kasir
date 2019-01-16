/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dashboard;
import Master.*;
import Koneksi.Koneksi;
import java.awt.GraphicsEnvironment;
import javax.swing.JFrame;
/**
 *
 * @author Lenovo
 */
public class DashboardAdmin extends javax.swing.JFrame {
    boolean[] stat = new boolean[3];
    User var_user;
    Akses var_akses;
    
    static boolean maximized = true;
    /**
     * Creates new form DashboardAdmin
     */
    public DashboardAdmin() {
        initComponents();
        fullscreen();
        resetstat();
    }
    
    private void fullscreen() {
        if (maximized) {
            DashboardAdmin.this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
            DashboardAdmin.this.setMaximizedBounds(env.getMaximumWindowBounds());
            maximized = false;
        } else {
            setExtendedState(JFrame.NORMAL);
            maximized = true;
        }
    }
    
    private void addToPane() {
        dekstop_pane.add(var_akses);
        dekstop_pane.add(var_user);

    }
    private void resetstat() {
        for (int i = 0; i < 1; i++) {
            stat[i] = false;
        }
    }
    private void hide_pane() {
        if (stat[0] == true) {
            var_akses.dispose();
        }
        if (stat[1] == true) {
            var_user.dispose();
        }
        resetstat();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dekstop_pane = new javax.swing.JDesktopPane();
        menu_akses = new javax.swing.JButton();
        menu_user = new javax.swing.JButton();
        menu_distributor = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1370, 770));

        javax.swing.GroupLayout dekstop_paneLayout = new javax.swing.GroupLayout(dekstop_pane);
        dekstop_pane.setLayout(dekstop_paneLayout);
        dekstop_paneLayout.setHorizontalGroup(
            dekstop_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        dekstop_paneLayout.setVerticalGroup(
            dekstop_paneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 608, Short.MAX_VALUE)
        );

        menu_akses.setText("AKSES");
        menu_akses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_aksesActionPerformed(evt);
            }
        });

        menu_user.setText("USER");
        menu_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_userActionPerformed(evt);
            }
        });

        menu_distributor.setText("DISTRIBUTOR");
        menu_distributor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_distributorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(191, 191, 191)
                .addComponent(menu_user, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menu_akses, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(menu_distributor)
                .addContainerGap(850, Short.MAX_VALUE))
            .addComponent(dekstop_pane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(dekstop_pane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(menu_user, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_akses, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(menu_distributor, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menu_aksesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_aksesActionPerformed
        // TODO add your handling code here:
        hide_pane();
        stat[0] = true;
        var_akses  = new Akses();
        dekstop_pane.add(var_akses);
        var_akses.setVisible(true);
    }//GEN-LAST:event_menu_aksesActionPerformed

    private void menu_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_userActionPerformed
        // TODO add your handling code here:
        hide_pane();
        stat[1] = true;
        var_user  = new User();
        dekstop_pane.add(var_user);
        var_user.setVisible(true);
    }//GEN-LAST:event_menu_userActionPerformed

    private void menu_distributorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_distributorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_menu_distributorActionPerformed

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
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DashboardAdmin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DashboardAdmin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane dekstop_pane;
    private javax.swing.JButton menu_akses;
    private javax.swing.JButton menu_distributor;
    private javax.swing.JButton menu_user;
    // End of variables declaration//GEN-END:variables
}
