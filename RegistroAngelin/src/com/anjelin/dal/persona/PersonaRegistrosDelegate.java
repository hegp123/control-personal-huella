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
import com.anjelin.util.DateUtils;
import java.util.ArrayList;

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

                if (DateUtils.fechasDelMismoDia(registro.getFecha(), hora)) {
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
    
    public List<RegistroPersona> registrosPersonaPorRangoFechas(Persona persona, Date fechaInicio, Date fechaFinal) {

        if(persona == null){
            return new ArrayList<RegistroPersona>(0);
        }

        return EM.createNamedQuery("RegistroPersona.findByRangoFechas", RegistroPersona.class)
                .setParameter("idPersona", persona)
                .setParameter("fechaInicio", fechaInicio)
                .setParameter("fechaFin", fechaFinal)
                .getResultList();
    }

    @Override
    public void eliminar(RegistroPersona entity) throws Exception{
        try {
            EM.getTransaction().begin();
            super.eliminar(entity); //To change body of generated methods, choose Tools | Templates.
            EM.getTransaction().commit();
        } catch (Exception e) {
            if (EM.getTransaction().isActive()) {
                EM.getTransaction().rollback();
            }
            throw e;
        }

    }
    
    
    
}
