package com.proday.market.web.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Ruben Malaga
 */
@Component
public class JWTUtil {
    private static final String KEY = "proday";

    public String generarToken(UserDetails userdetails) {
        return Jwts.builder()
                    .setSubject(userdetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256, KEY)
                    .compact()
        ;
    }
}
