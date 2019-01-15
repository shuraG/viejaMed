package com.viejar.viejamedi.consultorio.api.resources;

import com.viejar.viejamedi.consultorio.model.Paciente;
import com.viejar.viejamedi.consultorio.model.Usuario;
import com.viejar.viejamedi.consultorio.service.PacienteService;
import com.viejar.viejamedi.security.api.AuthenticatedUser;
import java.util.Set;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Dependent
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PacienteSubResource {

    @Inject
    @AuthenticatedUser
    Usuario authenticatedUser;

    @Inject
    PacienteService pacienteService;

    @GET
    @RolesAllowed({"MEDICO"})
    public Set<Paciente> getPacientes() {
        Set<Paciente> pacientes = authenticatedUser.getMedico().getPacienteSet();
        return pacientes;
    }

}
