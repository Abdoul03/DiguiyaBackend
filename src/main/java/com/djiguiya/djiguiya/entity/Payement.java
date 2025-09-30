package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.entity.enums.PayementMethode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Payement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private int montant;
    @Column(nullable = false)
    private PayementMethode methodeDePayement;
    @Column(nullable = false)
    private Date date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parrain_id", nullable = false)
    private Parrain parrain;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "enfant_id", nullable = false)
    private Enfant enfant;

    @ManyToOne(fetch = FetchType.LAZY)
    private Parrainage parrainage;

    @ManyToOne(fetch = FetchType.LAZY)
    private Association association;
}
