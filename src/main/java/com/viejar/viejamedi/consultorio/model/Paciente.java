/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Administrador
 */
@Entity
@Table(name = "paciente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Paciente.findAll", query = "SELECT p FROM Paciente p")
    , @NamedQuery(name = "Paciente.findByPacienteHc", query = "SELECT p FROM Paciente p WHERE p.pacienteHc = :pacienteHc")
    , @NamedQuery(name = "Paciente.findByApellidoPaterno", query = "SELECT p FROM Paciente p WHERE p.apellidoPaterno = :apellidoPaterno")
    , @NamedQuery(name = "Paciente.findByApellidoMaterno", query = "SELECT p FROM Paciente p WHERE p.apellidoMaterno = :apellidoMaterno")
    , @NamedQuery(name = "Paciente.findByPrimerNombre", query = "SELECT p FROM Paciente p WHERE p.primerNombre = :primerNombre")
    , @NamedQuery(name = "Paciente.findBySegundoNombre", query = "SELECT p FROM Paciente p WHERE p.segundoNombre = :segundoNombre")
    , @NamedQuery(name = "Paciente.findByCedulaRuc", query = "SELECT p FROM Paciente p WHERE p.cedulaRuc = :cedulaRuc")
    , @NamedQuery(name = "Paciente.findByFechaNacimiento", query = "SELECT p FROM Paciente p WHERE p.fechaNacimiento = :fechaNacimiento")
    , @NamedQuery(name = "Paciente.findByDireccion", query = "SELECT p FROM Paciente p WHERE p.direccion = :direccion")
    , @NamedQuery(name = "Paciente.findByEmail", query = "SELECT p FROM Paciente p WHERE p.email = :email")
    , @NamedQuery(name = "Paciente.findByTelefono", query = "SELECT p FROM Paciente p WHERE p.telefono = :telefono")
    , @NamedQuery(name = "Paciente.findByCelular", query = "SELECT p FROM Paciente p WHERE p.celular = :celular")
    , @NamedQuery(name = "Paciente.findByEstado", query = "SELECT p FROM Paciente p WHERE p.estado = :estado")
    , @NamedQuery(name = "Paciente.findByFoto", query = "SELECT p FROM Paciente p WHERE p.foto = :foto")
    , @NamedQuery(name = "Paciente.findByCedulaEmail", query = "SELECT p FROM Paciente p WHERE p.cedulaRuc = :cedulaRuc OR p.email =:email")})
public class Paciente implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "apellido_paterno")
    private String apellidoPaterno;
    @Size(max = 2147483647)
    @Column(name = "apellido_materno")
    private String apellidoMaterno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "primer_nombre")
    private String primerNombre;
    @Size(max = 2147483647)
    @Column(name = "segundo_nombre")
    private String segundoNombre;
    @Size(max = 2147483647)
    @Column(name = "cedula_ruc")
    private String cedulaRuc;
    @Basic(optional = false)
    @NotNull()
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    @Size(max = 2147483647)
    @Column(name = "direccion")
    private String direccion;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
    @Size(max = 2147483647)
    @Column(name = "telefono")
    private String telefono;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 2147483647)
    @Column(name = "celular")
    private String celular;
    @Column(name = "telecom")
    private Integer telecom;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pacienteHc", fetch = FetchType.EAGER)
    private Set<Cita> citaSet;

    @JoinTable(name = "medico_paciente", joinColumns = {
        @JoinColumn(name = "paciente_hc", referencedColumnName = "paciente_hc")}, inverseJoinColumns = {
        @JoinColumn(name = "medico_id", referencedColumnName = "medico_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Medico> medicoSet;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "paciente_hc")
    private Integer pacienteHc;
    
    @Column(name = "estado")
    private Boolean estado;
    @Column(name = "foto")
    private BigInteger foto;

    @JoinColumn(name = "estado_civil", referencedColumnName = "parametro_id")
    @ManyToOne
    private Parametro estadoCivil;
    @JoinColumn(name = "grupo_sanguineo", referencedColumnName = "parametro_id")
    @ManyToOne
    private Parametro grupoSanguineo;

    @JoinColumn(name = "usuario_id", referencedColumnName = "usua_id")
    @OneToOne(fetch = FetchType.LAZY)
    private Usuario usuarioId;

    public Paciente() {
    }

    public Paciente(Integer pacienteHc) {
        this.pacienteHc = pacienteHc;
    }

    public Paciente(Integer pacienteHc, String apellidoPaterno, String primerNombre, Date fechaNacimiento) {
        this.pacienteHc = pacienteHc;
        this.apellidoPaterno = apellidoPaterno;
        this.primerNombre = primerNombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getPacienteHc() {
        return pacienteHc;
    }

    public void setPacienteHc(Integer pacienteHc) {
        this.pacienteHc = pacienteHc;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getCedulaRuc() {
        return cedulaRuc;
    }

    public void setCedulaRuc(String cedulaRuc) {
        this.cedulaRuc = cedulaRuc;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public BigInteger getFoto() {
        return foto;
    }

    public void setFoto(BigInteger foto) {
        this.foto = foto;
    }

    public Parametro getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(Parametro estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Parametro getTipoSangre() {
        return grupoSanguineo;
    }

    public void setTipoSangre(Parametro tipoSangre) {
        this.grupoSanguineo = tipoSangre;
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
        hash += (pacienteHc != null ? pacienteHc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Paciente)) {
            return false;
        }
        Paciente other = (Paciente) object;
        if ((this.pacienteHc == null && other.pacienteHc != null) || (this.pacienteHc != null && !this.pacienteHc.equals(other.pacienteHc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viejar.viejamedi.consultorio.model.Paciente[ pacienteHc=" + pacienteHc + " ]";
    }

    @XmlTransient
    public Set<Medico> getMedicoSet() {
        return medicoSet;
    }

    public void setMedicoSet(Set<Medico> medicoSet) {
        this.medicoSet = medicoSet;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public Integer getTelecom() {
        return telecom;
    }

    public void setTelecom(Integer telecom) {
        this.telecom = telecom;
    }

    @XmlTransient
    public Set<Cita> getCitaSet() {
        return citaSet;
    }

    public void setCitaSet(Set<Cita> citaSet) {
        this.citaSet = citaSet;
    }

}
