package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.entity.enums.Genre;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String telephone;

    @Column(unique = true)
    private String email;

    private boolean actif = false;

    private String photoUrl;

    @Column(nullable = false)
    private String motDePasse;

    private Genre genre;


    @OneToOne(cascade = CascadeType.ALL)
    @Enumerated(EnumType.STRING)
    private RoleType role;
}
