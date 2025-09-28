package com.djiguiya.djiguiya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String trimestre;
    private int moyenneGenerale;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bulletin_id", nullable = false)
    private Association association;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "matier_bulletin",
            joinColumns = @JoinColumn(name = "bulletin_id"),
            inverseJoinColumns = @JoinColumn(name = "matier_id")
    )
    private Set<Matiere> matiers;
}
