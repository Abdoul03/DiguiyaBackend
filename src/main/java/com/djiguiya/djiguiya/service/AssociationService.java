package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.mapper.AssociationMapper;
import com.djiguiya.djiguiya.dto.mapper.ChildMapper;
import com.djiguiya.djiguiya.dto.requestDto.AssociationRequest;
import com.djiguiya.djiguiya.dto.requestDto.ChildRegisteurDTO;
import com.djiguiya.djiguiya.dto.responseDto.AssociationResponse;
import com.djiguiya.djiguiya.entity.Association;
import com.djiguiya.djiguiya.entity.Enfant;
import com.djiguiya.djiguiya.entity.enums.Role;
import com.djiguiya.djiguiya.repository.AssociationRepository;
import com.djiguiya.djiguiya.repository.EnfantRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AssociationService {

    private final AssociationRepository associationRepository;
    private final PasswordEncoder passwordEncoder;

    //Create an Association
    public AssociationResponse createAssociation (AssociationRequest assoCreate) {
        Association asso = AssociationMapper.toEntity(assoCreate ,new Association());
        asso.setRole(Role.ROLE_ASSOCIATION);
        asso.setMotDePasse(passwordEncoder.encode(assoCreate.motDePasse()));
        associationRepository.save(asso);
        return AssociationMapper.toResponse(asso);
    }

    //Get all associations
    public List<AssociationResponse> getAssociations () {
        List<Association> association = associationRepository.findAll();
        return association.stream().map(AssociationMapper :: toResponse).toList();
    }

    //Get an associations
    public AssociationResponse getAnAssociation (long associationId) {
        Association association = associationRepository.findById(associationId).orElseThrow(()-> new EntityNotFoundException("Association introuvable"));
        return AssociationMapper.toResponse(association);
    }

    //Update an association
    public AssociationResponse updateAssociation(long associationId, AssociationRequest assoCreate){
        Association association = associationRepository.findById(associationId).orElseThrow(()-> new EntityNotFoundException("Association introuvable"));

        association.setAddress(assoCreate.address());
        association.setEmail(assoCreate.email());
        association.setTelephone(assoCreate.telephone());

        associationRepository.save(association);

        return AssociationMapper.toResponse(association);
    }
    //delete an association
    public boolean deleteAssociation(long associationId){
        Association association = associationRepository.findById(associationId).orElseThrow(()-> new EntityNotFoundException("Association introuvable"));
        associationRepository.delete(association);
        return true;
    }
}
