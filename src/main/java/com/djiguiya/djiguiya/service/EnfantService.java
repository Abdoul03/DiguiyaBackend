package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.mapper.ChildMapper;
import com.djiguiya.djiguiya.dto.mapper.UtilisateurMapper;
import com.djiguiya.djiguiya.dto.requestDto.ChildRegisteurDTO;
import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
import com.djiguiya.djiguiya.entity.Association;
import com.djiguiya.djiguiya.entity.Enfant;
import com.djiguiya.djiguiya.entity.enums.Role;
import com.djiguiya.djiguiya.repository.AssociationRepository;
import com.djiguiya.djiguiya.repository.EnfantRepository;
import com.djiguiya.djiguiya.security.JwtService;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EnfantService {

    private final EnfantRepository enfantRepository;
    private final AssociationRepository associationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    //Create a child
    public ChildResponseDTO createChild(ChildRegisteurDTO user){




        if (!"ROLE_ASSOCIATION".equals(associationFund.getRole())) {
            throw new RuntimeException("Seules les associations peuvent ajouter des enfants");
        }

        Enfant child = ChildMapper.toEntity(user, new Enfant());
        child.setActif(true);
        child.setMotDePasse(passwordEncoder.encode(user.motDePasse()));
        child.setRole(Role.ROLE_CHILD);
        child.setAssociation();
        child.setAge(child.getAge());

        Enfant save = enfantRepository.save(child);

       return ChildMapper.toResponse(save);
    }

    //Get all children
    public List<ChildResponseDTO> getChildren(){
        var getAll = enfantRepository.findAll();
        if (getAll.isEmpty()) return new ArrayList<>();
        return getAll.stream().map(ChildMapper::toResponse).toList();
    }

    //Get a child
    public ChildResponseDTO getChild(long childId){
        Enfant child = enfantRepository.findById(childId).orElseThrow(()-> new EntityNotFoundException("Enfant non trouvable"));
        return ChildMapper.toResponse(child);
    }

    //update a child information
    public ChildResponseDTO updateChild(long childId, ChildRegisteurDTO enfantDto){
        Enfant enfant = enfantRepository.findById(childId).orElseThrow(() -> new EntityNotFoundException("Enfant non trouvé avec id " + childId));

        enfant.setNom(enfantDto.nom());
        enfant.setPrenom(enfantDto.prenom());
        enfant.setAge(enfantDto.age());
        enfant.setClasse(enfantDto.classe());
        enfant.setGenre(enfantDto.genre());
        enfant.setPhotoUrl(enfantDto.photoUrl());
        enfant.setTelephone(enfantDto.telephone());
        enfant.setMotDePasse(passwordEncoder.encode(enfantDto.motDePasse()));
        enfant.setEmail(enfantDto.email());

        enfantRepository.save(enfant);
        return ChildMapper.toResponse(enfant);
    }
    //Delete a child information
    public boolean deleteChild(long childId, long association) throws Exception {

        Enfant enfant = enfantRepository.findById(childId).orElseThrow(() -> new EntityNotFoundException("Enfant non trouvé avec id " + childId));
        Association fundAssociation = associationRepository.findById(association).orElseThrow(()-> new EntityNotFoundException("Association non trouver"));

        if(fundAssociation.getEnfants().contains(enfant)){
            enfantRepository.delete(enfant);
            return true;
        }else {
            throw new Exception("Vous n'avez pas le drois de supprimer cet enfant");
        }
    }

}
