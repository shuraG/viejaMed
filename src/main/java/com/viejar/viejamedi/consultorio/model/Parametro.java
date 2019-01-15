/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.model;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "parametro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Parametro.findAll", query = "SELECT p FROM Parametro p")
    , @NamedQuery(name = "Parametro.findByValorMinimo", query = "SELECT p FROM Parametro p WHERE p.valorMinimo = :valorMinimo")
    , @NamedQuery(name = "Parametro.findByValorMaximo", query = "SELECT p FROM Parametro p WHERE p.valorMaximo = :valorMaximo")
    , @NamedQuery(name = "Parametro.findByDisplay", query = "SELECT p FROM Parametro p WHERE p.display = :display")
    , @NamedQuery(name = "Parametro.findByDescripcion", query = "SELECT p FROM Parametro p WHERE p.descripcion = :descripcion")
    , @NamedQuery(name = "Parametro.findByOrden", query = "SELECT p FROM Parametro p WHERE p.orden = :orden")
    , @NamedQuery(name = "Parametro.findByParametroTipo", query = "SELECT p FROM Parametro p WHERE p.parametroTipo = :parametroTipo")
    , @NamedQuery(name = "Parametro.findByParametroId", query = "SELECT p FROM Parametro p WHERE p.parametroId = :parametroId")})
public class Parametro implements Serializable {

    @Size(max = 2147483647)
    @Column(name = "valor_minimo")
    private String valorMinimo;
    @Size(max = 2147483647)
    @Column(name = "valor_maximo")
    private String valorMaximo;
    @Size(max = 2147483647)
    @Column(name = "display")
    private String display;
    @Basic(optional = false)
    @NotNull()
    @Size(min = 1, max = 2147483647)
    @Column(name = "descripcion")
    private String descripcion;
    @Size(max = 2147483647)
    @Column(name = "orden")
    private String orden;
    @Size(max = 2147483647)
    @Column(name = "parametro_tipo")
    private String parametroTipo;
    @OneToMany(mappedBy = "estadoCivil")
    private Set<Paciente> pacienteSet;
    @OneToMany(mappedBy = "grupoSanguineo")
    private Set<Paciente> pacienteSet1;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "parametro_id")
    private String parametroId;

    public Parametro() {
    }

    public Parametro(String parametroId) {
        this.parametroId = parametroId;
    }

    public Parametro(String parametroId, String descripcion) {
        this.parametroId = parametroId;
        this.descripcion = descripcion;
    }

    public String getValorMinimo() {
        return valorMinimo;
    }

    public void setValorMinimo(String valorMinimo) {
        this.valorMinimo = valorMinimo;
    }

    public String getValorMaximo() {
        return valorMaximo;
    }

    public void setValorMaximo(String valorMaximo) {
        this.valorMaximo = valorMaximo;
    }


    public String getParametroTipo() {
        return parametroTipo;
    }

    public void setParametroTipo(String parametroTipo) {
        this.parametroTipo = parametroTipo;
    }

    public String getParametroId() {
        return parametroId;
    }

    public void setParametroId(String parametroId) {
        this.parametroId = parametroId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (parametroId != null ? parametroId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Parametro)) {
            return false;
        }
        Parametro other = (Parametro) object;
        if ((this.parametroId == null && other.parametroId != null) || (this.parametroId != null && !this.parametroId.equals(other.parametroId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viejar.viejamedi.consultorio.model.Parametro[ parametroId=" + parametroId + " ]";
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }

    @XmlTransient
    public Set<Paciente> getPacienteSet() {
        return pacienteSet;
    }

    public void setPacienteSet(Set<Paciente> pacienteSet) {
        this.pacienteSet = pacienteSet;
    }

    @XmlTransient
    public Set<Paciente> getPacienteSet1() {
        return pacienteSet1;
    }

    public void setPacienteSet1(Set<Paciente> pacienteSet1) {
        this.pacienteSet1 = pacienteSet1;
    }
    
}
