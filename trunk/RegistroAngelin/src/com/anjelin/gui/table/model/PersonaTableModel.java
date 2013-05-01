/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.table.model;

import com.anjelin.dal.persona.PersonaDelegate;
import com.anjelin.modelo.Persona;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Admon
 */
public class PersonaTableModel extends AbstractTableModel {
    
    //ver http://grch.com.ar/docs/pp/Apuntes/Introduccion%20al%20uso%20de%20JPA%202%20y%20swing.pdf
    
    public static final String[] columnNames = {"ID","NOMBRES","APELLIDOS" ,"TELEFONO" ,"DIRECCION" ,"NUMERO_IDENTIFICACION"};
    public static final Class[] tipoDatoColumna = {Integer.class,String.class,String.class,String.class ,String.class,String.class};
     public static final int ID_INDEX = 0;
     public static final int NOMBRES_INDEX = 1;
     public static final int APELLIDOS_INDEX = 2;
     public static final int TELEFONO_INDEX = 3;
     public static final int DIRECCION_INDEX = 4;
     public static final int NUMERO_IDENTIFICACION_INDEX = 5;
    
    private List<Persona> personas = new ArrayList<Persona>();

    public PersonaTableModel() {
        PersonaDelegate personaDelegate = new PersonaDelegate();
        this.personas = personaDelegate.buscarTodos();
    }
    
    
    

    @Override
    public int getRowCount() {
        return this.personas.size();
    }

    @Override
    public int getColumnCount() {
        return this.columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Persona persona;
        
        // Se obtiene la persona de la fila indicada
        persona = (Persona)(personas.get(rowIndex));
        switch (columnIndex)
        {
            case ID_INDEX:
                return persona.getId();
            case NOMBRES_INDEX:
                return persona.getNombres();
            case APELLIDOS_INDEX:
                return persona.getApellidos();
            case TELEFONO_INDEX:
                return persona.getTelefono();
            case DIRECCION_INDEX:
                return persona.getDireccion();
            case NUMERO_IDENTIFICACION_INDEX:
                return persona.getNumeroIdentificacion(); 
            default:
                return null;
        }
    }
    
    public Persona getPersona (int rowIndex){
        return this.personas.get(rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex)
        {
            case ID_INDEX:
                return this.tipoDatoColumna[ID_INDEX];
            case NOMBRES_INDEX:
                return this.tipoDatoColumna[NOMBRES_INDEX];
            case APELLIDOS_INDEX:
                return this.tipoDatoColumna[APELLIDOS_INDEX];
            case TELEFONO_INDEX:
                return this.tipoDatoColumna[TELEFONO_INDEX];
            case DIRECCION_INDEX:
                return this.tipoDatoColumna[DIRECCION_INDEX];
            case NUMERO_IDENTIFICACION_INDEX:
                return this.tipoDatoColumna[NUMERO_IDENTIFICACION_INDEX];
            default:
                return super.getColumnClass(columnIndex);
        }
    }

    @Override
    public String getColumnName(int column) {
        return this.columnNames[column];
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }
    
}
