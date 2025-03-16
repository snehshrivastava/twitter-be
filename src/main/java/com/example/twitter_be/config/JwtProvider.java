package com.example.twitter_be.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {
    @Value("${jwtSecretKey}")
    String secretKeyString;
    public String generateToken(Authentication authentication){
        System.err.println(secretKeyString);
        SecretKey key = Keys.hmacShaKeyFor(secretKeyString.getBytes());
        String jwt = Jwts.builder()
        .setIssuedAt(new Date())
        .setExpiration(new Date(new Date().getTime()+86400000))
        .claim("email", authentication.getName())
        .signWith(key).compact();
        return jwt;
    }

    public String getUserEmailFromToken(String token){
        SecretKey key = Keys.hmacShaKeyFor(secretKeyString.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }
}
