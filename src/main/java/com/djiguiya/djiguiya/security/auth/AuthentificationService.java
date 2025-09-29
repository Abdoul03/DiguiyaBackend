package com.djiguiya.djiguiya.security.auth;

import com.djiguiya.djiguiya.dto.AuthRequest;
import com.djiguiya.djiguiya.entity.Association;
import com.djiguiya.djiguiya.entity.RefreshToken;
import com.djiguiya.djiguiya.entity.Utilisateurs;
import com.djiguiya.djiguiya.repository.AssociationRepository;
import com.djiguiya.djiguiya.repository.RefreshTokenRepository;
import com.djiguiya.djiguiya.repository.UserRepository;
import com.djiguiya.djiguiya.security.JwtService;
import io.jsonwebtoken.JwtException;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Base64;

@AllArgsConstructor
@Service
public class AuthentificationService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final AssociationRepository associationRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;


    public TokenPairResponse authenticate(AuthRequest authenticationRequest) {
        Utilisateurs userToAuthenticate = userRepository.findByUsername(authenticationRequest.username())
                .orElseThrow(() -> new BadCredentialsException("Username ou mot de passe incorrect."));

        if (BCrypt.checkpw(authenticationRequest.motDePasse(), userToAuthenticate.getMotDePasse())) {
            String newAccessToken = jwtService.generateAccessToken(
                    userToAuthenticate.getId(),
                    userToAuthenticate.getRole()
            );
            String newRefreshToken = jwtService.generateRefreshToken(
                    userToAuthenticate.getId(),
                    userToAuthenticate.getRole()
            );
            storeRefreshToken(userToAuthenticate.getId(), newRefreshToken);
            return new TokenPairResponse(newAccessToken, newRefreshToken);
        }
        throw new BadCredentialsException("Username ou mot de passe incorrect.");
    }

    public TokenPairResponse authenticateAssociation(AuthRequest authenticationRequest) {
        Association associationToAuthenticate = associationRepository.findByUsername(authenticationRequest.username())
                .orElseThrow(() -> new BadCredentialsException("Username ou mot de passe incorrect."));

        if (BCrypt.checkpw(authenticationRequest.motDePasse(), associationToAuthenticate.getMotDePasse())) {
            String newAccessToken = jwtService.generateAccessToken(
                    associationToAuthenticate.getId(),
                    associationToAuthenticate.getRole()
            );
            String newRefreshToken = jwtService.generateRefreshToken(
                    associationToAuthenticate.getId(),
                    associationToAuthenticate.getRole()
            );
            storeRefreshToken(associationToAuthenticate.getId(), newRefreshToken);
            return new TokenPairResponse(newAccessToken, newRefreshToken);
        }
        throw new BadCredentialsException("Username ou mot de passe incorrect.");
    }

    @Transactional
    public TokenPairResponse refresh(String refreshToken) {
        if (!jwtService.isTokenRefreshValid(refreshToken)) {
            throw new JwtException("");
        }
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        Utilisateurs user = userRepository.findById(userId).orElseThrow(() -> new JwtException("Utilisateur introuvable."));

        String hashedToken = hashToken(refreshToken);
        refreshTokenRepository.findByUserIdAndToken(user.getId(), hashedToken)
                .orElseThrow(() -> new JwtException(""));

        String newAccessToken = jwtService.generateAccessToken(user.getId(), user.getRole());
        String newRefreshToken = jwtService.generateRefreshToken(user.getId(), user.getRole());
        storeRefreshToken(user.getId(), newRefreshToken);

        return new TokenPairResponse(newAccessToken, newRefreshToken);
    }

    private void storeRefreshToken(Long userId, String refreshToken) {
        String hashedToken = hashToken(refreshToken);
        long expiryMs = jwtService.getRefreshTokenValidity();
        Instant expiresAt = Instant.now().plusMillis(expiryMs);

        Utilisateurs user = userRepository.findById(userId)
                .orElseThrow(() -> new JwtException("Utilisateur introuvable."));

        RefreshToken tokenToStore = refreshTokenRepository.findByUserId(userId)
                .orElse(new RefreshToken(null, user, Instant.now(), null, ""));

        tokenToStore.setExpiresAt(expiresAt);
        tokenToStore.setToken(hashedToken);

        refreshTokenRepository.save(tokenToStore);
    }

    private String hashToken(String token) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(token.getBytes());
            return Base64.getEncoder().encodeToString(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Erreur lors du hash du token.", e);
        }
    }

    public Long getCurrentUserId() {
        return Long.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
    }

    public record TokenPairResponse(String accessToken, String refreshToken) {
    }

}
