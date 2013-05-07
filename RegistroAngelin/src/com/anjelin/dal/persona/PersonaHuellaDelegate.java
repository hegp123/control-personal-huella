/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.dal.persona;

import com.anjelin.dal.AbstractFacade;
import static com.anjelin.dal.AbstractFacade.EM;
import com.anjelin.modelo.PersonaHuella;
import com.anjelin.modelo.RegistroPersona;
import java.util.List;

/**
 *
 * @author Admon
 */
public class PersonaHuellaDelegate extends AbstractFacade<PersonaHuella>{

   public PersonaHuellaDelegate(){
       super(PersonaHuella.class);
   }
   
    public List<PersonaHuella> buscarHuellasDePersonasActivas() {
        return EM.createNamedQuery("PersonaHuella.findAllPersonasActivas", PersonaHuella.class).getResultList();
    }
}
