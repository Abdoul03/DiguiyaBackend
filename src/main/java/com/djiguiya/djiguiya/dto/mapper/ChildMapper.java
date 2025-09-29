package com.djiguiya.djiguiya.dto.mapper;

import com.djiguiya.djiguiya.dto.requestDto.ChildRegisteurDTO;
import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
import com.djiguiya.djiguiya.entity.Enfant;

public class ChildMapper {
    public static ChildResponseDTO toResponse(Enfant enfant){
        return new ChildResponseDTO(
                enfant.getId(),
                enfant.getNom(),
                enfant.getPrenom(),
                enfant.getUsername(),
                enfant.getTelephone(),
                enfant.getAge(),
                enfant.getClasse(),
                enfant.getEmail(),
                enfant.getGenre(),
                enfant.getRole(),
                enfant.getAssociation().getId(),
                enfant.getBesoins(),
                enfant.getParent()

        );
    }

    public static Enfant toEntity(ChildRegisteurDTO creatChild, Enfant child){
        child.setNom(creatChild.nom());
        child.setPrenom(creatChild.prenom());
        child.setEmail(creatChild.email());
        child.setTelephone(creatChild.telephone());
        child.setGenre(creatChild.genre());
        child.setMotDePasse(creatChild.motDePasse());
        child.setAge(creatChild.age());
        child.setClasse(creatChild.classe());
        child.setPhotoUrl(creatChild.photoUrl());
        child.setUsername(creatChild.username());
        child.setBesoins(creatChild.besoin());
        child.setParent(creatChild.parents());
        return child;
    }
}
