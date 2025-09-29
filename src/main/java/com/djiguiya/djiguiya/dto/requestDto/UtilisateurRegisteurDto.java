package com.djiguiya.djiguiya.dto.requestDto;

import com.djiguiya.djiguiya.entity.enums.Genre;
import com.djiguiya.djiguiya.entity.enums.Role;
import lombok.Data;

@Data
public class UtilisateurRegisteurDto {
    private String nom;
    private String prenom;
    private String username;
    private String telephone;
    private String email;
    private String motDePasse;
    private Genre genre;
    private Role role;
}