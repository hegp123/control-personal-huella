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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Admon
 */
public class EntradasPendientesException extends Exception {

    private String mensaje = "Las siguientes entradas aún están pendientes de registrar sus salidas: \n\n";
    private List<RegistroPersona> entradasPendientes;


    public EntradasPendientesException(List<RegistroPersona> entradasPendientes) {
        this.entradasPendientes = entradasPendientes;
    }
    
    public String getMensaje(){
        
        String formatoHora = "hh:mm:ss a";
        
        for (RegistroPersona registroPersona : entradasPendientes) {
            
            Date fecha = registroPersona.getFecha();
            String fechaStr = getDiaDeLaSemana(fecha) + ", " + getDia(fecha) + " de "+ getMes(fecha) + " de " + getAnno(fecha) + " ";            
            
            mensaje = mensaje.concat(fechaStr);
            mensaje = mensaje.concat(new SimpleDateFormat(formatoHora).format(registroPersona.getHoraEntrada()) + "\n");
        }
        
        mensaje = mensaje.concat("\nComuniquese con su Administrador, para que ingrese los regsitros manualmente.");
        
        return mensaje;
    }
    
    
}
