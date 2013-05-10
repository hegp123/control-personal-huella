/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static Date getLastDay(Date fecha, boolean truncar) {

        Date ultimoDiaMes = null;

        if (fecha != null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            int maximoDia = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
            calendar.set(Calendar.DATE, maximoDia);
            if (truncar) {
                calendar.setTime(truncDate(calendar.getTime()));
            }
            ultimoDiaMes = calendar.getTime();
        }

        return ultimoDiaMes;

    }

    public static Date getFirstDay(Date fecha, boolean truncar) {

        Date primerDia = null;

        if (fecha != null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            if (truncar) {
                calendar.setTime(truncDate(calendar.getTime()));
            }
            primerDia = calendar.getTime();
        }

        return primerDia;

    }

    public static boolean fechasDelMismoDia(Date fechaBD, Date fechaHoy) {

        try {
            Calendar calBD = Calendar.getInstance();
            calBD.setTime(fechaBD);
            int diaBD = calBD.get(Calendar.DAY_OF_MONTH);
            int mesBD = calBD.get(Calendar.MONTH);
            int annoBD = calBD.get(Calendar.YEAR);

            Calendar calHoy = Calendar.getInstance();
            calHoy.setTime(fechaHoy);
            int diaHoy = calHoy.get(Calendar.DAY_OF_MONTH);
            int mesHoy = calHoy.get(Calendar.MONTH);
            int annoHoy = calHoy.get(Calendar.YEAR);

            if (diaBD == diaHoy && mesBD == mesHoy && annoBD == annoHoy) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    
    public static Date truncDate(Date fecha) {

        Date fechaTrunc = fecha;

        if (fecha != null) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            fechaTrunc = calendar.getTime();
        }

        return fechaTrunc;
    }
}
