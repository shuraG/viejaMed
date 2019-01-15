/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.viejar.viejamedi.security.service;

import com.viejar.viejamedi.common.configuration.Configurable;
import com.viejar.viejamedi.security.api.AuthenticationTokenDetails;
import com.viejar.viejamedi.security.model.Authority;
import java.time.ZonedDateTime;
import java.util.Set;
import java.util.UUID;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 *
 * @author Administrador
 */
@ApplicationScoped
public class AuthenticationTokenService {

    /**
     * How long the token is valid for (in seconds).
     */
    @Inject
    @Configurable("authentication.jwt.validFor")
    private Long validFor;

    /**
     * How many times the token can be refreshed.
     */
    @Inject
    @Configurable("authentication.jwt.refreshLimit")
    private Integer refreshLimit;

    @Inject
    private AuthenticationTokenParser tokenParser;

    @Inject
    private AuthenticationTokenIssuer tokenIssuer;

    /**
     * Analiza y valida el token.
     *
     * @param token
     * @return
     */
    public AuthenticationTokenDetails parseToken(String token) {
        return tokenParser.parseToken(token);
    }

    /**
     * Issue a token for a user with the given authorities.
     *
     * @param username
     * @param authorities
     * @return
     */
    public String issueToken(String username, Set<Authority> authorities) {

        String id = generateTokenIdentifier();
        ZonedDateTime issuedDate = ZonedDateTime.now();
        ZonedDateTime expirationDate = calculateExpirationDate(issuedDate);

        AuthenticationTokenDetails authenticationTokenDetails = new AuthenticationTokenDetails.Builder()
                .withId(id)
                .withUsername(username)
                .withAuthorities(authorities)
                .withIssuedDate(issuedDate)
                .withExpirationDate(expirationDate)
                .withRefreshCount(0)
                .withRefreshLimit(refreshLimit)
                .build();

        return tokenIssuer.issueToken(authenticationTokenDetails);
    }

    private String generateTokenIdentifier() {
        return UUID.randomUUID().toString();
    }

    private ZonedDateTime calculateExpirationDate(ZonedDateTime issuedDate) {
        return issuedDate.plusSeconds(validFor);
    }

}
