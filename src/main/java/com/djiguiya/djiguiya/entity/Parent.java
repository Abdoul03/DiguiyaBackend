package com.djiguiya.djiguiya.entity;

import jakarta.persistence.*;

import java.util.Set;


@Entity
public class Parent extends Utilisateurs{
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "parent_enfant",
            joinColumns = @JoinColumn(name = "parent_Id"),
            inverseJoinColumns = @JoinColumn(name = "enfant_id")
    )
    private Set<Enfant> enfants;
}
