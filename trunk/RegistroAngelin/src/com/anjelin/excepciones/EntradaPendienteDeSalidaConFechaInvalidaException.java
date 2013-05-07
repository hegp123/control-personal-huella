/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.excepciones;

import static com.anjelin.gui.app.RelojPanel.getAnno;
import static com.anjelin.gui.app.RelojPanel.getDia;
import static com.anjelin.gui.app.RelojPanel.getDiaDeLaSemana;
import static com.anjelin.gui.app.RelojPanel.getMes;
import com.anjelin.modelo.RegistroPersona;
import java.util.Date;

/**
 *
 * @author Admon
 */
public class EntradaPendienteDeSalidaConFechaInvalidaException extends Exception {
    
    private final RegistroPersona registro;



    public EntradaPendienteDeSalidaConFechaInvalidaException(RegistroPersona registro) {
        this.registro = registro;
    }
    
    
    public String getMensaje(){
        
        Date fecha = this.registro.getFecha();
        String mensaje = this.registro.getIdPersona().getNombres() + ", el día "+ 
                getDiaDeLaSemana(fecha) + getDia(fecha) + " de "+ getMes(fecha) + " de " + getAnno(fecha) + 
                " registro su entrada pero nunca registro su salida.\n Solicite al administrador que ingrese su salida manualmente.";
        
        return mensaje;
    }
}
