/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.app;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author Admon
 */
public class RelojPanel extends JPanel implements ActionListener {

    private Timer timer;
    private JLabel horaLabel = new JLabel();
    private JLabel fechaLabel = new JLabel();
    private JButton registroEntrada = new JButton();
    private JButton registroSalida = new JButton();

    public RelojPanel() {

        setLayout(new GridLayout(0, 1));
        add(fechaLabel);        
        JPanel botonesRegistro = new JPanel(new GridLayout(0, 2, 10,10));
        registroEntrada.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/entrada.png")));
        registroSalida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/salida.png")));      
        botonesRegistro.add(registroEntrada);
        botonesRegistro.add(registroSalida);
        add(botonesRegistro);
        add(horaLabel);
        
        horaLabel.setSize(100, 50);
        
        
        inicializarHora();
        timer = new Timer(500, this);
        timer.setRepeats(true);
        timer.start();

    }

    private void inicializarHora() {

        String strDateFormat = "hh:mm:ss a";
        SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
        Date fecha = new Date(System.currentTimeMillis());
        String fechaStr = getDiaDeLaSemana(fecha) + ", "
                + getDia(fecha) + " de "
                + getMes(fecha) + " de "
                + getAnno(fecha);

        fechaLabel.setText(fechaStr);
        fechaLabel.setFont(new Font("Dialog", Font.PLAIN, 50));
        fechaLabel.setHorizontalAlignment( SwingConstants.CENTER );
        horaLabel.setText(sdf.format(fecha));
        horaLabel.setFont(new Font("Dialog", Font.PLAIN, 100));
        horaLabel.setHorizontalAlignment( SwingConstants.CENTER );


    }
    
    
    public static String getDiaDeLaSemana(Date d){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(d);
        switch (cal.get(Calendar.DAY_OF_WEEK)){
            case Calendar.SUNDAY: 
                return "Domingo";
            case Calendar.MONDAY: 
                return "Lunes";
            case Calendar.TUESDAY: 
                return "Martes";
            case Calendar.WEDNESDAY: 
                return "Miércoles"; 
            case Calendar.THURSDAY: 
                return "Jueves";
            case Calendar.FRIDAY: 
                return "Viernes";
            case Calendar.SATURDAY: 
                return "Sábado";
        }
        
	return "";		
    }
    
    public static String getMes(Date d){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(d);      
        switch (cal.get(Calendar.MONTH)){
            case Calendar.JANUARY: 
                return "Enero";
            case Calendar.FEBRUARY: 
                return "Febrero";
            case Calendar.MARCH: 
                return "Marzo";
            case Calendar.APRIL: 
                return "Abril"; 
            case Calendar.MAY: 
                return "Mayo";
            case Calendar.JUNE: 
                return "Junio";
            case Calendar.JULY: 
                return "Julio";
            case Calendar.AUGUST: 
                return "Agosto";
            case Calendar.SEPTEMBER: 
                return "Septiembre"; 
            case Calendar.OCTOBER: 
                return "Octubre";
            case Calendar.NOVEMBER: 
                return "Noviembre";
            case Calendar.DECEMBER: 
                return "Diciembre";                
        }
        
	return "";		
    }    

    public static int getDia(Date d){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(d); 
        return cal.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getAnno(Date d){
	GregorianCalendar cal = new GregorianCalendar();
	cal.setTime(d); 
        return cal.get(Calendar.YEAR);
    }    
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource().equals(timer)) {
            inicializarHora();
        }

    }
    
 
}
