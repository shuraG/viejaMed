/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.consultorio.service;

import com.viejar.viejamedi.common.persistence.AbstractFacade;
import com.viejar.viejamedi.consultorio.model.Paciente;
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
public class PacienteService extends AbstractFacade<Paciente> {

    @PersistenceContext(unitName = "com.softcase_loginViejaR_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PacienteService() {
        super(Paciente.class);
    }

    public Paciente findPacienteByCedulaOrEmail(String CiEmail) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("cedulaRuc", CiEmail);
        parameters.put("email", CiEmail);
        return findOneResult("Paciente.findByCedulaEmail", parameters);
    }

}
