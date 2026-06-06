package com.dev.ecommerceapp.security;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private long expiration;

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(
                secret.getBytes(StandardCharsets.UTF_8)
        );
    }

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + expiration)
                )
                .signWith(getSigningKey())
                .compact();
    }
    
    public boolean isTokenValid(String token) {
        try {
            Jwts.parser()
                    .verifyWith((SecretKey) getSigningKey())
                    .build()
                    .parseSignedClaims(token);

            return true;
        } catch (Exception ex) {
            return false;
        }
    }
    
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith((SecretKey)getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    
//    public List<GrantedAuthority> extractAuthorities(String token) {
//
//        Claims claims = Jwts.parser()
//                .verifyWith((SecretKey)getSigningKey())
//                .build()
//                .parseSignedClaims(token)
//                .getPayload();
//
//        List<String> roles =
//                claims.get("roles", List.class);
//
//        return roles.stream()
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
//    }
    
    
}
