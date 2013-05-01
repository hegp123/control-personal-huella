/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admon
 */
@Entity
@Table(name = "persona_huellas", catalog = "controlpersonal", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PersonaHuella.findAll", query = "SELECT p FROM PersonaHuella p"),
    @NamedQuery(name = "PersonaHuella.findByIdPersona", query = "SELECT p FROM PersonaHuella p WHERE p.personaHuellaPK.idPersona = :idPersona"),
    @NamedQuery(name = "PersonaHuella.findByFingerIndex", query = "SELECT p FROM PersonaHuella p WHERE p.personaHuellaPK.fingerIndex = :fingerIndex")})
public class PersonaHuella implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PersonaHuellaPK personaHuellaPK;
    @Basic(optional = false)
    @Lob
    @Column(name = "TEMPLATE_HUELLA", nullable = false)
    private byte[] templateHuella;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona persona;
    @JoinColumn(name = "fingerIndex", referencedColumnName = "fingerindex", nullable = false, insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DedoDigitalpersona dedoDigitalpersona;

    public PersonaHuella() {
    }

    public PersonaHuella(PersonaHuellaPK personaHuellaPK) {
        this.personaHuellaPK = personaHuellaPK;
    }

    public PersonaHuella(PersonaHuellaPK personaHuellaPK, byte[] templateHuella) {
        this.personaHuellaPK = personaHuellaPK;
        this.templateHuella = templateHuella;
    }

    public PersonaHuella(int idPersona, int fingerIndex) {
        this.personaHuellaPK = new PersonaHuellaPK(idPersona, fingerIndex);
    }

    public PersonaHuellaPK getPersonaHuellaPK() {
        return personaHuellaPK;
    }

    public void setPersonaHuellaPK(PersonaHuellaPK personaHuellaPK) {
        this.personaHuellaPK = personaHuellaPK;
    }

    public byte[] getTemplateHuella() {
        return templateHuella;
    }

    public void setTemplateHuella(byte[] templateHuella) {
        this.templateHuella = templateHuella;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public DedoDigitalpersona getDedoDigitalpersona() {
        return dedoDigitalpersona;
    }

    public void setDedoDigitalpersona(DedoDigitalpersona dedoDigitalpersona) {
        this.dedoDigitalpersona = dedoDigitalpersona;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (personaHuellaPK != null ? personaHuellaPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaHuella)) {
            return false;
        }
        PersonaHuella other = (PersonaHuella) object;
        if ((this.personaHuellaPK == null && other.personaHuellaPK != null) || (this.personaHuellaPK != null && !this.personaHuellaPK.equals(other.personaHuellaPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.anjelin.modelo.PersonaHuella[ personaHuellaPK=" + personaHuellaPK + " ]";
    }
    
}
