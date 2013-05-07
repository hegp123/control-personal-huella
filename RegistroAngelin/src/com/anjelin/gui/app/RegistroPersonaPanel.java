/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.app;

import com.anjelin.gui.table.model.RegistrosPersonaTableModel;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;

/**
 *
 * @author Admon
 */
class RegistroPersonaPanel extends JPanel {

    private static final Insets insets = new Insets(0, 0, 0, 0);
    private RegistrosPersonaTableModel registrosPersonaTableModel = new RegistrosPersonaTableModel();
    private JTable tablaRegistrosPersona = new JTable();
    private JComboBox comboMes = new JComboBox(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
    private JComboBox comboAnno ;
    private JButton buscar = new JButton("Buscar...");

    public RegistroPersonaPanel() {


        //setLayout(new GridBagLayout());
        setLayout(new GridLayout(0, 2, 5, 5));
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        int anno = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);

        comboMes.setSelectedIndex(mes);
        List<Integer> annos = new ArrayList<Integer>();
        for (int i = (anno - 10); i < (anno + 10); i++) {
            annos.add(i);
        }
        comboAnno =  new JComboBox(annos.toArray()); 
        comboAnno.setSelectedItem(anno);        
        tablaRegistrosPersona.setModel(registrosPersonaTableModel);
        
        JPanel panelBotones =new JPanel(new GridLayout(0, 2));
        panelBotones.add(comboMes);
        panelBotones.add(comboAnno);
        panelBotones.add(buscar);
        
        
        add(tablaRegistrosPersona);
        add(panelBotones);



    }

    public RegistrosPersonaTableModel getRegistrosPersonaTableModel() {
        return registrosPersonaTableModel;
    }

    public void setRegistrosPersonaTableModel(RegistrosPersonaTableModel registrosPersonaTableModel) {
        this.registrosPersonaTableModel = registrosPersonaTableModel;
    }

    public JTable getTablaRegistrosPersona() {
        return tablaRegistrosPersona;
    }

    public void setTablaRegistrosPersona(JTable tablaRegistrosPersona) {
        this.tablaRegistrosPersona = tablaRegistrosPersona;
    }

    public JComboBox getComboMes() {
        return comboMes;
    }

    public void setComboMes(JComboBox comboMes) {
        this.comboMes = comboMes;
    }

    public JComboBox getComboAnno() {
        return comboAnno;
    }

    public void setComboAnno(JComboBox comboAnno) {
        this.comboAnno = comboAnno;
    }

    public JButton getBuscar() {
        return buscar;
    }

    public void setBuscar(JButton buscar) {
        this.buscar = buscar;
    }


}
