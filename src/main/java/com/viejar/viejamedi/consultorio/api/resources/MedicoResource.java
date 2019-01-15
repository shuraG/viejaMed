/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.api.resources;

import com.viejar.viejamedi.consultorio.model.Usuario;
import com.viejar.viejamedi.security.api.AuthenticatedUser;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("medicos")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MedicoResource {

    @Inject
    @AuthenticatedUser
    Usuario authenticatedUser;

    @Inject
    PacienteSubResource pacienteSubResource;

    @Inject
    CitaSubResource citaSubResource;

    @GET
    @Path("me")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getMedico() {
        return Response.ok(authenticatedUser.getMedico().getNombre()).build();
    }

    @Path("/me/pacientes")
    public PacienteSubResource getMedicoPacientes() {
        return pacienteSubResource;
    }

    @Path("/me/citas")
    public CitaSubResource getMedicoCitas() {
        return citaSubResource;
    }
}
