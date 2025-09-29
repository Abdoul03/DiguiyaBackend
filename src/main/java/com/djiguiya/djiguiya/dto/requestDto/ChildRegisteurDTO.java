package com.djiguiya.djiguiya.dto.requestDto;

import com.djiguiya.djiguiya.entity.Besoin;
import com.djiguiya.djiguiya.entity.Parent;
import com.djiguiya.djiguiya.entity.enums.Genre;
import com.djiguiya.djiguiya.entity.enums.Role;

import java.util.List;
import java.util.Set;


public record ChildRegisteurDTO(
        String nom,
        String prenom,
        String username,
        String classe,
        long associationId,
        int age,
        String telephone,
        String email,
        String motDePasse,
        Genre genre,
        Role role,
        String photoUrl,
        int besoin,
        int parents
) {
}
