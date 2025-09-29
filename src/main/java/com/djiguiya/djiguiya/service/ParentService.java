package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.mapper.UtilisateurMapper;
import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.entity.Parent;
import com.djiguiya.djiguiya.entity.enums.Role;
import com.djiguiya.djiguiya.repository.ParentRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ParentService {
    private ParentRepository parentRepository;
    private final PasswordEncoder passwordEncoder;

    //Create a parent
    public UtilisateurResponseDTO createAParent(UtilisateurRegisteurDto user){
        Parent parent = UtilisateurMapper.toEntity(user, new Parent());
        parent.setActif(true);
        parent.setRole(Role.ROLE_PARENT);
        parent.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        parentRepository.save(parent);
        return UtilisateurMapper.toDTO(parent);
    }
    //Get All Parent
    public List<UtilisateurResponseDTO> getAllParent (){
        List<Parent> parents = parentRepository.findAll();
        return parents.stream().map(UtilisateurMapper::toDTO).toList();
    }
    //Get a parent
    public UtilisateurResponseDTO getParent (long parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(()->new EntityNotFoundException("Parent non trouve"));
        return UtilisateurMapper.toDTO(parent);
    }
    //Upadete parent
    public UtilisateurResponseDTO updateParent(UtilisateurRegisteurDto user){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long parentId = Long.valueOf(authentication.getPrincipal().toString());

        Parent parent = parentRepository.findById(parentId).orElseThrow(()->new EntityNotFoundException("Parent non trouve"));

        parent.setNom(user.getNom());
        parent.setPrenom(user.getPrenom());
        parent.setEmail(user.getEmail());
        parent.setTelephone(user.getTelephone());
        parent.setGenre(user.getGenre());
        parent.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        parentRepository.save(parent);
        return UtilisateurMapper.toDTO(parent);
    }
    //Delete parent
    public boolean deleteParent(long parentId){
        Parent parent = parentRepository.findById(parentId).orElseThrow(()->new EntityNotFoundException("Parent non trouve"));
        return true;
    }
}
