/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.gui.app;

import com.anjelin.constantes.Constantes;
import com.anjelin.dal.AbstractFacade;
import com.anjelin.dal.persona.PersonaDelegate;
import com.anjelin.modelo.Persona;
import com.toedter.calendar.JDateChooser;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

/**
 *
 * @author Admon
 */
public class FiltroGeneradorReporteHorasExtras extends JDialog {

    private List<Persona> personas = new ArrayList<Persona>();
    private JList listaPersonas;
    private Persona personaSeleccionada;
    private JDateChooser fechaInicial = new JDateChooser();
    private JDateChooser fechaFinal = new JDateChooser();
    private JButton botonGenerar = new JButton("Generar Reporte");

    public FiltroGeneradorReporteHorasExtras(Frame frame) {

        super(frame);
        setLayout(new GridLayout(0, 1));

        listaPersonas = new JList();
        listaPersonas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listaPersonas);
        

        listaPersonas.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {

                System.out.println(listaPersonas.getSelectedIndex());
                if (listaPersonas.getSelectedIndex() >= 0) {
                    personaSeleccionada = personas.get(listaPersonas.getSelectedIndex());
                } else {
                    personaSeleccionada = null;
                }

            }
        });

        setSize(500, 400);
        setModal(true);
        add(new JLabel("Seleccione una Persona y un rango de fechas para generar el reporte!"));
        add(scrollPane);
        
        JPanel panelFechas = new JPanel(new GridLayout(0, 2));
        
        panelFechas.add(new JLabel("Fecha Incial"));
        panelFechas.add(new JLabel("Fecha Final"));
        panelFechas.add(fechaInicial);
        panelFechas.add(fechaFinal);
        add(panelFechas);
        add(botonGenerar);

        botonGenerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validacionesFiltro()) {
                    try {
                        String formatoHora = "yyyy-MM-dd";
                        Map<String, Object> parametros = new HashMap<String, Object>();
                        parametros.put(Constantes.NOMBRE_PARAM_REPORTE_VALORHORAEXTRA, new Double(Constantes.getProperties().getProperty(Constantes.NOMBRE_PARAM_REPORTE_VALORHORAEXTRA)));
                        parametros.put(Constantes.NOMBRE_PARAM_REPORTE_FECHAINICIO, new SimpleDateFormat(formatoHora).format(fechaInicial.getDate()));
                        parametros.put(Constantes.NOMBRE_PARAM_REPORTE_FECHAFINAL, new SimpleDateFormat(formatoHora).format(fechaFinal.getDate()));
                        parametros.put(Constantes.NOMBRE_PARAM_REPORTE_IDPERSONA, getPersonaSeleccionada().getId());
                        parametros.put(Constantes.NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESLV, new Integer(Constantes.getProperties().getProperty(Constantes.NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESLV)));
                        parametros.put(Constantes.NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESSABADO, new Integer(Constantes.getProperties().getProperty(Constantes.NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESSABADO)));
                        parametros.put(Constantes.NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESFESTIVO, new Integer(Constantes.getProperties().getProperty(Constantes.NOMBRE_PARAM_REPORTE_NUMEROHORASLABORALESFESTIVO)));
                        parametros.put(Constantes.NOMBRE_PARAM_REPORTE_HORASALMUERZOLV, new Integer(Constantes.getProperties().getProperty(Constantes.NOMBRE_PARAM_REPORTE_HORASALMUERZOLV)));

                        abrirReporte(generarReporte(parametros));
                        dispose();
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(FiltroGeneradorReporteHorasExtras.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (JRException ex) {
                        Logger.getLogger(FiltroGeneradorReporteHorasExtras.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(FiltroGeneradorReporteHorasExtras.class.getName()).log(Level.SEVERE, null, ex);
                    }


                }


            }
        });

        cargarPersonas();
        listaPersonas.setSize(new Dimension((int)this.getSize().getWidth(),200));
        setLocationRelativeTo(null);
    }

    private void cargarPersonas() {

        try {
            PersonaDelegate delegate = new PersonaDelegate();
            setPersonas(delegate.buscarTodos());
            List<String> nombresPersonas = new ArrayList<String>();
            if (getPersonas() != null && !getPersonas().isEmpty()) {
                for (Persona persona : personas) {
                    nombresPersonas.add(persona.getApellidos() + " " + persona.getNombres());
                }
            }

            listaPersonas.setListData(nombresPersonas.toArray());


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private boolean validacionesFiltro() {

        Date fechaI = fechaInicial.getDate();
        Date fechaF = fechaFinal.getDate();
        if (getPersonaSeleccionada() == null || getPersonaSeleccionada().getId() == null) {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una persona!", "Error!", JOptionPane.ERROR_MESSAGE);
            return false;
        } else if (fechaI == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una Fecha de Inicio valida", "Error!", JOptionPane.ERROR_MESSAGE);
            fechaInicial.requestFocusInWindow();
            return false;
        } else if (fechaF == null) {
            JOptionPane.showMessageDialog(null, "Debe ingresar una Fecha Fin valida", "Error!", JOptionPane.ERROR_MESSAGE);
            fechaFinal.requestFocusInWindow();
            return false;
        } else if (fechaF.before(fechaI)) {
            JOptionPane.showMessageDialog(null, "La Fecha de Inicio debe ser menor a la Fecha Final", "Error!", JOptionPane.ERROR_MESSAGE);
            fechaInicial.requestFocusInWindow();
            return false;
        }


        return true;

    }

    private String generarReporte(Map<String, Object> parametros) throws ClassNotFoundException, JRException {


        String nombreDirectorio = Constantes.getProperties().getProperty(Constantes.KEY_RUTA_EXPORTACION_REPORTE_HORAS_EXTRAS) + "ReportesAnjelin\\";
        String fecha = new SimpleDateFormat("ddMMyyyyhhmmss").format(new Date());

        String nombreArchivo = "RHE_" + fecha + ".pdf";

        if (!new File(nombreDirectorio).exists()) {
            new File(nombreDirectorio).mkdirs();
        }

        System.out.println(nombreDirectorio + nombreArchivo);
        InputStream inputStream = FiltroGeneradorReporteHorasExtras.class.getResourceAsStream(Constantes.RUTA_REPORTE_HORAS_EXTRAS);
        if (inputStream == null) {
            throw new ClassNotFoundException("Archivo " + Constantes.RUTA_REPORTE_HORAS_EXTRAS + " no se encontró");
        }
        JRExporter exporter = null;
        AbstractFacade.EM.getTransaction().begin();
        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, AbstractFacade.getConnection());
        exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new File(nombreDirectorio + nombreArchivo));
        exporter.exportReport();
        AbstractFacade.EM.getTransaction().commit();

        return nombreDirectorio + nombreArchivo;

    }

    private void abrirReporte(String rutaReporte) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(rutaReporte);
                Desktop.getDesktop().open(myFile);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public JList getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(JList listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public JDateChooser getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(JDateChooser fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public JDateChooser getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(JDateChooser fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public JButton getBotonGenerar() {
        return botonGenerar;
    }

    public void setBotonGenerar(JButton botonGenerar) {
        this.botonGenerar = botonGenerar;
    }
}
