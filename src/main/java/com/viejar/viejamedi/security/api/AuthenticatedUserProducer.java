package com.viejar.viejamedi.security.api;

import com.viejar.viejamedi.consultorio.service.UsuarioService;
import com.viejar.viejamedi.consultorio.model.Usuario;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

@RequestScoped
public class AuthenticatedUserProducer {

    static int ant = 0;

    @Inject
    private UsuarioService usuarioService;

    /* @Produces
    @RequestScoped
    @AuthenticatedUser*/
    private Usuario authenticatedUser;

    public void handleAuthenticationEvent(@Observes @AuthenticatedUser String username) {
        System.out.println("Observador");
        this.authenticatedUser = findUser(username);
    }

    private Usuario findUser(String username) {
        Usuario usuario = usuarioService.findByUserName(username);
        return usuario;
    }

    @Produces
    @RequestScoped
    @AuthenticatedUser
    public Usuario createLogger() {
        ant++;
        System.out.println(ant + "Creando un authenticadesUser");
        return authenticatedUser;
    }
}
