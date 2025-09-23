package com.djiguiya.djiguiya.dto;

import com.djiguiya.djiguiya.entity.enums.Genre;

public record UserDto(
 int id,
 String nom,
 String prenom,
 String telephone,
 String email,
 String photoUrl,
 Genre genre,
 String role
) {
}
