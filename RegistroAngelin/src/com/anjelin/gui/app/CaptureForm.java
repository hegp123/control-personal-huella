package com.anjelin.gui.app;

import com.anjelin.constantes.Constantes;
import com.anjelin.dal.persona.PersonaHuellaDelegate;
import com.anjelin.dal.persona.PersonaRegistrosDelegate;
import com.anjelin.excepciones.EntradaPendienteDeSalidaConFechaInvalidaException;
import com.anjelin.excepciones.EntradasPendientesException;
import com.anjelin.excepciones.SinEntradasRegistradasException;
import com.anjelin.modelo.Persona;
import com.anjelin.modelo.PersonaHuella;
import com.anjelin.modelo.TipoRegistroEnum;
import com.anjelin.util.StackTraceUtil;
import com.anjelin.util.TocarSonido;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.digitalpersona.onetouch.*;
import com.digitalpersona.onetouch.capture.*;
import com.digitalpersona.onetouch.capture.event.*;
import com.digitalpersona.onetouch.processing.*;
import com.digitalpersona.onetouch.verification.DPFPVerification;
import com.digitalpersona.onetouch.verification.DPFPVerificationResult;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CaptureForm  extends JDialog {

    private DPFPCapture capturer;
    private JLabel picture = new JLabel();
    private JTextField prompt = new JTextField();
    private JTextArea log = new JTextArea();
    private JTextField status = new JTextField();
    private TipoRegistroEnum tipoRegistro;
    private DPFPVerification verificator = DPFPGlobal.getVerificationFactory().createVerification();
    private List<PersonaHuella> huellasPersonas;
    
    public CaptureForm(Frame owner, TipoRegistroEnum tipoRegistro) {
        super(owner, true);
        this.tipoRegistro=tipoRegistro;        
        setTitle("Registro de "+ tipoRegistro.getCadena());
        setLayout(new BorderLayout());
        rootPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        picture.setPreferredSize(new Dimension(240, 280));
        picture.setBorder(BorderFactory.createLoweredBevelBorder());
        prompt.setFont(UIManager.getFont("Panel.font"));
        prompt.setEditable(false);
        prompt.setColumns(40);
        prompt.setMaximumSize(prompt.getPreferredSize());
        prompt.setBorder(
                BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Sugerencia:"),
                BorderFactory.createLoweredBevelBorder()));
        log.setColumns(40);
        log.setEditable(false);
        log.setFont(UIManager.getFont("Panel.font"));
        JScrollPane logpane = new JScrollPane(log);
        logpane.setBorder(
                BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0), "Estado:"),
                BorderFactory.createLoweredBevelBorder()));

        status.setEditable(false);
        status.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        status.setFont(UIManager.getFont("Panel.font"));

        JButton quit = new JButton("Cerrar");
        quit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });

        JPanel right = new JPanel(new BorderLayout());
        right.setBackground(Color.getColor("control"));
        right.add(prompt, BorderLayout.PAGE_START);
        right.add(logpane, BorderLayout.CENTER);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(Color.getColor("control"));
        center.add(right, BorderLayout.CENTER);
        center.add(picture, BorderLayout.LINE_START);
        center.add(status, BorderLayout.PAGE_END);

        JPanel bottom = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        bottom.setBackground(Color.getColor("control"));
        bottom.add(quit);

        setLayout(new BorderLayout());
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.PAGE_END);
        
        this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowOpened(WindowEvent e) {
                init();
                start();                
                super.windowOpened(e);
            }

            @Override
            public void windowClosed(WindowEvent e) {
                stop();
                super.windowClosed(e);
            }
            
            
        });

//        this.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentShown(ComponentEvent e) {
//                init();
//                start();
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                stop();
//            }
//        });        
        pack();
        setLocationRelativeTo(null);
    }


    protected void init() {
        System.out.println("Iniciando..........................");
        capturer = DPFPGlobal.getCaptureFactory().createCapture();
        System.out.println("Iniciando 1..........................");
        capturer.setPriority(DPFPCapturePriority.CAPTURE_PRIORITY_LOW);
        System.out.println("Iniciando 2..........................");
        capturer.addDataListener(new DPFPDataAdapter() {
            @Override
            public void dataAcquired(final DPFPDataEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        status.setText("La huella fue capturada");
                        //setPrompt("Scan the same fingerprint again.");
                        process(e.getSample());
                    }
                });
            }
        });
        capturer.addReaderStatusListener(new DPFPReaderStatusAdapter() {
            @Override
            public void readerConnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        status.setText("El lector de huellas está conectado y listo para se usado");
                    }
                });
            }

            @Override
            public void readerDisconnected(final DPFPReaderStatusEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        status.setText("El lector de huellas dactilares NO se encuentra conectado");
                    }
                });
            }
        });
        capturer.addSensorListener(new DPFPSensorAdapter() {
            @Override
            public void fingerTouched(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        status.setText("El lector de huellas dactilares fue tocado.");
                    }
                });
            }

            @Override
            public void fingerGone(final DPFPSensorEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        status.setText("El dedo se retira del lector de huellas digitales.");
                    }
                });
            }
        });
        capturer.addImageQualityListener(new DPFPImageQualityAdapter() {
            @Override
            public void onImageQuality(final DPFPImageQualityEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        if (e.getFeedback().equals(DPFPCaptureFeedback.CAPTURE_FEEDBACK_GOOD)) {
                            status.setText("La calidad de la Huella digital es BUENA.");
                        } else {
                            status.setText("La calidad de la Huella digital es MALA.");
                        }
                    }
                });
            }
        });
    }

    protected void process(DPFPSample sample) {
        // Draw fingerprint sample image.
        drawPicture(convertSampleToBitmap(sample));
        buscarHuella(sample);
    }

    protected void start() {        
        capturer.startCapture();
        setPrompt("Coloque el dedo en el lector de huellas, para registrar su "+ tipoRegistro.getCadena());
        makeReport("Cargando Huellas desde la BD.... Un momento por favor!");
        cargarListadoHuellas();
    }

    protected void stop() {
        System.out.println("Cerrando.........................");
        capturer.stopCapture();
    }

    public void setStatus(String string) {
        status.setText(string);
    }

    public void setPrompt(String string) {
        prompt.setText(string);
    }

    public void makeReport(String string) {
        log.append(string + "\n");
    }

    public void drawPicture(Image image) {
        picture.setIcon(new ImageIcon(
                image.getScaledInstance(picture.getWidth(), picture.getHeight(), Image.SCALE_SMOOTH)));
    }

    protected Image convertSampleToBitmap(DPFPSample sample) {
        return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    protected DPFPFeatureSet extractFeatures(DPFPSample sample, DPFPDataPurpose purpose) {
        DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();
        try {
            return extractor.createFeatureSet(sample, purpose);
        } catch (DPFPImageQualityException e) {
            return null;
        }
    }
    
    private void buscarHuella(DPFPSample sample) {
        
        try {
            if(this.huellasPersonas != null && !this.huellasPersonas.isEmpty()){
                for (PersonaHuella personaHuella : huellasPersonas) {
                    DPFPTemplate template = DPFPGlobal.getTemplateFactory().createTemplate(personaHuella.getTemplateHuella());
                    
                    // Process the sample and create a feature set for the enrollment purpose.
                    DPFPFeatureSet features = extractFeatures(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

                    // Check quality of the sample and start verification if it's good
                    if (features != null) {
                        // Compare the feature set with our template
                        DPFPVerificationResult result =verificator.verify(features, template);
                        if (result.isVerified()) {
                            validacionesRegistroPersona(personaHuella.getPersona());
                            makeReport("La huella fue VERIFICADA.");
                            break;
                        }
                    } 
                }
            }
            
        } catch (Exception e) {
            makeReport(StackTraceUtil.getStackTrace(e));
        }
        
    }  

    private void cargarListadoHuellas() {


        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    PersonaHuellaDelegate delegate = new PersonaHuellaDelegate();
                    huellasPersonas = delegate.buscarHuellasDePersonasActivas();
                    if (huellasPersonas == null || huellasPersonas.isEmpty()) {
                        setPrompt("No existen huellas resgitradas en el sistema de personas Activas.");
                        stop();
                    }
                    makeReport("Fin Cargue huellas!");
                } catch (Exception e) {
                    makeReport(StackTraceUtil.getStackTrace(e));
                }
            }
        });


    }

    private void validacionesRegistroPersona(Persona persona) {
        try {
            PersonaRegistrosDelegate delegate = new PersonaRegistrosDelegate();
            delegate.ingresarRegsitroPersona(persona, tipoRegistro);
            if (persona != null) {
                String nombrecompletoPersona = persona.getNombres() + " " + persona.getApellidos();
                if (tipoRegistro.equals(TipoRegistroEnum.ENTRADA)) {
                    makeReport("Bienvenido: " + nombrecompletoPersona);
                    cerrarVentana();
                } else if (tipoRegistro.equals(TipoRegistroEnum.SALIDA)) {
                    makeReport("Adiós: " + nombrecompletoPersona);
                    cerrarVentana();
                }
                tocarSonido();
            }            
        } catch (EntradasPendientesException ex) {
            JOptionPane.showMessageDialog(null, ex.getMensaje(), "Inconsistencia!", JOptionPane.WARNING_MESSAGE);
        } catch (SinEntradasRegistradasException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Inconsistencia!", JOptionPane.WARNING_MESSAGE);
        } catch (EntradaPendienteDeSalidaConFechaInvalidaException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Inconsistencia!", JOptionPane.WARNING_MESSAGE);
        } catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error!", JOptionPane.ERROR_MESSAGE);
        }
        
    }

    private void cerrarVentana() {
        try {
            //Se cierra despues de 2 segundos
            new Thread(new Runnable() {

                @Override
                public void run() {
                    try {
                        Thread.sleep(Long.parseLong(Constantes.getProperties().getProperty("tiempoCierreVentanaCaptura")));
                        CaptureForm.this.dispose();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(CaptureForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }).start();
            
        } catch (Exception e) {
        }
    }

    private void tocarSonido() {
        try {
            if(tipoRegistro.equals(TipoRegistroEnum.ENTRADA)){
                new Thread(new TocarSonido(Constantes.RUTA_BIENVENIDO)).start();
            }
            else if(tipoRegistro.equals(TipoRegistroEnum.SALIDA)){
                new Thread(new TocarSonido(Constantes.RUTA_ADIOS)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
