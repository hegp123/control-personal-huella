/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Admon
 */
@Embeddable
public class PersonaHuellaPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ID_PERSONA", nullable = false)
    private int idPersona;
    @Basic(optional = false)
    @Column(nullable = false)
    private int fingerIndex;

    public PersonaHuellaPK() {
    }

    public PersonaHuellaPK(int idPersona, int fingerIndex) {
        this.idPersona = idPersona;
        this.fingerIndex = fingerIndex;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public int getFingerIndex() {
        return fingerIndex;
    }

    public void setFingerIndex(int fingerIndex) {
        this.fingerIndex = fingerIndex;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPersona;
        hash += (int) fingerIndex;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PersonaHuellaPK)) {
            return false;
        }
        PersonaHuellaPK other = (PersonaHuellaPK) object;
        if (this.idPersona != other.idPersona) {
            return false;
        }
        if (this.fingerIndex != other.fingerIndex) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.anjelin.modelo.PersonaHuellaPK[ idPersona=" + idPersona + ", fingerIndex=" + fingerIndex + " ]";
    }
    
}
