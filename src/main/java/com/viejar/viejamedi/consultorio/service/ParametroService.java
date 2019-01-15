/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.service;

import com.viejar.viejamedi.common.persistence.AbstractFacade;
import com.viejar.viejamedi.consultorio.model.Parametro;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Administrador
 */
@Stateless
public class ParametroService extends AbstractFacade<Parametro> {

    @PersistenceContext(unitName = "com.softcase_loginViejaR_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ParametroService() {
        super(Parametro.class);
    }
    
}
