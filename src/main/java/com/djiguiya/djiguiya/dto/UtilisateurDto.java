package com.djiguiya.djiguiya.dto;


import com.djiguiya.djiguiya.entity.enums.Genre;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {
    private int id;
    private String nom;
    private String prenom;
    private String telephone;
    private String email;
    private boolean actif;
    private String photoUrl;
    private Genre genre;
    private String role;
}
