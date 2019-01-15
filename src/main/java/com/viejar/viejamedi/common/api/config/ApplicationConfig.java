/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.common.api.config;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Administrador
 */
@javax.ws.rs.ApplicationPath("viejar")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.viejar.viejamedi.common.api.config.CORSFilter.class);
        resources.add(com.viejar.viejamedi.consultorio.api.resources.CitaSubResource.class);
        resources.add(com.viejar.viejamedi.consultorio.api.resources.MedicoResource.class);
        resources.add(com.viejar.viejamedi.consultorio.api.resources.PacienteResource.class);
        resources.add(com.viejar.viejamedi.consultorio.api.resources.PacienteSubResource.class);
        resources.add(com.viejar.viejamedi.consultorio.api.resources.UsuarioResource.class);
        resources.add(com.viejar.viejamedi.security.api.exeptionmapper.AccessDeniedExceptionMapper.class);
        resources.add(com.viejar.viejamedi.security.api.exeptionmapper.AuthenticationExceptionMapper.class);
        resources.add(com.viejar.viejamedi.security.api.filter.AuthenticationFilter.class);
        resources.add(com.viejar.viejamedi.security.api.filter.AuthorizationFilter.class);
        resources.add(com.viejar.viejamedi.security.api.resource.AuthenticationResource.class);
    }
    
}
