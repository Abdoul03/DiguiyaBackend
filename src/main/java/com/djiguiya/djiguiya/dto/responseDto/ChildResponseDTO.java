package com.djiguiya.djiguiya.dto.responseDto;

import com.djiguiya.djiguiya.entity.Besoin;
import com.djiguiya.djiguiya.entity.Parent;
import com.djiguiya.djiguiya.entity.enums.Genre;
import com.djiguiya.djiguiya.entity.enums.Role;

import java.util.List;
import java.util.Set;

public record ChildResponseDTO(
        Long id,
        String nom,
        String prenom,
        String username,
        String classe,
        int age,
        String telephone,
        String email,
        Genre genre,
        Role role,
        long associationId,
        Set<Besoin> besoins,
        Set<Parent> parent
) {
}
