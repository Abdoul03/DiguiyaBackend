package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.entity.enums.Justificatif;
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

}
