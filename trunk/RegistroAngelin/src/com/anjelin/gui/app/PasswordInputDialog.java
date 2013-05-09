/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.app;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admon
 */

public class PasswordInputDialog extends JDialog implements ActionListener {
    private static String OK = "ACEPTAR";
    private JLabel label;
    private JPasswordField passwordField;
    private JButton okButton;
    
 
    public PasswordInputDialog() {
        
        setLayout(new GridLayout(0, 1));

 
        //Create everything.
        passwordField = new JPasswordField(10);
        passwordField.setActionCommand(OK);
        passwordField.addActionListener(this);
 
        label = new JLabel("Enter the password: ");
        label.setLabelFor(passwordField);
 
        JPanel buttonPane = createButtonPanel();
 
        //Lay out everything.
        JPanel textPane = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        textPane.add(label);
        textPane.add(passwordField);
 
        
        getContentPane().add(textPane);
        getContentPane().add(buttonPane);
        
        
        setModal(true);
        pack();
        setLocationRelativeTo(null);
    }
 
    protected JPanel createButtonPanel() {
        JPanel p = new JPanel(new GridLayout(0,1));
        okButton = new JButton(OK);
        okButton.setActionCommand(OK);
        okButton.addActionListener(this);
        p.add(okButton); 
        return p;
    }
 
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
 
        if (OK.equals(cmd)) { //Process the password.
            char[] input = passwordField.getPassword();
            if (isPasswordCorrect(input)) {
                add(new JLabel("<html><b>Cargando datos....</b></html>"));
                validate();
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {                        
                        PersonaJFrame framePersona = new PersonaJFrame();
                        framePersona.setVisible(true);
                        PasswordInputDialog.this.dispose();
                    }
                });
            } else {
                JOptionPane.showMessageDialog(null,
                    "Contraseña Incorrecta, Intente Nuevamente!!",
                    "Contraseña Incorrecta",
                    JOptionPane.ERROR_MESSAGE);
            }
 
            //Zero out the possible password, for security.
            Arrays.fill(input, '0');
 
            passwordField.selectAll();
            resetFocus();
        } 
    }
 
    /**
     * Checks the passed-in array against the correct password.
     * After this method returns, you should invoke eraseArray
     * on the passed-in array.
     */
    private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect = true;
        char[] correctPassword = { '1', '2', '3'};
 
        if (input.length != correctPassword.length) {
            isCorrect = false;
        } else {
            isCorrect = Arrays.equals (input, correctPassword);
        }
 
        //Zero out the password.
        Arrays.fill(correctPassword,'0');
 
        return isCorrect;
    }
 
    //Must be called from the event dispatch thread.
    protected void resetFocus() {
        passwordField.requestFocusInWindow();
    }
 
    
}