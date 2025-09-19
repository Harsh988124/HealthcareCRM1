//package com.healthcare.crm.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Service;
//
//import java.security.Key;
//import java.util.Date;
//
//@Service
//public class JwtService {
//    private static final String SECRET = "my_super_secret_key_which_should_be_very_long";
//    private static final long EXPIRATION = 1000 * 60 * 60; // 1 hour
//
//    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());
//
//    public String generateToken(String email, String role) {
//        return Jwts.builder()
//                .setSubject(email)
//                .claim("role", role)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractEmail(String token) {
//        return Jwts.parserBuilder().setSigningKey(key).build()
//                .parseClaimsJws(token).getBody().getSubject();
//    }
//
//    public String extractRole(String token) {
//        return (String) Jwts.parserBuilder().setSigningKey(key).build()
//                .parseClaimsJws(token).getBody().get("role");
//    }
//
//    public boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
//            return true;
//        } catch (JwtException e) {
//            return false;
//        }
//    }
//}
//