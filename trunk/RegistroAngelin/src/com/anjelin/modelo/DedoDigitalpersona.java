/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Admon
 */
@Entity
@Table(name = "dedos_digitalpersona", catalog = "controlpersonal", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DedoDigitalpersona.findAll", query = "SELECT d FROM DedoDigitalpersona d"),
    @NamedQuery(name = "DedoDigitalpersona.findByFingerindex", query = "SELECT d FROM DedoDigitalpersona d WHERE d.fingerindex = :fingerindex"),
    @NamedQuery(name = "DedoDigitalpersona.findByDescripcion", query = "SELECT d FROM DedoDigitalpersona d WHERE d.descripcion = :descripcion")})
public class DedoDigitalpersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer fingerindex;
    @Basic(optional = false)
    @Column(nullable = false, length = 20)
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dedoDigitalpersona", fetch = FetchType.LAZY)
    private List<PersonaHuella> personaHuellaList;

    public DedoDigitalpersona() {
    }

    public DedoDigitalpersona(Integer fingerindex) {
        this.fingerindex = fingerindex;
    }

    public DedoDigitalpersona(Integer fingerindex, String descripcion) {
        this.fingerindex = fingerindex;
        this.descripcion = descripcion;
    }

    public Integer getFingerindex() {
        return fingerindex;
    }

    public void setFingerindex(Integer fingerindex) {
        this.fingerindex = fingerindex;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public List<PersonaHuella> getPersonaHuellaList() {
        return personaHuellaList;
    }

    public void setPersonaHuellaList(List<PersonaHuella> personaHuellaList) {
        this.personaHuellaList = personaHuellaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fingerindex != null ? fingerindex.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DedoDigitalpersona)) {
            return false;
        }
        DedoDigitalpersona other = (DedoDigitalpersona) object;
        if ((this.fingerindex == null && other.fingerindex != null) || (this.fingerindex != null && !this.fingerindex.equals(other.fingerindex))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.anjelin.modelo.DedoDigitalpersona[ fingerindex=" + fingerindex + " ]";
    }
    
}
