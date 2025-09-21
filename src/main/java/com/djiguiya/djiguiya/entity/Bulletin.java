package com.djiguiya.djiguiya.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Bulletin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String trimestre;
    private int moyenneGenerale;
}
