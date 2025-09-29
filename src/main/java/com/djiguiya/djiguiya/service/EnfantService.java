package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.mapper.ChildMapper;
import com.djiguiya.djiguiya.dto.requestDto.ChildRegisteurDTO;
import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
import com.djiguiya.djiguiya.entity.Association;
import com.djiguiya.djiguiya.entity.Enfant;
import com.djiguiya.djiguiya.entity.enums.Role;
import com.djiguiya.djiguiya.repository.AssociationRepository;
import com.djiguiya.djiguiya.repository.EnfantRepository;
import com.djiguiya.djiguiya.security.JwtService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class EnfantService {

    private final EnfantRepository enfantRepository;
    private final AssociationRepository associationRepository;
    private final PasswordEncoder passwordEncoder;

    //Create a child
    public ChildResponseDTO createChild(ChildRegisteurDTO user){
        // Récupérer l'ID de l'utilisateur courant (association) depuis SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long associationId = Long.valueOf(authentication.getPrincipal().toString());


        if (authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ASSOCIATION"))) {
            throw new RuntimeException("Seules les associations peuvent ajouter des enfants");
        }

        Association associationFund = associationRepository.findById(associationId)
                .orElseThrow(() -> new RuntimeException("Association introuvable"));

        Enfant child = ChildMapper.toEntity(user, new Enfant());
        child.setActif(true);
        child.setMotDePasse(passwordEncoder.encode(user.motDePasse()));
        child.setRole(Role.ROLE_CHILD);
        child.setAssociation(associationFund);
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
    public ChildResponseDTO updateChild(long childId, ChildRegisteurDTO enfantDto) throws AccessDeniedException {
        // Récupérer l'ID de l'utilisateur courant (association) depuis SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long associationId = Long.valueOf(authentication.getPrincipal().toString());

        if (authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ASSOCIATION"))) {
            throw new AccessDeniedException("Seules les associations peuvent modifier les information des enfants");
        }

        // Vérifier que l’enfant appartient bien à l’association
        Enfant enfant = enfantRepository.findByIdAndAssociationId(childId, associationId)
                .orElseThrow(() -> new AccessDeniedException("Vous n'avez pas le droit de modifier les informations cet enfant"));

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
    public void deleteChild(long childId) throws Exception {
        // Récupérer l'ID de l'utilisateur courant (association) depuis SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long associationId = Long.valueOf(authentication.getPrincipal().toString());

        if (authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ASSOCIATION"))) {
            throw new AccessDeniedException("Seules les associations peuvent supprimer des enfants");
        }

        // Vérifier que l’enfant appartient bien à l’association
        Enfant enfant = enfantRepository.findByIdAndAssociationId(childId, associationId)
                .orElseThrow(() -> new AccessDeniedException("Vous n'avez pas le droit de supprimer cet enfant"));

        enfantRepository.delete(enfant);
    }

}
