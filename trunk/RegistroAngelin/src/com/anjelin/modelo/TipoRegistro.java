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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipos_registro", catalog = "controlpersonal", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoRegistro.findAll", query = "SELECT t FROM TipoRegistro t"),
    @NamedQuery(name = "TipoRegistro.findById", query = "SELECT t FROM TipoRegistro t WHERE t.id = :id"),
    @NamedQuery(name = "TipoRegistro.findByDesscripcion", query = "SELECT t FROM TipoRegistro t WHERE t.desscripcion = :desscripcion")})
public class TipoRegistro implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String desscripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoRegistro", fetch = FetchType.LAZY)
    private List<RegistroPersona> registroPersonaList;

    public TipoRegistro() {
    }

    public TipoRegistro(Integer id) {
        this.id = id;
    }

    public TipoRegistro(Integer id, String desscripcion) {
        this.id = id;
        this.desscripcion = desscripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDesscripcion() {
        return desscripcion;
    }

    public void setDesscripcion(String desscripcion) {
        this.desscripcion = desscripcion;
    }

    @XmlTransient
    public List<RegistroPersona> getRegistroPersonaList() {
        return registroPersonaList;
    }

    public void setRegistroPersonaList(List<RegistroPersona> registroPersonaList) {
        this.registroPersonaList = registroPersonaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoRegistro)) {
            return false;
        }
        TipoRegistro other = (TipoRegistro) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.anjelin.modelo.TipoRegistro[ id=" + id + " ]";
    }
    
}
