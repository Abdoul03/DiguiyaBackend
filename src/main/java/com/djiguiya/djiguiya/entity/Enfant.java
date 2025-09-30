package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Enfant extends Utilisateurs{
    @Column(nullable = false)
    private String classe;
    @Column(nullable = false)
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id", nullable = false)
    private Association association;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Parrainage> parrainage = new HashSet<>();

    @ManyToMany(mappedBy = "enfants", fetch = FetchType.LAZY)
    private Set<Parent> parent;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "enfant_besoin",
            joinColumns = @JoinColumn(name = "enfant_Id"),
            inverseJoinColumns = @JoinColumn(name = "besoin_id")
    )
    private Set<Besoin> besoins;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "enfant")
    private Set<Document> documents;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Payement> payements;

}
