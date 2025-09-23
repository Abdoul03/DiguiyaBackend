package com.djiguiya.djiguiya.security;

import com.djiguiya.djiguiya.entity.RoleType;
import com.djiguiya.djiguiya.entity.Utilisateurs;
import com.djiguiya.djiguiya.entity.enums.Role;
import com.djiguiya.djiguiya.repository.RoleTypeRepository;
import com.djiguiya.djiguiya.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataInitializer {
    private UserRepository utilisateurRepository;
    private PasswordEncoder passwordEncoder;
    private RoleTypeRepository roleTypeRepository;

    public DataInitializer(UserRepository utilisateurRepository,
                           PasswordEncoder passwordEncoder,
                           RoleTypeRepository roleTypeRepository) {
        this.utilisateurRepository = utilisateurRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleTypeRepository = roleTypeRepository;
    }


    @Bean
    @Transactional
    public CommandLineRunner initAdmin() {
        return args -> {
            // Vérifie si un rôle ADMIN existe déjà
            RoleType roleAdmin = roleTypeRepository.findByLibelle(Role.ADMIN)
                    .orElse(null);


            String username = "admin";
            String password = "password123";

            // Vérifie si un admin existe déjà
            if (utilisateurRepository.findByUsername(username).isEmpty()) {
                Utilisateurs admin = new Utilisateurs();
                admin.setNom("Super");
                admin.setPrenom("Admin");
                admin.setUsername(username);
                admin.setEmail("admin@djiguiya.com");
                admin.setMotDePasse(passwordEncoder.encode(password));
                admin.setActif(true);
                admin.setRole(roleAdmin);

                utilisateurRepository.save(admin);
                System.out.println("Admin par défaut créé : " + username + " / " + password);
            } else {
                System.out.println(" Admin déjà existant, aucune action.");
            }
        };
    }

}
