package com.djiguiya.djiguiya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Validatore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private Instant creation;
    private Instant expire;
    private Instant activation;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    private Utilisateurs utilisateur;
}
