package com.djiguiya.djiguiya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Besoin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nom;
    private String description;
    private int montant_necessaire;

    @ManyToMany(mappedBy = "besoins",fetch = FetchType.LAZY)
    private List<Enfant> enfants;

    @ManyToMany(mappedBy = "besoins", fetch = FetchType.LAZY)
    private List<Depense> depenses;
}
