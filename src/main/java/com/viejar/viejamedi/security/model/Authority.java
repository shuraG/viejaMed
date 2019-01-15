/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.security.model;

import com.viejar.viejamedi.consultorio.model.Usuario;
import com.fasterxml.jackson.annotation.JsonValue;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "authority")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Authority.findAll", query = "SELECT a FROM Authority a")
    , @NamedQuery(name = "Authority.findByAuthorityId", query = "SELECT a FROM Authority a WHERE a.authorityId = :authorityId")
    , @NamedQuery(name = "Authority.findByAuthorityNombre", query = "SELECT a FROM Authority a WHERE a.authorityNombre = :authorityNombre")})
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "authority_id")
    private Integer authorityId;
    @Size(max = 30)
    @Column(name = "authority_nombre")
    private String authorityNombre;
    @JoinTable(name = "usuario_authority", joinColumns = {
        @JoinColumn(name = "authority_id", referencedColumnName = "authority_id")}, inverseJoinColumns = {
        @JoinColumn(name = "usuario_id", referencedColumnName = "usua_id")})
    @ManyToMany
    private Set<Usuario> usuarioSet;

    public Authority() {
    }

    public Authority(Integer authorityId) {
        this.authorityId = authorityId;
    }
    
    public Integer getAuthorityId() {
        return authorityId;
    }

    public void setAuthorityId(Integer authorityId) {
        this.authorityId = authorityId;
    }
    @JsonValue
    public String getAuthorityNombre() {
        return authorityNombre;
    }

    public void setAuthorityNombre(String authorityNombre) {
        this.authorityNombre = authorityNombre;
    }

    @XmlTransient
    public Set<Usuario> getUsuarioSet() {
        return usuarioSet;
    }

    public void setUsuarioSet(Set<Usuario> usuarioSet) {
        this.usuarioSet = usuarioSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authorityId != null ? authorityId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authority)) {
            return false;
        }
        Authority other = (Authority) object;
        if ((this.authorityId == null && other.authorityId != null) || (this.authorityId != null && !this.authorityId.equals(other.authorityId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //return "com.viejar.loginviejar.mdl.Authority[ authorityId=" + authorityId + " ]";
        return this.authorityNombre;
    }

}
