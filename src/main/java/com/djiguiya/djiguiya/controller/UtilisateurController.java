package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.entity.Utilisateurs;
import com.djiguiya.djiguiya.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    @GetMapping
    public ResponseEntity<List<UtilisateurResponseDTO>> getAllUser() {
        return ResponseEntity.ok( utilisateurService.getAllUsers());
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UtilisateurResponseDTO> getAUser(@PathVariable long userId){
        return ResponseEntity.ok(utilisateurService.getUser(userId));
    }
    @DeleteMapping("/{userId}")
    public boolean deleteUser(@PathVariable long userId){
        return utilisateurService.deleteUser(userId);
    }
}
