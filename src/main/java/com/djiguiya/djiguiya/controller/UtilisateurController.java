package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/utilisateur")
public class UtilisateurController {

    private UtilisateurService utilisateurService;

    @PostMapping
    public void addUser(){
        System.out.println("hello");
    }
}
