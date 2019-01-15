/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "cita")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c")
    , @NamedQuery(name = "Cita.findByCitaId", query = "SELECT c FROM Cita c WHERE c.citaId = :citaId")
    , @NamedQuery(name = "Cita.findByCreado", query = "SELECT c FROM Cita c WHERE c.creado = :creado")
    , @NamedQuery(name = "Cita.findByFechaAtendido", query = "SELECT c FROM Cita c WHERE c.fechaAtendido = :fechaAtendido")
    , @NamedQuery(name = "Cita.findByEstado", query = "SELECT c FROM Cita c WHERE c.estado = :estado")
    , @NamedQuery(name = "Cita.findByCreadoPor", query = "SELECT c FROM Cita c WHERE c.creadoPor = :creadoPor")
    , @NamedQuery(name = "Cita.findByRazon", query = "SELECT c FROM Cita c WHERE c.razon = :razon")
    , @NamedQuery(name = "Cita.findByConsultorioId", query = "SELECT c FROM Cita c WHERE c.consultorioId = :consultorioId")
    , @NamedQuery(name = "Cita.findByDescripcion", query = "SELECT c FROM Cita c WHERE c.descripcion = :descripcion")
    , @NamedQuery(name = "Cita.findByCitaTipo", query = "SELECT c FROM Cita c WHERE c.citaTipo = :citaTipo")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cita_id")
    private Integer citaId;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "creado")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creado;
    
    @Column(name = "fecha_atendido")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaAtendido;
    
    @Column(name = "estado")
    private Integer estado;
    @Column(name = "creado_por")
    private Integer creadoPor;
    @Column(name = "razon")
    private Integer razon;
    @Column(name = "consultorio_id")
    private Integer consultorioId;
    @Size(max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "cita_tipo")
    private Integer citaTipo;
    
    @JoinColumn(name = "medico_id", referencedColumnName = "medico_id")
    @ManyToOne(optional = false)
    private Medico medicoId;
    
    @JoinColumn(name = "paciente_hc", referencedColumnName = "paciente_hc")
    @ManyToOne(optional = false)
    private Paciente pacienteHc;

    public Cita() {
    }

    public Cita(Integer citaId) {
        this.citaId = citaId;
    }

    public Cita(Integer citaId, Date creado) {
        this.citaId = citaId;
        this.creado = creado;
    }

    public Integer getCitaId() {
        return citaId;
    }

    public void setCitaId(Integer citaId) {
        this.citaId = citaId;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getFechaAtendido() {
        return fechaAtendido;
    }

    public void setFechaAtendido(Date fechaAtendido) {
        this.fechaAtendido = fechaAtendido;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Integer getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(Integer creadoPor) {
        this.creadoPor = creadoPor;
    }

    public Integer getRazon() {
        return razon;
    }

    public void setRazon(Integer razon) {
        this.razon = razon;
    }

    public Integer getConsultorioId() {
        return consultorioId;
    }

    public void setConsultorioId(Integer consultorioId) {
        this.consultorioId = consultorioId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCitaTipo() {
        return citaTipo;
    }

    public void setCitaTipo(Integer citaTipo) {
        this.citaTipo = citaTipo;
    }

    public Medico getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Medico medicoId) {
        this.medicoId = medicoId;
    }

    public Paciente getPacienteHc() {
        return pacienteHc;
    }

    public void setPacienteHc(Paciente pacienteHc) {
        this.pacienteHc = pacienteHc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (citaId != null ? citaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.citaId == null && other.citaId != null) || (this.citaId != null && !this.citaId.equals(other.citaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viejar.viejamedi.consultorio.model.Cita[ citaId=" + citaId + " ]";
    }
    
}
