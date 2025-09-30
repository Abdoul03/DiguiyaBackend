package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.entity.enums.PayementMethode;
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
public class Parrain extends Utilisateurs{
    private PayementMethode methodeDePayment;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Parrainage> parrainages = new HashSet<>();

    @OneToMany(mappedBy = "parrain", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Payement> payements = new HashSet<>();


}
