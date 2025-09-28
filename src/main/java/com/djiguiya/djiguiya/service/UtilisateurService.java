package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.mapper.UtilisateurMapper;
import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.entity.Utilisateurs;
import com.djiguiya.djiguiya.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class UtilisateurService {

    private UserRepository userRepository;

    //Get AllUsers
    public List<UtilisateurResponseDTO> getAllUsers(){
        List<Utilisateurs> user = userRepository.findAll();
        return user.stream().map(UtilisateurMapper::toDTO).toList();
    }
    //Get a user
    public UtilisateurResponseDTO getUser(long userId){
        Utilisateurs user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("Utilisateur introuvable"));
        return UtilisateurMapper.toDTO(user);
    }
    // Delete User
    public boolean deleteUser(long userId){
        Utilisateurs user = userRepository.findById(userId).orElseThrow(()-> new EntityNotFoundException("Utilisateur introuvable"));
        if (user != null){
            userRepository.delete(user);
        }
        return true;
    }

}
