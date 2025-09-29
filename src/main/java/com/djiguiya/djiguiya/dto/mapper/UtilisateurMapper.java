package com.djiguiya.djiguiya.dto.mapper;

import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.entity.Utilisateurs;

public class UtilisateurMapper {
    public static <T extends Utilisateurs> T toEntity(UtilisateurRegisteurDto dto, T userEntity) {
        userEntity.setNom(dto.getNom());
        userEntity.setPrenom(dto.getPrenom());
        userEntity.setUsername(dto.getUsername());
        userEntity.setTelephone(dto.getTelephone());
        userEntity.setEmail(dto.getEmail());
        userEntity.setMotDePasse(dto.getMotDePasse());
        userEntity.setGenre(dto.getGenre());
        userEntity.setRole(dto.getRole());
        return userEntity;
    }

    public static UtilisateurResponseDTO toDTO(Utilisateurs user) {
        if (user == null) return null;

        UtilisateurResponseDTO dto = new UtilisateurResponseDTO();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setUsername(user.getUsername());
        dto.setTelephone(user.getTelephone());
        dto.setEmail(user.getEmail());
        dto.setActif(user.isActif());
        dto.setPhotoUrl(user.getPhotoUrl());
        dto.setGenre(user.getGenre());
        dto.setRole(user.getRole());
        return dto;
    }
}
