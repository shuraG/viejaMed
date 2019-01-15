/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.security.api.filter;

import com.viejar.viejamedi.security.api.AuthenticatedUser;
import com.viejar.viejamedi.security.exeption.AccessDeniedException;
import com.viejar.viejamedi.security.model.Authority;
import com.viejar.viejamedi.consultorio.model.Usuario;
import java.io.IOException;
import java.lang.reflect.Method;
import javax.annotation.Priority;
import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Administrador
 */
@Provider
@Dependent
@Priority(Priorities.AUTHORIZATION)
public class AuthorizationFilter implements ContainerRequestFilter {

    @Inject
    @AuthenticatedUser
    Usuario usuarioAutentificado;

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {

        Method method = resourceInfo.getResourceMethod();

        // @DenyAll on the method takes precedence over @RolesAllowed and @PermitAll
        if (method.isAnnotationPresent(DenyAll.class)) {
            throw new AccessDeniedException("You don't have permissions to perform this action.");
        }

        // @RolesAllowed on the method takes precedence over @PermitAll
        RolesAllowed rolesAllowed = method.getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value());
            return;
        }

        // @PermitAll on the method takes precedence over @RolesAllowed on the class
        if (method.isAnnotationPresent(PermitAll.class)) {
            // Do nothing
            return;
        }

        // @DenyAll can't be attached to classes
        // @RolesAllowed on the class takes precedence over @PermitAll on the class
        rolesAllowed = resourceInfo.getResourceClass().getAnnotation(RolesAllowed.class);
        if (rolesAllowed != null) {
            performAuthorization(rolesAllowed.value());
        }

        // @PermitAll on the class
        if (resourceInfo.getResourceClass().isAnnotationPresent(PermitAll.class)) {
            // Do nothing
            return;
        }

        // Authentication is required for non-annotated methods
        if (!isAuthenticated(usuarioAutentificado)) {
            throw new AccessDeniedException("Autentifiación es requerida para ejecutar esta acción.");
        }
    }

    /**
     * Perform authorization based on roles.
     *
     * @param rolesAllowed
     * @param requestContext
     */
    private void performAuthorization(String[] rolesAllowed) {

        if (rolesAllowed.length > 0 && !isAuthenticated(usuarioAutentificado)) {
            throw new AccessDeniedException("Autentifiación es requerida para ejecutar esta acción.");
        }

        for (final String role : rolesAllowed) {
            if (isUserInRole(role)) {
                return;
            }
        }

        throw new AccessDeniedException("No tienes permisos para ejecutar esta acción.");
    }

    /**
     * Verifica si el Usuario esta autentificado.
     *
     * @param requestContext
     * @return
     */
    private boolean isAuthenticated(final Usuario usuarioAutentificado) {
        return usuarioAutentificado != null;
    }

    private boolean isUserInRole(String role) {
        return usuarioAutentificado.getAuthorities()
                .stream()
                .map(Authority::getAuthorityNombre)
                .anyMatch(p -> p.equals(role));
    }

}
