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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private int montant;
    @Column(nullable = false)
    private PayementMethode methodeDePayement;
    @Column(nullable = false)
    private Date date;
}
