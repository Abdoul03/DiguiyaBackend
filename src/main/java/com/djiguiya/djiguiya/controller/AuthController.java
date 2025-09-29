package com.djiguiya.djiguiya.controller;


import com.djiguiya.djiguiya.dto.AuthRequest;
import com.djiguiya.djiguiya.dto.requestDto.AssociationRequest;
import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.AssociationResponse;
import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.security.auth.AuthentificationService;
import com.djiguiya.djiguiya.service.AssociationService;
import com.djiguiya.djiguiya.service.ParrainService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("auth")
public class AuthController {
    private ParrainService parrainService;
    private AuthentificationService authentificationService;
    private AssociationService associationService;

    @PostMapping("/register/sponsor")
    public ResponseEntity<UtilisateurResponseDTO> createSponsor(
            @Valid @RequestBody UtilisateurRegisteurDto sponsort){
        return ResponseEntity.ok(
                parrainService.createParrain(sponsort)
        );
    }

    @PostMapping("/register/association")
    public ResponseEntity<AssociationResponse> createAssociation (@Valid @RequestBody AssociationRequest association) {
        return ResponseEntity.ok(
                associationService.createAssociation(association)
        );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthentificationService.TokenPairResponse> login(
            @Valid @RequestBody AuthRequest authenticationRequest
    ) {
        return ResponseEntity.ok(
                authentificationService.authenticate(authenticationRequest)
        );
    }

    @PostMapping("/login/association")
    public ResponseEntity<AuthentificationService.TokenPairResponse> loginAssociation(
            @Valid @RequestBody AuthRequest authenticationRequest
    ) {
        return ResponseEntity.ok(
                authentificationService.authenticateAssociation(authenticationRequest)
        );
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthentificationService.TokenPairResponse> refreshToken(
            @RequestBody String refreshToken
    ) {
        return ResponseEntity.ok(authentificationService.refresh(refreshToken));
    }
}
