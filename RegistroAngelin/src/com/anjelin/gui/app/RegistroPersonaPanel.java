/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.app;

import com.anjelin.gui.table.model.RegistrosPersonaTableModel;
import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Admon
 */
class RegistroPersonaPanel extends JPanel {

    private static final Insets insets = new Insets(0, 0, 0, 0);
    private RegistrosPersonaTableModel registrosPersonaTableModel = new RegistrosPersonaTableModel();
    
    //private JComboBox comboMes = new JComboBox(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
    //private JComboBox comboAnno ;
    private JDateChooser fechaInicial = new JDateChooser();
    private JDateChooser fechaFinal = new JDateChooser();    
    private JButton buscar = new JButton("Buscar...");
    private JScrollPane jScrollPanelTabla = new JScrollPane();
    private JTable tablaRegistrosPersona = new JTable();
    private JButton eliminarButton = new JButton("Eliminar");
    private JButton modificarButton = new JButton("modificar");
    
    public RegistroPersonaPanel() {


        //setLayout(new GridBagLayout());
        setLayout(new GridBagLayout());
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date(System.currentTimeMillis()));
        int anno = cal.get(Calendar.YEAR);
        int mes = cal.get(Calendar.MONTH);        
        cal.set(1, mes, anno);
        
        Date _fechaInicio = (Date)cal.getTime().clone();
        int ultimoDia = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        cal.set(ultimoDia, mes, anno);
        Date _fechaFin = (Date)cal.getTime().clone();
        
        GridBagConstraints cons_fechaInicial = new GridBagConstraints();
        cons_fechaInicial.gridx = 0; // El área de texto empieza en la columna cero.
        cons_fechaInicial.gridy = 0; // El área de texto empieza en la fila cero
        cons_fechaInicial.gridwidth = 1; // El área de texto ocupa 1 columna.
        cons_fechaInicial.gridheight = 1; // El área de texto ocupa 1 fila.
        cons_fechaInicial.weightx=1;
        fechaInicial.setDate(_fechaInicio);
        System.out.println("Fecha Inicio: "+_fechaInicio);
        //add(fechaInicial, cons_fechaInicial);
        add(new JButton("1"), cons_fechaInicial);

        GridBagConstraints cons_fechaFinal = new GridBagConstraints();
        cons_fechaFinal.gridx = 1; 
        cons_fechaFinal.gridy = 0; 
        cons_fechaFinal.gridwidth = 1; 
        cons_fechaFinal.gridheight = 1;
        fechaFinal.setDate(_fechaFin);
        System.out.println("Fecha Fin: "+_fechaFin);
        //add(fechaInicial, cons_fechaFinal);        
        add(new JButton("2"), cons_fechaFinal);

        GridBagConstraints cons_botonBuscar = new GridBagConstraints();
        cons_botonBuscar.gridx = 2; 
        cons_botonBuscar.gridy = 0; 
        cons_botonBuscar.gridwidth = 1; 
        cons_botonBuscar.gridheight = 1;
        add(buscar, cons_botonBuscar);  
        //add(new JButton("3"), cons_botonBuscar);

        GridBagConstraints cons_botonModificar = new GridBagConstraints();
        cons_botonModificar.gridx = 3; 
        cons_botonModificar.gridy = 0; 
        cons_botonModificar.gridwidth = 1; 
        cons_botonModificar.gridheight = 1;
        add(modificarButton, cons_botonModificar);                        
        //add(new JButton("4"), cons_botonModificar);
        
        GridBagConstraints cons_tablaRegistros = new GridBagConstraints();
        cons_tablaRegistros.gridx = 0; 
        cons_tablaRegistros.gridy = 1; 
        cons_tablaRegistros.gridwidth = 3; 
        cons_tablaRegistros.gridheight = 2;
        cons_tablaRegistros.weighty = 1.0; // La fila 1 debe estirarse, le ponemos un 1.0
        cons_tablaRegistros.fill = GridBagConstraints.BOTH;
        cons_tablaRegistros.anchor= GridBagConstraints.FIRST_LINE_START;
        jScrollPanelTabla.setViewportView(tablaRegistrosPersona);
        add(jScrollPanelTabla, cons_tablaRegistros);  
        //add(new JButton("5"), cons_tablaRegistros);

        GridBagConstraints cons_botonEliminar = new GridBagConstraints();
        cons_botonEliminar.gridx = 3; 
        cons_botonEliminar.gridy = 1; 
        cons_botonEliminar.gridwidth = 1; 
        cons_botonEliminar.gridheight = 1;
        add(eliminarButton, cons_botonEliminar);         
        //add(new JButton("6"), cons_botonEliminar);

        
        tablaRegistrosPersona.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evnt) {
                if (evnt.getClickCount() > 1) {                    
                    RegistrosPersonaDialog dialogo = new RegistrosPersonaDialog(null, true);
                    dialogo.setVisible(true);
                    
                }
            }
        });

        setBorder(BorderFactory.createLineBorder(Color.black));

    }

    public RegistrosPersonaTableModel getRegistrosPersonaTableModel() {
        return registrosPersonaTableModel;
    }

    public void setRegistrosPersonaTableModel(RegistrosPersonaTableModel registrosPersonaTableModel) {
        this.registrosPersonaTableModel = registrosPersonaTableModel;
    }

    public JDateChooser getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(JDateChooser fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public JDateChooser getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(JDateChooser fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public JButton getBuscar() {
        return buscar;
    }

    public void setBuscar(JButton buscar) {
        this.buscar = buscar;
    }

    public JScrollPane getjScrollPanelTabla() {
        return jScrollPanelTabla;
    }

    public void setjScrollPanelTabla(JScrollPane jScrollPanelTabla) {
        this.jScrollPanelTabla = jScrollPanelTabla;
    }

    public JTable getTablaRegistrosPersona() {
        return tablaRegistrosPersona;
    }

    public void setTablaRegistrosPersona(JTable tablaRegistrosPersona) {
        this.tablaRegistrosPersona = tablaRegistrosPersona;
    }

    public JButton getEliminarButton() {
        return eliminarButton;
    }

    public void setEliminarButton(JButton eliminarButton) {
        this.eliminarButton = eliminarButton;
    }

    public JButton getModificarButton() {
        return modificarButton;
    }

    public void setModificarButton(JButton modificarButton) {
        this.modificarButton = modificarButton;
    }    
}
