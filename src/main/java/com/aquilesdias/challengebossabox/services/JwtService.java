package com.aquilesdias.challengebossabox.services;

import com.aquilesdias.challengebossabox.domain.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtService {

    @Value("${security.jwt-key-secret}")
    private String keySecret;

    @Value("${security.jwt-expiration}")
    private String expiration;

    public String generatedToken(Usuario usuario){

        try {
            Algorithm algorithm = Algorithm.HMAC512(keySecret);
            String token = JWT.create()
                    .withIssuer("bossabox")
                    .withSubject(usuario.getUsername())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException ex) {
             throw new RuntimeException("Error generated token: ", ex);
          }
    }

    public String validateToken(String token){

        try {
            Algorithm algorithm = Algorithm.HMAC512(keySecret);
            return JWT.require(algorithm)
                    .withIssuer("bossabox")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException ex){
            return "";
        }
    }

    private Instant genExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
