/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.table.model;

import com.anjelin.dal.persona.PersonaRegistrosDelegate;
import com.anjelin.modelo.Persona;
import com.anjelin.modelo.RegistroPersona;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Admon
 */
public class RegistrosPersonaTableModel extends AbstractTableModel {
    
    //ver http://grch.com.ar/docs/pp/Apuntes/Introduccion%20al%20uso%20de%20JPA%202%20y%20swing.pdf
    
    public static final String[] columnNames = {"FECHA","HORA ENTRADA","HORA SALIDA"};
    public static final Class[] tipoDatoColumna = {String.class,String.class,String.class};
     public static final int FECHA_INDEX = 0;
     public static final int HORA_ENTRADA_INDEX = 1;
     public static final int HORA_SALIDA_INDEX = 2;
    
    private List<RegistroPersona> registros = new ArrayList<RegistroPersona>();
    private Persona persona;

    public RegistrosPersonaTableModel(Persona persona) {
        this.persona=persona;
    }

    public RegistrosPersonaTableModel() {
        
    }
    
    public void cargarRegistrosPersonaPorRango(Persona persona, Date fechaInicio, Date fechaFin) {
        PersonaRegistrosDelegate registrosDelegate = new PersonaRegistrosDelegate();
        this.registros = registrosDelegate.registrosPersonaPorRangoFechas(persona, fechaInicio, fechaFin);
    }
       

    @Override
    public int getRowCount() {
        return this.registros.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        RegistroPersona registro = (RegistroPersona)(registros.get(rowIndex));
        
        String formatoHora = "hh:mm:ss a";
        String formatoFecha = "dd/MM/yyyy";
        switch (columnIndex)
        {
            case FECHA_INDEX:
                if(registro.getFecha() != null){
                    return new SimpleDateFormat(formatoFecha).format(registro.getFecha()); 
                }
                return "";
            case HORA_ENTRADA_INDEX:
                if(registro.getHoraEntrada() != null){
                    return new SimpleDateFormat(formatoHora).format(registro.getHoraEntrada()); 
                }
                return "";
            case HORA_SALIDA_INDEX:
                if(registro.getHoraSalida() != null){
                    return new SimpleDateFormat(formatoHora).format(registro.getHoraSalida()); 
                }
                return "";           
            default:
                return null;
        }
    }
    
    public RegistroPersona getRegistroPersona (int rowIndex){
        return this.registros.get(rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
            case FECHA_INDEX:
                return this.tipoDatoColumna[FECHA_INDEX];
            case HORA_ENTRADA_INDEX:
                return this.tipoDatoColumna[HORA_ENTRADA_INDEX];
            case HORA_SALIDA_INDEX:
                return this.tipoDatoColumna[HORA_SALIDA_INDEX];
            default:
                return super.getColumnClass(columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    public List<RegistroPersona> getRegistros() {
        return registros;
    }

    public void setRegistros(List<RegistroPersona> registros) {
        this.registros = registros;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }    
}
