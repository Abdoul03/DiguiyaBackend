package com.djiguiya.djiguiya.dto.responseDto;

import com.djiguiya.djiguiya.entity.enums.Genre;
import com.djiguiya.djiguiya.entity.enums.Role;
import lombok.Data;

@Data
public class UtilisateurResponseDTO {
    private long id;
    private String nom;
    private String prenom;
    private String username;
    private String telephone;
    private String email;
    private boolean actif;
    private String photoUrl;
    private Genre genre;
    private Role role;
}
