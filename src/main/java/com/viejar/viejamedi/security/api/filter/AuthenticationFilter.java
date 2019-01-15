/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.security.api.filter;

import com.viejar.viejamedi.consultorio.service.UsuarioService;
import com.viejar.viejamedi.security.api.AuthenticatedUser;
import com.viejar.viejamedi.consultorio.model.Usuario;
import com.viejar.viejamedi.security.api.AuthenticatedUserDetails;
import com.viejar.viejamedi.security.api.AuthenticationTokenDetails;
import com.viejar.viejamedi.security.api.TokenBasedSecurityContext;
import com.viejar.viejamedi.security.service.AuthenticationTokenService;
import java.io.IOException;
import javax.annotation.Priority;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.ext.Provider;
import javax.enterprise.event.Event;

@Provider
@Dependent
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @Inject
    @AuthenticatedUser
    Event<String> userAuthenticatedEvent;

    @Inject
    private AuthenticationTokenService authenticationTokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {        
        String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String authenticationToken = authorizationHeader.substring(7);
            handleTokenBasedAuthentication(authenticationToken, requestContext);
        }

    }

    private void handleTokenBasedAuthentication(String authenticationToken, ContainerRequestContext requestContext) {                
        AuthenticationTokenDetails authenticationTokenDetails = authenticationTokenService.parseToken(authenticationToken);
        userAuthenticatedEvent.fire(authenticationTokenDetails.getUsername());

        /* Usuario usuario = usuarioFacade.findByUserName(authenticationTokenDetails.getUsername());
        AuthenticatedUserDetails authenticatedUserDetails = new AuthenticatedUserDetails(usuario.getUsuaNombre(), usuario.getAuthorities());
        boolean isSecure = requestContext.getSecurityContext().isSecure();
        SecurityContext securityContext = new TokenBasedSecurityContext(authenticatedUserDetails, authenticationTokenDetails, isSecure);
        requestContext.setSecurityContext(securityContext);*/
    }

}
