/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.api.resources;

import com.viejar.viejamedi.consultorio.model.Cita;
import com.viejar.viejamedi.consultorio.model.Usuario;
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
public class CitaSubResource {

    @Inject
    @AuthenticatedUser
    Usuario authenticatedUser;

    @GET
    @RolesAllowed({"MEDICO"})
    public Set<Cita> getCitas() {
        Set<Cita> citas = authenticatedUser.getMedico().getCitaSet();
        return citas;
    }

}
