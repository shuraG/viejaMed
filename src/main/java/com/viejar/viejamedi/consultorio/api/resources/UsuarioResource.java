/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.api.resources;

import com.viejar.viejamedi.consultorio.model.Usuario;
import com.viejar.viejamedi.security.api.AuthenticatedUser;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Administrador
 */
@Path("users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UsuarioResource {

   /* @Inject
    @AuthenticatedUser
    Usuario authenticatedUser;

    @GET
    @Path("me")
    @RolesAllowed({"MEDICO"})
    public Response getUser() {
        return Response.ok(authenticatedUser.getUsuaNombre()).build();
    }*/

}
