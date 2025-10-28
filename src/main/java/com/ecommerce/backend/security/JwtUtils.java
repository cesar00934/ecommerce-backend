package com.ecommerce.backend.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

  @Value("${jwt.secret}")
  private String jwtSecret;

  @Value("${jwt.expirationMs}")
  private Long jwtExpirationMs;

  private Key getSigningKey(){
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }

  public String generateToken(String username, Collection<String> roles){
    Date now = new Date();
    Date expiry = new Date(now.getTime() + jwtExpirationMs);
    return Jwts.builder()
        .setSubject(username)
        .claim("roles", roles)
        .setIssuedAt(now)
        .setExpiration(expiry)
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public String getUsernameFromToken(String token){
    return Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
        .parseClaimsJws(token).getBody().getSubject();
  }

  public List<String> getRolesFromToken(String token){
    Claims c = Jwts.parserBuilder().setSigningKey(getSigningKey()).build()
        .parseClaimsJws(token).getBody();
    Object rs = c.get("roles");
    if (rs instanceof List) {
      return ((List<?>) rs).stream().map(Object::toString).collect(Collectors.toList());
    }
    return Collections.emptyList();
  }

  public boolean validateJwtToken(String authToken) {
    try {
      Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(authToken);
      return true;
    } catch (JwtException e) {
      return false;
    }
  }
}
