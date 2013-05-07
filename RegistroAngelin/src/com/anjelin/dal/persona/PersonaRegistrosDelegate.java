/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.dal.persona;

import com.anjelin.dal.AbstractFacade;
import static com.anjelin.dal.AbstractFacade.EM;
import com.anjelin.excepciones.EntradaPendienteDeSalidaConFechaInvalidaException;
import com.anjelin.modelo.Persona;
import com.anjelin.modelo.RegistroPersona;
import com.anjelin.modelo.TipoRegistroEnum;
import java.util.Date;
import java.util.List;
import com.anjelin.excepciones.EntradasPendientesException;
import com.anjelin.excepciones.SinEntradasRegistradasException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Admon
 */
public class PersonaRegistrosDelegate extends AbstractFacade<RegistroPersona> {

    public PersonaRegistrosDelegate() {
        super(RegistroPersona.class);
    }

    public void ingresarRegsitroPersona(Persona persona, TipoRegistroEnum tipoRegistro) throws EntradasPendientesException, SinEntradasRegistradasException, EntradaPendienteDeSalidaConFechaInvalidaException {

        List<RegistroPersona> entradasPendientes = buscarEntradasPendientes(persona);
        Date hora = new Date(System.currentTimeMillis());

        if (tipoRegistro.equals(tipoRegistro.ENTRADA)) {

            if (entradasPendientes == null || entradasPendientes.isEmpty()) {
                //Si no tiene entradas pendientes inserta la entrada
                try {
                    EM.getTransaction().begin();
                    RegistroPersona registroEntrada = new RegistroPersona();
                    registroEntrada.setFecha(hora);
                    registroEntrada.setHoraEntrada(hora);
                    registroEntrada.setAuto((short) 1);
                    registroEntrada.setContabilizado((short) 0);
                    registroEntrada.setIdPersona(persona);
                    registroEntrada.setObservaciones("Registro desde de App.");
                    crear(registroEntrada);
                    EM.flush();
                    EM.getTransaction().commit();
                } catch (Exception e) {
                    if (EM.getTransaction().isActive()) {
                        EM.getTransaction().rollback();
                    }
                }

            } else {
                throw new EntradasPendientesException(entradasPendientes);
            }


        } else if (tipoRegistro.equals(tipoRegistro.SALIDA)) {

            if (entradasPendientes == null || entradasPendientes.isEmpty()) {
                //Si no enconto registros con una entrada registrada y sin salida
                throw new SinEntradasRegistradasException();
            } else if (entradasPendientes.size() == 1) {
                RegistroPersona registro = entradasPendientes.get(0);

                if (fechasDelMismoDia(registro.getFecha(), hora)) {
                    //Si solo tiene un registro y es del mismo día. Se actauliza la salida 
                    try {
                        EM.getTransaction().begin();
                        registro.setHoraSalida(hora);
                        modificar(registro);
                        EM.flush();
                        EM.getTransaction().commit();
                    } catch (Exception e) {
                        if (EM.getTransaction().isActive()) {
                            EM.getTransaction().rollback();
                        }
                    }
                } else {
                    throw new EntradaPendienteDeSalidaConFechaInvalidaException(registro);
                }

            } else if (entradasPendientes.size() > 1) {
                throw new EntradasPendientesException(entradasPendientes);
            }

        }
    }

    public List<RegistroPersona> buscarEntradasPendientes(Persona persona) {
        return EM.createNamedQuery("RegistroPersona.findByPersonaActivas", RegistroPersona.class).setParameter("idPersona", persona).getResultList();
    }

    private boolean fechasDelMismoDia(Date fechaBD, Date fechaHoy) {

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

    public List<RegistroPersona> registrosPersonaPorMesyAno(Persona persona, int mes, int anno) {

        if(persona == null){
            return new ArrayList<RegistroPersona>(0);
        }
        
        return EM.createNamedQuery("RegistroPersona.findPorPersonaMesyAnno", RegistroPersona.class)
                .setParameter("idPersona", persona)
                .setParameter("anno", anno)
                .setParameter("mes", mes)
                .getResultList();
    }
}
