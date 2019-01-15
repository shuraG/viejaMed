/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.security.service;

import com.viejar.viejamedi.consultorio.service.UsuarioService;
import com.viejar.viejamedi.security.exeption.AuthenticationException;
import com.viejar.viejamedi.consultorio.model.Usuario;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * Componente para validar las credenciales del usuario
 *
 */
@ApplicationScoped
public class UsernamePasswordValidator {

    @Inject
    UsuarioService usuarioService;
    
    public Usuario validateCredentials(String username, String password){

        Usuario user = usuarioService.findByUserName(username);

        if (user == null) {
            // User cannot be found with the given username/email
            throw new AuthenticationException("Credenciales incorrectas.");
        }

        /* if (!user.isActive()) {
            // User is not active
            throw new AuthenticationException("The user is inactive.");
        }*/

        if (!password.equals(user.getUsuaPassword())) {
            // Invalid password
            throw new AuthenticationException("Bad credentials.");
        }
        return user;
    }

}
