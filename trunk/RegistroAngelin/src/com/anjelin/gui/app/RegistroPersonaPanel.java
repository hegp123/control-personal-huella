/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.app;

import com.anjelin.constantes.Constantes;
import com.anjelin.dal.persona.PersonaRegistrosDelegate;
import com.anjelin.gui.table.model.RegistrosPersonaTableModel;
import com.anjelin.modelo.Persona;
import com.anjelin.modelo.RegistroPersona;
import com.anjelin.util.DateUtils;
import com.anjelin.util.StackTraceUtil;
import com.toedter.calendar.JDateChooser;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Admon
 */
class RegistroPersonaPanel extends JPanel implements ActionListener{

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
    private Date fechaInicio;
    private Date fechaFin;
    private Persona personaSeleccionada;
    private RegistroPersona registroPersonaSeleccionado;
    
    public RegistroPersonaPanel(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
        setLayout(new GridBagLayout());
        Date fechaHoy = new Date(System.currentTimeMillis());
        fechaInicio = DateUtils.getFirstDay(fechaHoy, true);
        fechaFin = DateUtils.getLastDay(fechaHoy, true);

        
        GridBagConstraints cons_fechaInicial = new GridBagConstraints();
        cons_fechaInicial.gridx = 0; // El área de texto empieza en la columna cero.
        cons_fechaInicial.gridy = 0; // El área de texto empieza en la fila cero
        cons_fechaInicial.gridwidth = 1; // El área de texto ocupa 1 columna.
        cons_fechaInicial.gridheight = 1; // El área de texto ocupa 1 fila.
        cons_fechaInicial.weightx=1;
        fechaInicial.setDate(fechaInicio);
        cons_fechaInicial.fill = GridBagConstraints.BOTH;
        add(fechaInicial, cons_fechaInicial);
        
        //add(new JTextField(), cons_fechaInicial);
        
        //add(new JButton("1"), cons_fechaInicial);

        GridBagConstraints cons_fechaFinal = new GridBagConstraints();
        cons_fechaFinal.gridx = 1; 
        cons_fechaFinal.gridy = 0; 
        cons_fechaFinal.gridwidth = 1; 
        cons_fechaFinal.gridheight = 1;
        cons_fechaFinal.weightx=1;
        fechaFinal.setDate(fechaFin);        
        cons_fechaFinal.fill = GridBagConstraints.BOTH;
        add(fechaFinal, cons_fechaFinal); 
        //add(new JTextField(), cons_fechaFinal);    
        
        //add(new JButton("2"), cons_fechaFinal);

        GridBagConstraints cons_botonBuscar = new GridBagConstraints();
        cons_botonBuscar.gridx = 2; 
        cons_botonBuscar.gridy = 0; 
        cons_botonBuscar.gridwidth = 1; 
        cons_botonBuscar.gridheight = 1;
        cons_botonBuscar.weightx=1;
        cons_botonBuscar.fill = GridBagConstraints.BOTH;
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
        cons_tablaRegistros.weightx=1.0;
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
                    RegistrosPersonaDialog dialogo = new RegistrosPersonaDialog(getRegistroPersonaSeleccionado(), true, getTablaRegistrosPersona());
                    dialogo.setVisible(true);
                    
                }
            }
        });

        //setBorder(BorderFactory.createLineBorder(Color.black));
        
        //Comando
        buscar.setActionCommand(String.valueOf(Constantes.COMANDO_BUSCAR));
        eliminarButton.setActionCommand(String.valueOf(Constantes.COMANDO_ELIMINAR));
        modificarButton.setActionCommand(String.valueOf(Constantes.COMANDO_MODIFICAR));
        
        //listener
        buscar.addActionListener(this);
        eliminarButton.addActionListener(this);
        modificarButton.addActionListener(this);
        
        //listener a la tabla
        tablaRegistrosPersona.getSelectionModel().addListSelectionListener(new RowListener());
        
        //tipo de seleccion en el registro
        tablaRegistrosPersona.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        Integer comando = Integer.parseInt(actionEvent.getActionCommand());
        switch (comando) {
            case Constantes.COMANDO_BUSCAR: {                
                Date fechaI = fechaInicial.getDate();
                Date fechaF = fechaFinal.getDate();
                if(getPersonaSeleccionada() == null || getPersonaSeleccionada().getId() == null){
                    JOptionPane.showMessageDialog(null, "Debe seleccionar una persona!", "Error!", JOptionPane.ERROR_MESSAGE);
                    break;                    
                }else if(fechaI == null){
                    JOptionPane.showMessageDialog(null, "Debe ingresar una Fecha de Inicio valida", "Error!", JOptionPane.ERROR_MESSAGE);
                    fechaInicial.requestFocusInWindow();
                    break;
                }else if(fechaF == null){
                    JOptionPane.showMessageDialog(null, "Debe ingresar una Fecha Fin valida", "Error!", JOptionPane.ERROR_MESSAGE);
                    fechaFinal.requestFocusInWindow();
                    break;
                }if(fechaF.before(fechaI)){
                    JOptionPane.showMessageDialog(null, "La Fecha de Inicio debe ser menor a la Fecha Final", "Error!", JOptionPane.ERROR_MESSAGE);
                    fechaInicial.requestFocusInWindow();                    
                }
                getTablaRegistrosPersona().removeAll();
                registrosPersonaTableModel.cargarRegistrosPersonaPorRango(personaSeleccionada, DateUtils.truncDate(fechaI), DateUtils.truncDate(fechaF));
                getTablaRegistrosPersona().setModel(registrosPersonaTableModel);
                updateUI();
                break;
            }
            case Constantes.COMANDO_MODIFICAR: {
                
                RegistrosPersonaDialog dialogo =  new RegistrosPersonaDialog(getRegistroPersonaSeleccionado(), true,getTablaRegistrosPersona());
                dialogo.setVisible(true);
                
                break;
            }
            case Constantes.COMANDO_ELIMINAR: {
                
                if (JOptionPane.showConfirmDialog(null, "<html>Confirma la eliminación del registro <b> ID: " + getRegistroPersonaSeleccionado().getId() + "</b> ?</html>", "CONFIRMACION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    PersonaRegistrosDelegate delegate = new PersonaRegistrosDelegate();
                    try {
                        delegate.eliminar(getRegistroPersonaSeleccionado());
                    } catch (Exception ex) {                        
                        JOptionPane.showMessageDialog(null, StackTraceUtil.getStackTrace(ex), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

                break;
            }
            default:
                break;
        }

    }

    private class RowListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent event) {
            if (event.getValueIsAdjusting()) {
                return;
            }
            seleccionarRegistroPersona();
        }
    }    
    
        private void seleccionarRegistroPersona() {

        try {
            for (int c : tablaRegistrosPersona.getSelectedRows()) {
                RegistrosPersonaTableModel modelo = (RegistrosPersonaTableModel) tablaRegistrosPersona.getModel();
                setRegistroPersonaSeleccionado(modelo.getRegistroPersona(c));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


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

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public RegistroPersona getRegistroPersonaSeleccionado() {
        return registroPersonaSeleccionado;
    }

    public void setRegistroPersonaSeleccionado(RegistroPersona registroPersonaSeleccionado) {
        this.registroPersonaSeleccionado = registroPersonaSeleccionado;
    }
}
