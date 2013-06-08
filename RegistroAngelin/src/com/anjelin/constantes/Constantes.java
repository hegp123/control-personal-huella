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
    public static final int COMANDO_ELIMINAR = 2;
    public static final int COMANDO_MODIFICAR = 3;
    public static final int COMANDO_GUARDAR = 4;
    public final static int COMANDO_CANCELAR = 5;
    public final static int COMANDO_NUEVO = 6;
    public final static int TAMANO_IMAGEN_WIDTH = 40;
    public final static int TAMANO_IMAGEN_HEIGHT = 40;
    public static final String CONFIG_FILE_NAME = "conf.properties";
    public static boolean FRAME_PERSONAS_ACTIVA = false;
    public static final String RUTA_BIENVENIDO = "sonidos/bienvenido.wav";
    public static final String RUTA_ADIOS = "sonidos/adios.wav";
    public static final String NOMBRE_PARAM_REPORTE_VALORHORAEXTRA = "p_valorHoraExtra";
    public static final String NOMBRE_PARAM_REPORTE_FECHAINICIO = "p_fechaInicio";
    public static final String NOMBRE_PARAM_REPORTE_FECHAFINAL = "p_fechaFin";
    public static final String NOMBRE_PARAM_REPORTE_IDPERSONA = "p_idPersona";
    public static final String NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESLV = "p_numeroHorasLaboralesLV";
    public static final String NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESSABADO = "p_numeroHorasLaboralesS";
    public static final String NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESFESTIVO = "p_numeroHorasLaboralesFestivos";
    public static final String NOMBRE_PARAM_REPORTE_HORASALMUERZOLV = "p_horaAlmuerzoLV";
    public static final String RUTA_REPORTE_HORAS_EXTRAS = "/reportes/reportHorasExtras.jasper";
    public static final String KEY_RUTA_EXPORTACION_REPORTE_HORAS_EXTRAS = "rutaExportacionReportes";
    
    private static Properties properties = null;

    public static synchronized Properties getProperties() {

        if (properties == null) {
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
