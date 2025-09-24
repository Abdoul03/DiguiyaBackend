package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.UtilisateurDto;
import com.djiguiya.djiguiya.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@Service
public class UtilisateurService {
    private UserRepository userRepository;

    public void inscription(UtilisateurDto utilisateurDto){
        //Validation Email
        if(!utilisateurDto.getEmail().contains("@")){
            throw new RuntimeException("Votre email est invalide");
        }
        if(!utilisateurDto.getEmail().contains(".")){
            throw new RuntimeException("Votre email est invalide");
        }
    }

}
