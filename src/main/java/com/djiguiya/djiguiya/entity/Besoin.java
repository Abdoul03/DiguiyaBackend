package com.djiguiya.djiguiya.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Besoin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String nom;
    private String description;
    private int montant_necessaire;
}
