/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.security.service;

import com.viejar.viejamedi.common.persistence.AbstractFacade;
import com.viejar.viejamedi.security.model.Authority;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class AuthorityService extends AbstractFacade<Authority> {

    @PersistenceContext(unitName = "com.softcase_loginViejaR_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuthorityService() {
        super(Authority.class);
    }
    
    public Authority findByName(String name) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("authorityNombre", name);
        return findOneResult("Authority.findByAuthorityNombre", parameters);
    }
    
}
