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
import com.anjelin.util.StackTraceUtil;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author javrammo
 */
public class RegistrosPersonaDialog extends JDialog implements ActionListener{
    public static final String FORMATO_HORA = "HH:mm";

    private RegistroPersona registro;
    private JDateChooser fecha = new JDateChooser();
    //private JComboBox horaLlegada;
    //private JComboBox horaSalida = new JComboBox();
    //private JComboBox minutosLlegada = new JComboBox();
    //private JComboBox minutosSalida = new JComboBox();
    //private JComboBox combo_AM_PM_Entrada = new JComboBox(new String[]{"AM", "PM"});
    //private JComboBox combo_AM_PM_Salida = new JComboBox(new String[]{"AM", "PM"});
    private JTextFieldDateEditor horaEntrada;
    private JTextFieldDateEditor horaSalida;
    private JTextArea observaciones = new JTextArea();
    private JButton botonEditar = new JButton("Editar");
    private JButton botonGuardar = new JButton("Guardar");
    private boolean edicion; //true: edicion, false: nuevo registro
    private JButton botonEliminar = new JButton("Eliminar");
    private JTable tablaRegistrosPersona;
    private Persona personaSeleccionada;
    
    

    public RegistrosPersonaDialog(RegistroPersona registro, boolean edicion, JTable tablaRegistrosPersona) {
        this.registro = registro;
        this.edicion = edicion;
        this.tablaRegistrosPersona = tablaRegistrosPersona;

        setLayout(new BorderLayout(5, 5));

        JPanel fechaHoras = new JPanel();
        BoxLayout layout = new BoxLayout(fechaHoras, BoxLayout.Y_AXIS);
        fechaHoras.setLayout(layout);

        JPanel panelHoras = new JPanel(new GridLayout(2, 3));

        //Integer[] horas = new Integer[12];
        //for (int i = 0; i < 12; i++) {
        //    horas[i] = i + 1;
        //}
        //horaLlegada = new JComboBox(horas);
        this.horaEntrada = new JTextFieldDateEditor(true, FORMATO_HORA, "##:##",' ');           
        this.horaEntrada.setToolTipText("Ingrese la hora en formato de 24 horas. Ejemplo 8:00PM = 20:00");
        this.horaSalida = new JTextFieldDateEditor(true, FORMATO_HORA, "##:##",' ');           
        this.horaSalida.setToolTipText("Ingrese la hora en formato de 24 horas. Ejemplo 8:00PM = 20:00");        
        
        
        panelHoras.add(crearComponenteConLabelSuperior("**Hora LLegada (HH24:MM): ", horaEntrada));
        panelHoras.add(crearComponenteConLabelSuperior("Hora Salida (HH24:MM): ", horaSalida));
        //Integer[] minutos = new Integer[60];
        //for (int i = 0; i < 60; i++) {
        //    minutos[i] = i;
        //}
        //minutosLlegada = new JComboBox(minutos);
        //panelHoras.add(crearComponenteConLabelSuperior("Minutos LLegada: ", minutosLlegada));
        //panelHoras.add(crearComponenteConLabelSuperior("", combo_AM_PM_Entrada));
        //horaSalida = new JComboBox(horas);
        //panelHoras.add(crearComponenteConLabelSuperior("Hora Salida: ", horaSalida));
        //minutosSalida = new JComboBox(minutos);
        //panelHoras.add(crearComponenteConLabelSuperior("Minutos LLegada: ", minutosSalida));
        //panelHoras.add(crearComponenteConLabelSuperior("", combo_AM_PM_Salida));

        fechaHoras.add(crearComponenteConLabelSuperior("**Fecha:", fecha));
        fechaHoras.add(panelHoras);

        getContentPane().add(fechaHoras, BorderLayout.NORTH);
        add(crearComponenteConLabelSuperior("**Observaciones:", observaciones), BorderLayout.CENTER);
        add(new JLabel("** Campos Obligatorios"), BorderLayout.SOUTH);
        observaciones.setRows(4);


        if (this.edicion) {
            JPanel botones = new JPanel();
            botones.add(botonEditar);
            botones.add(botonEliminar);
            botonEditar.setActionCommand(String.valueOf(Constantes.COMANDO_MODIFICAR));
            botonEditar.addActionListener(this);
            botonEliminar.setActionCommand(String.valueOf(Constantes.COMANDO_ELIMINAR));
            botonEliminar.addActionListener(this);
            botonEditar.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/edit.png")).getImage().getScaledInstance( Constantes.TAMANO_IMAGEN_WIDTH, Constantes.TAMANO_IMAGEN_HEIGHT,  java.awt.Image.SCALE_SMOOTH )));
            botonEliminar.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/delete.png")).getImage().getScaledInstance( Constantes.TAMANO_IMAGEN_WIDTH, Constantes.TAMANO_IMAGEN_HEIGHT,  java.awt.Image.SCALE_SMOOTH )));

            getContentPane().add(botones, BorderLayout.SOUTH);
        } else {
            getContentPane().add(botonGuardar, BorderLayout.SOUTH);
            botonGuardar.setActionCommand(String.valueOf(Constantes.COMANDO_GUARDAR));
            botonGuardar.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/images/save.png")).getImage().getScaledInstance( Constantes.TAMANO_IMAGEN_WIDTH, Constantes.TAMANO_IMAGEN_HEIGHT,  java.awt.Image.SCALE_SMOOTH )));
            botonGuardar.addActionListener(this);
        }
        
        if(this.registro != null){
            cargarInformacion(this.registro);
        }
        
        setResizable(false);
        setModal(true);
        pack();
        setLocationRelativeTo(null);
    }

    private JPanel crearComponenteConLabelSuperior(String tituloLabel, Component componente) {

        //JPanel panel = new JPanel(new BorderLayout(1, 1));
        JPanel panel = new JPanel();
        BoxLayout layout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(layout);
        panel.add(new Label(tituloLabel), BorderLayout.NORTH);
        panel.add(componente, BorderLayout.SOUTH);
        return panel;
    }

    private void cargarInformacion(RegistroPersona registro) {
        
        fecha.setDate(registro.getFecha());
        SimpleDateFormat formato = new SimpleDateFormat(FORMATO_HORA);
        if(registro.getHoraEntrada() != null){
            
            horaEntrada.setText(formato.format(registro.getHoraEntrada()));
            horaEntrada.setDate(registro.getHoraEntrada());

        }
        
        if(registro.getHoraSalida() != null){
            horaSalida.setText(formato.format(registro.getHoraSalida()));
            horaSalida.setDate(registro.getHoraSalida());
        }
        
        observaciones.setText(registro.getObservaciones());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        
        Integer comando = Integer.parseInt(actionEvent.getActionCommand());
        switch (comando) {
            case Constantes.COMANDO_MODIFICAR: {
                
                if(validacionesRegistro()){
                    
                    registro.setFecha(fecha.getDate());
                    registro.setHoraEntrada(horaEntrada.getDate());
                    registro.setHoraSalida(horaSalida.getDate());
                    registro.setObservaciones(observaciones.getText());
                    registro.setAuto((short)0);
                    
                    PersonaRegistrosDelegate delegate = new PersonaRegistrosDelegate();
                    try {
                        delegate.modificar(getRegistro());
                        JOptionPane.showMessageDialog(this, "Registro Modificado con Éxito!!", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        ((AbstractTableModel)tablaRegistrosPersona.getModel()).fireTableDataChanged();
                    } catch (Exception ex) {                        
                        JOptionPane.showMessageDialog(this, StackTraceUtil.getStackTrace(ex), "Error!", JOptionPane.ERROR_MESSAGE);
                    } 
                                       
                }
                
                break;
            }
            case Constantes.COMANDO_ELIMINAR: {
                
                if (JOptionPane.showConfirmDialog(null, "<html>Confirma la eliminación del registro <b> ID: " + getRegistro().getId() + "</b> ?</html>", "CONFIRMACION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    PersonaRegistrosDelegate delegate = new PersonaRegistrosDelegate();
                    try {
                        delegate.eliminar(getRegistro());
                        dispose();
                        //eliminamos el objeto a la lista
                        RegistrosPersonaTableModel model = (RegistrosPersonaTableModel) tablaRegistrosPersona.getModel();
                        model.getRegistros().remove(getRegistro());                        
                        ((AbstractTableModel)tablaRegistrosPersona.getModel()).fireTableDataChanged();
                    } catch (Exception ex) {                        
                        JOptionPane.showMessageDialog(null, StackTraceUtil.getStackTrace(ex), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

                break;
            }
            case Constantes.COMANDO_GUARDAR: {
                
                if(validacionesRegistro()){
                    //copiamos la persona
                    registro = new RegistroPersona();
                    registro.setFecha(fecha.getDate());
                    registro.setHoraEntrada(horaEntrada.getDate());
                    registro.setHoraSalida(horaSalida.getDate());
                    registro.setObservaciones(observaciones.getText());
                    registro.setIdPersona(getPersonaSeleccionada());
                    registro.setAuto((short)0);
                    
                    PersonaRegistrosDelegate delegate = new PersonaRegistrosDelegate();
                    try {
                        delegate.crear(getRegistro());
                        JOptionPane.showMessageDialog(this, "Registro Creado con Éxito!!", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        //agregamos el nuevo objeto a la lista
                        RegistrosPersonaTableModel model = (RegistrosPersonaTableModel) tablaRegistrosPersona.getModel();
                        model.getRegistros().add(registro);
                        //refrescamos la tabla
                        ((AbstractTableModel)tablaRegistrosPersona.getModel()).fireTableDataChanged();
                    } catch (Exception ex) {                        
                        JOptionPane.showMessageDialog(this, StackTraceUtil.getStackTrace(ex), "Error!", JOptionPane.ERROR_MESSAGE);
                    } 
                                       
                }                
                break;
            }                
        }
    }

    private boolean validacionesRegistro() {
        
        if(fecha.getDate() == null){
            JOptionPane.showMessageDialog(this, "Ingrese una Fecha valida", "Error validacion", JOptionPane.ERROR_MESSAGE);
            fecha.requestFocusInWindow();
            return false;
        }else if(horaEntrada.getDate() == null){
            JOptionPane.showMessageDialog(this, "Ingrese una Hora de Entrada valida", "Error validacion", JOptionPane.ERROR_MESSAGE);
            horaEntrada.requestFocusInWindow();
            return false;
        }else if(observaciones.getText() == null || observaciones.getText().trim().isEmpty()){
            JOptionPane.showMessageDialog(this, "Ingrese una Observación valida", "Error validacion", JOptionPane.ERROR_MESSAGE);
            observaciones.requestFocusInWindow();
            return false;
        }        
        return true;
    }

    public RegistroPersona getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroPersona registro) {
        this.registro = registro;
    }

    public boolean isEdicion() {
        return edicion;
    }

    public void setEdicion(boolean edicion) {
        this.edicion = edicion;
    }

    public JTable getTablaRegistrosPersona() {
        return tablaRegistrosPersona;
    }

    public void setTablaRegistrosPersona(JTable tablaRegistrosPersona) {
        this.tablaRegistrosPersona = tablaRegistrosPersona;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }
}
