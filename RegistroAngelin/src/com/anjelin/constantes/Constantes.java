/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.constantes;

import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Admon
 */
public class Constantes {
    
    public static final int COMANDO_BUSCAR = 1;
    public static final int COMANDO_ELIMINAR = 2 ;
    public static final int COMANDO_MODIFICAR = 3;    
    public static final int COMANDO_GUARDAR = 4; 
    public final static int COMANDO_CANCELAR = 5;
    public final static int COMANDO_NUEVO = 6;
    public final static int TAMANO_IMAGEN_WIDTH   = 40;
    public final static int TAMANO_IMAGEN_HEIGHT   = 40; 
    public static final String CONFIG_FILE_NAME="conf.properties";
    
    private static Properties properties = null;

    public static synchronized Properties getProperties() {
        
        if(properties == null){
            properties = new Properties();
            try {
                properties.load(Constantes.class.getClassLoader().getResourceAsStream(CONFIG_FILE_NAME));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return properties;
    }
    
}
