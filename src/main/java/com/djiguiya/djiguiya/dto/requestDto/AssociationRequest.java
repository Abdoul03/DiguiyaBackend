package com.djiguiya.djiguiya.dto.requestDto;

public record AssociationRequest (
    String nom,
    String address,
    String email,
    String telephone,
    String username,
    String motDePasse
) {
}
