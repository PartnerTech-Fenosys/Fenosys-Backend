/*
 * Copyright (c) 2021. Developed by Diego Campos Sandoval.
 */

package pe.partnertech.fenosys.security.jwt;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import pe.partnertech.fenosys.serviceimpl.UserDetailsImpl;

import java.util.Date;

@Component
public class JwtProvider {

    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    public String generateJwtToken(Authentication authentication) {

        UserDetailsImpl usuarioPrincipal = (UserDetailsImpl) authentication.getPrincipal();

        return Jwts.builder().setSubject((usuarioPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateJwtToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("Firma JWT Invalida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Token JWT Invalido: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT Expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT No Soportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Token JWT Vacio: {}", e.getMessage());
        }

        return false;
    }
}
