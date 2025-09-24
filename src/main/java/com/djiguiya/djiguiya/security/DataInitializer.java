package com.djiguiya.djiguiya.security;

import com.djiguiya.djiguiya.entity.RoleType;
import com.djiguiya.djiguiya.entity.Utilisateurs;
import com.djiguiya.djiguiya.entity.enums.Role;
import com.djiguiya.djiguiya.repository.RoleTypeRepository;
import com.djiguiya.djiguiya.repository.UserRepository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
public class DataInitializer {
    @Value("${admin_username}")
    String username ;
    @Value("${admin_email}")
    String email;
    @Value("${admin_password}")
    String password;

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
            RoleType role = new RoleType();
            role.setLibelle(Role.ROLE_ADMIN);


            // Vérifie si un admin existe déjà
            if (utilisateurRepository.findByUsername(username).isEmpty()) {
                Utilisateurs admin = new Utilisateurs();
                admin.setNom("Super");
                admin.setPrenom("Admin");
                admin.setUsername(username);
                admin.setEmail(email);
                admin.setMotDePasse(passwordEncoder.encode(password));
                admin.setActif(true);
                admin.setRole(role);

                utilisateurRepository.save(admin);
                System.out.println("Admin par défaut créé ");
            } else {
                System.out.println(" Admin déjà existant, aucune action.");
            }
        };
    }

}
