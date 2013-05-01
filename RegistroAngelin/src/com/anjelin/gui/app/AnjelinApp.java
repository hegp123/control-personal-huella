/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.app;

import com.digitalpersona.onetouch.DPFPFingerIndex;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author Admon
 */
public class AnjelinApp extends javax.swing.JFrame {
    
    private PersonaJFrame framePersona = null;

    /**
     * Creates new form AnjelinApp
     */
    public AnjelinApp() {
        initComponents();
        
        try {
          EnumMap<DPFPFingerIndex, String> fingerNames;
        
        	fingerNames = new EnumMap<DPFPFingerIndex, String>(DPFPFingerIndex.class);
        	fingerNames.put(DPFPFingerIndex.LEFT_PINKY,	  "left pinky");
        	fingerNames.put(DPFPFingerIndex.LEFT_RING,    "left ring");
        	fingerNames.put(DPFPFingerIndex.LEFT_MIDDLE,  "left middle");
        	fingerNames.put(DPFPFingerIndex.LEFT_INDEX,   "left index");
        	fingerNames.put(DPFPFingerIndex.LEFT_THUMB,   "left thumb");
        	fingerNames.put(DPFPFingerIndex.RIGHT_PINKY,  "right pinky");
        	fingerNames.put(DPFPFingerIndex.RIGHT_RING,   "right ring");
        	fingerNames.put(DPFPFingerIndex.RIGHT_MIDDLE, "right middle");
        	fingerNames.put(DPFPFingerIndex.RIGHT_INDEX,  "right index");
        	fingerNames.put(DPFPFingerIndex.RIGHT_THUMB,  "right thumb");
                
                for (Map.Entry<DPFPFingerIndex, String> entry : fingerNames.entrySet()) {
                DPFPFingerIndex dPFPFingerIndex = entry.getKey();
                String string = entry.getValue();
                System.out.println(dPFPFingerIndex.toBit() + "|" + dPFPFingerIndex.toString());
                
            }
        
        } catch (Exception e) {
            e.printStackTrace();
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

        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        exitMenuItem = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentsMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        fileMenu.setMnemonic('f');
        fileMenu.setText("Principal");

        exitMenuItem.setMnemonic('x');
        exitMenuItem.setText("Salir");
        exitMenuItem.setToolTipText("Salir de la aplicación");
        exitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Personal");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Datos Personas");
        cutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cutMenuItemActionPerformed(evt);
            }
        });
        editMenu.add(cutMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Ayuda");

        contentsMenuItem.setMnemonic('c');
        contentsMenuItem.setText("Contents");
        helpMenu.add(contentsMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitMenuItemActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitMenuItemActionPerformed

    private void cutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cutMenuItemActionPerformed
        
        if(framePersona != null && framePersona.isVisible()){
            System.out.println("Esta visible...........");
            return;
        }else{
            framePersona = new PersonaJFrame();
            framePersona.setVisible(true);
        }
        
        
    }//GEN-LAST:event_cutMenuItemActionPerformed

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
            java.util.logging.Logger.getLogger(AnjelinApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AnjelinApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AnjelinApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AnjelinApp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AnjelinApp().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentsMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenuItem exitMenuItem;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenuBar menuBar;
    // End of variables declaration//GEN-END:variables
}