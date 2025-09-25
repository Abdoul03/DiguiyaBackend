package com.djiguiya.djiguiya.security;

import com.djiguiya.djiguiya.entity.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.secret-key}")
    private String jwtSecret;
    private SecretKey secretKey;

    private final long accessTokenValidity = 10L * 60L * 1000L;
    @Getter
    private final long refreshTokenValidity = 30L * 24L * 60L * 60L * 1000L;

    @PostConstruct
    void init() {
        this.secretKey = Keys.hmacShaKeyFor(Base64.getDecoder().decode(jwtSecret.getBytes(StandardCharsets.UTF_8)));
    }

    private String generateToken(Long userId, TokenType tokenType, long expiryAt, Role userRoles) {
        Date now = Date.from(Instant.now());
        Date expirationDate = new Date(now.getTime() + expiryAt);

        return Jwts.builder()
                .subject(userId.toString())
                .claim("type", tokenType.name())
                .claim("role", userRoles)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }


    private String generateAccessToken( Long userId, Role userRoles){
        return generateToken(userId, TokenType.ACCESS_TOKEN, accessTokenValidity, userRoles);
    }
    private String generateRefreshToken(Long userId, Role userRoles){
        return generateToken(userId, TokenType.REFRESH_TOKEN, refreshTokenValidity, userRoles);
    }


    public boolean isTokenAccessValid(String accessToken) {
        Claims claims = parseAllClaims(accessToken);
        if (claims == null) return false;
        String type = (String) claims.get("type");
        return TokenType.ACCESS_TOKEN.name().equals(type);
    }
    public boolean isTokenRefreshValid(String refreshToken){
        Claims claims = parseAllClaims(refreshToken);
        if (claims == null) return false;
        String type = (String) claims.get("type");
        return TokenType.REFRESH_TOKEN.name().equals(type);
    }
    public Long getUserIdFromToken(String token) {
        Claims claims = parseAllClaims(token);
        if (claims == null) throw new IllegalArgumentException("Token invalide.");
        return Long.parseLong(claims.getSubject());
    }
    public Role getUserRolesFromToken(String token) {
        Claims claims = parseAllClaims(token);
        if (claims == null) throw new IllegalArgumentException("Token invalide");
        Object roleClaim = claims.get("role");
        if (roleClaim == null) {
            throw new IllegalArgumentException("Aucun rôle trouvé dans le token");
        }
        return Role.valueOf(roleClaim.toString());
    }


    private Claims parseAllClaims(String token) {
        String rawToken = token.startsWith("Bearer ") ? token.substring(7) : token;

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(rawToken)
                .getPayload();
    }

    private enum TokenType {
        ACCESS_TOKEN,
        REFRESH_TOKEN
    }
}
