package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.entity.enums.Genre;
import com.djiguiya.djiguiya.entity.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Utilisateurs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String telephone;

    @Column(nullable = false, unique = true)
    private String email;

    private String photoUrl;

    @Column(nullable = false)
    private String motDePasse;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

}
