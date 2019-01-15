/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.api.resources;

import com.viejar.viejamedi.consultorio.model.Paciente;
import com.viejar.viejamedi.consultorio.service.PacienteService;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Administrador
 */
@Path("pacientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @EJB
    PacienteService pacienteService;

    @GET
    @RolesAllowed({"MEDICO"})
    public List<Paciente> getPacientes() {
        return pacienteService.findAll();
    }

    @GET
    @RolesAllowed({"MEDICO"})
    @Path("{cedulaOrEmail}")
    public Paciente getPacienteCedulaEmail(@PathParam("cedulaOrEmail") String cedulaOrEmail) {
        return pacienteService.findPacienteByCedulaOrEmail(cedulaOrEmail);
    }
    
    

}
