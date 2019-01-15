/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.security.api.resource;

import com.viejar.viejamedi.security.api.model.AuthenticationToken;
import com.viejar.viejamedi.consultorio.model.Usuario;
import com.viejar.viejamedi.security.api.model.UserCredentials;
import com.viejar.viejamedi.security.service.AuthenticationTokenService;
import com.viejar.viejamedi.security.service.UsernamePasswordValidator;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;

@Path("auth")
public class AuthenticationResource {

    @Context
    private SecurityContext securityContext;
    @Inject
    private UsernamePasswordValidator usernamePasswordValidator;
    @Inject
    private AuthenticationTokenService authenticationTokenService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PermitAll
    public Response authenticate(UserCredentials credentials) {        
        Usuario user = usernamePasswordValidator.validateCredentials(credentials.getUserName(), credentials.getPassword());        
        String token = authenticationTokenService.issueToken(user.getUsuaNombre(), user.getAuthorities());        
        AuthenticationToken authenticationToken = new AuthenticationToken();
        authenticationToken.setToken(token);
        return Response.ok(authenticationToken).build();
    }

}
