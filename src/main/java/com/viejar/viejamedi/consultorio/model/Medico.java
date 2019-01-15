/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "medico")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medico.findAll", query = "SELECT m FROM Medico m")
    , @NamedQuery(name = "Medico.findByMedicoId", query = "SELECT m FROM Medico m WHERE m.medicoId = :medicoId")
    , @NamedQuery(name = "Medico.findByNombre", query = "SELECT m FROM Medico m WHERE m.nombre = :nombre")})
public class Medico implements Serializable {

    @Size(max = 2147483647)
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicoId", fetch = FetchType.EAGER)
    private Set<Cita> citaSet;

    @ManyToMany(mappedBy = "medicoSet", fetch = FetchType.EAGER)
    private Set<Paciente> pacienteSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "medico_id")
    private Integer medicoId;

    @JoinColumn(name = "usuario_id", referencedColumnName = "usua_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuarioId;

    public Medico() {
    }

    public Medico(Integer medicoId) {
        this.medicoId = medicoId;
    }

    public Integer getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Integer medicoId) {
        this.medicoId = medicoId;
    }

    @XmlTransient
    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicoId != null ? medicoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medico)) {
            return false;
        }
        Medico other = (Medico) object;
        if ((this.medicoId == null && other.medicoId != null) || (this.medicoId != null && !this.medicoId.equals(other.medicoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viejar.viejamedi.consultorio.model.Medico[ medicoId=" + medicoId + " ]";
    }

    @XmlTransient
    public Set<Paciente> getPacienteSet() {
        return pacienteSet;
    }

    public void setPacienteSet(Set<Paciente> pacienteSet) {
        this.pacienteSet = pacienteSet;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Set<Cita> getCitaSet() {
        return citaSet;
    }

    public void setCitaSet(Set<Cita> citaSet) {
        this.citaSet = citaSet;
    }

}
