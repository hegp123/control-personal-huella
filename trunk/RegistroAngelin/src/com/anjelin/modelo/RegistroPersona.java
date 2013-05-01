/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.anjelin.modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Admon
 */
@Entity
@Table(name = "registros_persona", catalog = "controlpersonal", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RegistroPersona.findAll", query = "SELECT r FROM RegistroPersona r"),
    @NamedQuery(name = "RegistroPersona.findById", query = "SELECT r FROM RegistroPersona r WHERE r.id = :id"),
    @NamedQuery(name = "RegistroPersona.findByFecha", query = "SELECT r FROM RegistroPersona r WHERE r.fecha = :fecha"),
    @NamedQuery(name = "RegistroPersona.findByHora", query = "SELECT r FROM RegistroPersona r WHERE r.hora = :hora"),
    @NamedQuery(name = "RegistroPersona.findByAuto", query = "SELECT r FROM RegistroPersona r WHERE r.auto = :auto"),
    @NamedQuery(name = "RegistroPersona.findByContabilizado", query = "SELECT r FROM RegistroPersona r WHERE r.contabilizado = :contabilizado")})
public class RegistroPersona implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Basic(optional = false)
    @Column(nullable = false)
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Column(nullable = false)
    private short auto;
    @Basic(optional = false)
    @Column(nullable = false)
    private short contabilizado;
    @Lob
    @Column(length = 65535)
    private String observaciones;
    @JoinColumn(name = "ID_TIPO_REGISTRO", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TipoRegistro idTipoRegistro;
    @JoinColumn(name = "ID_PERSONA", referencedColumnName = "ID", nullable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Persona idPersona;

    public RegistroPersona() {
    }

    public RegistroPersona(Integer id) {
        this.id = id;
    }

    public RegistroPersona(Integer id, Date fecha, Date hora, short auto, short contabilizado) {
        this.id = id;
        this.fecha = fecha;
        this.hora = hora;
        this.auto = auto;
        this.contabilizado = contabilizado;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public short getAuto() {
        return auto;
    }

    public void setAuto(short auto) {
        this.auto = auto;
    }

    public short getContabilizado() {
        return contabilizado;
    }

    public void setContabilizado(short contabilizado) {
        this.contabilizado = contabilizado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public TipoRegistro getIdTipoRegistro() {
        return idTipoRegistro;
    }

    public void setIdTipoRegistro(TipoRegistro idTipoRegistro) {
        this.idTipoRegistro = idTipoRegistro;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
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
        if (!(object instanceof RegistroPersona)) {
            return false;
        }
        RegistroPersona other = (RegistroPersona) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.anjelin.modelo.RegistroPersona[ id=" + id + " ]";
    }
    
}
