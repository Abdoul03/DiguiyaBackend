package com.djiguiya.djiguiya.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Parrainage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private int fonds;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payement> payements = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Association association;
}
