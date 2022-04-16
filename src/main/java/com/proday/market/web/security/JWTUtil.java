package com.proday.market.web.security;

import io.jsonwebtoken.Claims;
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

    public String generateToken(UserDetails userdetails) {
        return Jwts.builder()
                    .setSubject(userdetails.getUsername())
                    .setIssuedAt(new Date())
                    .setExpiration(new Date(System.currentTimeMillis() * 1000 * 60 * 60 * 10))
                    .signWith(SignatureAlgorithm.HS256, KEY)
                    .compact()
        ;
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        return userDetails.getUsername().equals(this.extractUsername(token))
            && !isTokenExpired(token)
        ;
    }

    public String extractUsername(String token) {
        return this.getClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return this.getClaims(token).getExpiration().before(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser().setSigningKey(KEY).parseClaimsJws(token).getBody();
    }
}
