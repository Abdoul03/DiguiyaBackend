package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.entity.enums.Justificatif;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Depense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private int Montant;
    @Column(nullable = false)
    private Justificatif justificatif;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "besoin_depense",
            joinColumns = @JoinColumn(name = "depense_id"),
            inverseJoinColumns = @JoinColumn(name = "besoin_id")
    )
    private Set<Besoin> besoins;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id", nullable = false)
    private Association association;

}
