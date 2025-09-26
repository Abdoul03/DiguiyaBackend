package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.mapper.UtilisateurMapper;
import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.entity.Utilisateurs;
import com.djiguiya.djiguiya.repository.UserRepository;
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
    public UtilisateurResponseDTO getUser(int userId){

    }

}
