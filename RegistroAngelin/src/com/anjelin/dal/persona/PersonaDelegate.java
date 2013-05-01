/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.dal.persona;

import com.anjelin.dal.AbstractFacade;
import com.anjelin.modelo.Persona;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 *
 * @author Admon
 */
public class PersonaDelegate extends AbstractFacade<Persona>{

   public PersonaDelegate(){
       super(Persona.class);
   }    

}
