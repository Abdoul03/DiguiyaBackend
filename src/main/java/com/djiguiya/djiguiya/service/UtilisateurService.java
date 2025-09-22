package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class UtilisateurService {
    private UserRepository userRepository;
}
