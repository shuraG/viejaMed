/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.model;

import com.viejar.viejamedi.security.model.Authority;
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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByUsuaId", query = "SELECT u FROM Usuario u WHERE u.usuaId = :usuaId")
    , @NamedQuery(name = "Usuario.findByUsuaNombre", query = "SELECT u FROM Usuario u WHERE u.usuaNombre = :usuaNombre")
    , @NamedQuery(name = "Usuario.findByUsuaPassword", query = "SELECT u FROM Usuario u WHERE u.usuaPassword = :usuaPassword")
    , @NamedQuery(name = "Usuario.findByUsuaEstado", query = "SELECT u FROM Usuario u WHERE u.usuaEstado = :usuaEstado")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usua_id")
    private Integer usuaId;
    @Size(max = 30)
    @Column(name = "usua_nombre")
    private String usuaNombre;
    @Size(max = 150)
    @Column(name = "usua_password")
    private String usuaPassword;
    @Column(name = "usua_estado")
    private Boolean usuaEstado;

    @OneToOne(mappedBy = "usuarioId", cascade = CascadeType.ALL, 
              fetch = FetchType.LAZY, optional = false)
    private Paciente paciente;

    @OneToOne(mappedBy = "usuarioId", cascade = CascadeType.ALL, 
              fetch = FetchType.LAZY, optional = false)
    private Medico medico;

    @JoinColumn(name = "empresa_id", referencedColumnName = "empresa_id")
    @ManyToOne
    private Empresa empresaId;

    @ManyToMany(mappedBy = "usuarioSet", fetch = FetchType.EAGER)
    private Set<Authority> authoritySet;

    public Usuario() {
    }

    public Usuario(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public Integer getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public String getUsuaNombre() {
        return usuaNombre;
    }

    public void setUsuaNombre(String usuaNombre) {
        this.usuaNombre = usuaNombre;
    }

    public String getUsuaPassword() {
        return usuaPassword;
    }

    public void setUsuaPassword(String usuaPassword) {
        this.usuaPassword = usuaPassword;
    }

    public Boolean getUsuaEstado() {
        return usuaEstado;
    }

    public void setUsuaEstado(Boolean usuaEstado) {
        this.usuaEstado = usuaEstado;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public Empresa getEmpresaId() {
        return empresaId;
    }

    public void setEmpresaId(Empresa empresaId) {
        this.empresaId = empresaId;
    }

    @XmlTransient
    public Set<Authority> getAuthorities() {
        return authoritySet;
    }

    public void setAuthoritySet(Set<Authority> authoritySet) {
        this.authoritySet = authoritySet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuaId != null ? usuaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuaId == null && other.usuaId != null) || (this.usuaId != null && !this.usuaId.equals(other.usuaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viejar.viejamedi.consultorio.model.Usuario[ usuaId=" + usuaId + " ]";
    }

}
