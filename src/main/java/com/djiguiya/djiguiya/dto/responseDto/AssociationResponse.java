package com.djiguiya.djiguiya.dto.responseDto;

import com.djiguiya.djiguiya.entity.Enfant;

import java.util.List;

public record AssociationResponse(
        long id,
        String nom,
        String address,
        String email,
        String telephone,
        List<Enfant> enfants,
        String username
) {
}
