/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.app;

import com.anjelin.constantes.Constantes;
import com.anjelin.dal.persona.PersonaRegistrosDelegate;
import com.anjelin.modelo.RegistroPersona;
import com.anjelin.util.StackTraceUtil;
import com.toedter.calendar.JDateChooser;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author javrammo
 */
public class RegistrosPersonaDialog extends JDialog implements ActionListener{

    private RegistroPersona registro;
    private JDateChooser fecha = new JDateChooser();
    private JComboBox horaLlegada;
    private JComboBox horaSalida = new JComboBox();
    private JComboBox minutosLlegada = new JComboBox();
    private JComboBox minutosSalida = new JComboBox();
    private JComboBox combo_AM_PM_Entrada = new JComboBox(new String[]{"AM", "PM"});
    private JComboBox combo_AM_PM_Salida = new JComboBox(new String[]{"AM", "PM"});
    private JTextArea observaciones = new JTextArea();
    private JButton botonEditar = new JButton("Editar");
    private JButton botonGuardar = new JButton("Guardar");
    private boolean edicion; //true: edicion, false: nuevo registro
    private JButton botonEliminar = new JButton("Eliminar");
    

    public RegistrosPersonaDialog(RegistroPersona registro, boolean edicion) {
        this.registro = registro;
        this.edicion = edicion;

        setLayout(new BorderLayout(5, 5));

        JPanel fechaHoras = new JPanel();
        BoxLayout layout = new BoxLayout(fechaHoras, BoxLayout.Y_AXIS);
        fechaHoras.setLayout(layout);

        JPanel panelHoras = new JPanel(new GridLayout(2, 3));

        Integer[] horas = new Integer[12];
        for (int i = 0; i < 12; i++) {
            horas[i] = i + 1;
        }
        horaLlegada = new JComboBox(horas);
        panelHoras.add(crearComponenteConLabelSuperior("Hora LLegada: ", horaLlegada));
        Integer[] minutos = new Integer[60];
        for (int i = 0; i < 60; i++) {
            minutos[i] = i;
        }
        minutosLlegada = new JComboBox(minutos);
        panelHoras.add(crearComponenteConLabelSuperior("Minutos LLegada: ", minutosLlegada));
        panelHoras.add(crearComponenteConLabelSuperior("", combo_AM_PM_Entrada));
        horaSalida = new JComboBox(horas);
        panelHoras.add(crearComponenteConLabelSuperior("Hora Salida: ", horaSalida));
        minutosSalida = new JComboBox(minutos);
        panelHoras.add(crearComponenteConLabelSuperior("Minutos LLegada: ", minutosSalida));
        panelHoras.add(crearComponenteConLabelSuperior("", combo_AM_PM_Salida));

        fechaHoras.add(crearComponenteConLabelSuperior("Fecha:", fecha));
        fechaHoras.add(panelHoras);

        getContentPane().add(fechaHoras, BorderLayout.NORTH);
        add(crearComponenteConLabelSuperior("Observaciones:", observaciones), BorderLayout.CENTER);
        observaciones.setRows(4);


        if (this.edicion) {
            JPanel botones = new JPanel();
            botones.add(botonEditar);
            botones.add(botonEliminar);
            botonEditar.setActionCommand(String.valueOf(Constantes.COMANDO_MODIFICAR));
            botonEditar.addActionListener(this);
            botonEliminar.setActionCommand(String.valueOf(Constantes.COMANDO_ELIMINAR));
            botonEliminar.addActionListener(this);
            getContentPane().add(botones, BorderLayout.SOUTH);
        } else {
            getContentPane().add(botonGuardar, BorderLayout.SOUTH);
            botonGuardar.setActionCommand(String.valueOf(Constantes.COMANDO_GUARDAR));
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
        if(registro.getHoraEntrada() != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(registro.getHoraEntrada());
            int hora = cal.get(Calendar.HOUR_OF_DAY);
            if(hora > 12){
                horaLlegada.setSelectedItem(hora-12);
                combo_AM_PM_Entrada.setSelectedItem("PM");
            }else{
                horaLlegada.setSelectedItem(hora);
                combo_AM_PM_Entrada.setSelectedItem("AM");
            }
            int minutos = cal.get(Calendar.MINUTE);
            minutosLlegada.setSelectedItem(minutos);
        }
        
        if(registro.getHoraSalida() != null){
            Calendar cal = Calendar.getInstance();
            cal.setTime(registro.getHoraSalida());
            int hora = cal.get(Calendar.HOUR_OF_DAY);
            if(hora > 12){
                horaSalida.setSelectedItem(hora-12);
                combo_AM_PM_Salida.setSelectedItem("PM");
            }else{
                horaSalida.setSelectedItem(hora);
                combo_AM_PM_Salida.setSelectedItem("AM");
            }
            int minutos = cal.get(Calendar.MINUTE);
            minutosSalida.setSelectedItem(minutos);
        }
        
        observaciones.setText(registro.getObservaciones());
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        
        Integer comando = Integer.parseInt(actionEvent.getActionCommand());
        switch (comando) {
            case Constantes.COMANDO_MODIFICAR: {
                
                RegistrosPersonaDialog dialogo =  new RegistrosPersonaDialog(getRegistro(), true);
                dialogo.setVisible(true);
                
                break;
            }
            case Constantes.COMANDO_ELIMINAR: {
                
                if (JOptionPane.showConfirmDialog(null, "<html>Confirma la eliminación del registro <b> ID: " + getRegistro().getId() + "</b> ?</html>", "CONFIRMACION", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    PersonaRegistrosDelegate delegate = new PersonaRegistrosDelegate();
                    try {
                        delegate.eliminar(getRegistro());
                    } catch (Exception ex) {                        
                        JOptionPane.showMessageDialog(null, StackTraceUtil.getStackTrace(ex), "Error!", JOptionPane.ERROR_MESSAGE);
                    }
                }

                break;
            }
            case Constantes.COMANDO_GUARDAR: {
                
                break;
            }                
        }
    }

    public RegistroPersona getRegistro() {
        return registro;
    }

    public void setRegistro(RegistroPersona registro) {
        this.registro = registro;
    }
    
    
    
}
