package com.djiguiya.djiguiya.dto.mapper;

import com.djiguiya.djiguiya.dto.requestDto.AssociationRequest;
import com.djiguiya.djiguiya.dto.responseDto.AssociationResponse;
import com.djiguiya.djiguiya.entity.Association;

import java.util.ArrayList;
import java.util.List;

public class AssociationMapper {

    public static Association toEntity (AssociationRequest asso,Association association){

        association.setNom(asso.nom());
        association.setAddress(asso.address());
        association.setEmail(asso.email());
        association.setTelephone(asso.telephone());
        association.setUsername(asso.username());
        association.setMotDePasse(asso.motDePasse());

        return association;
    }

    public static AssociationResponse toResponse (Association asso) {

        if(asso == null) return null;

        AssociationResponse association = new AssociationResponse(
                asso.getId(),
                asso.getNom(),
                asso.getAddress(),
                asso.getEmail(),
                asso.getTelephone(),
                asso.getEnfants(),
                asso.getUsername()
        );

        return association;
    }
}
