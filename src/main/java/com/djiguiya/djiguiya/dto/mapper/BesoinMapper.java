package com.djiguiya.djiguiya.dto.mapper;

import com.djiguiya.djiguiya.dto.requestDto.BesoinRequest;
import com.djiguiya.djiguiya.dto.responseDto.BesoinResponse;
import com.djiguiya.djiguiya.entity.Besoin;

public class BesoinMapper {

    public static Besoin toEntity (BesoinRequest besoinRequest, Besoin besoin){
        besoin.setNom(besoinRequest.nom());
        besoin.setDescription(besoinRequest.description());
        besoin.setMontant_necessaire(besoinRequest.montant());
        return besoin;
    }

    public static BesoinResponse toResponse(Besoin besoin){
        if(besoin == null) return null;
        BesoinResponse besoinResponse = new BesoinResponse(
                besoin.getId(),
                besoin.getNom(),
                besoin.getDescription(),
                besoin.getMontant_necessaire()
        );
        return besoinResponse;
    }
}
