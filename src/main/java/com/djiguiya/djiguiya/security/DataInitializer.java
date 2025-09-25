package com.djiguiya.djiguiya.security;

import com.djiguiya.djiguiya.entity.Admin;
import com.djiguiya.djiguiya.entity.enums.Role;
import com.djiguiya.djiguiya.repository.AdminRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Value("${admin_username}")
    String username ;
    @Value("${admin_email}")
    String email;
    @Value("${admin_password}")
    String password;


    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(AdminRepository adminRepository, PasswordEncoder passwordEncoder) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    @Transactional
    public CommandLineRunner initAdmin() {
        return args -> {
            // Vérifie si un admin existe déjà
            if (adminRepository.findByUsername(username).isEmpty()) {
                Admin admin = new Admin();
                admin.setNom("Super");
                admin.setPrenom("Admin");
                admin.setUsername(username);
                admin.setEmail(email);
                admin.setMotDePasse(passwordEncoder.encode(password));
                admin.setActif(true);
                admin.setRole(Role.ROLE_ADMIN);

                adminRepository.save(admin);
                System.out.println("Admin par défaut créé ");
            } else {
                System.out.println(" Admin déjà existant, aucune action.");
            }
        };
    }

}
