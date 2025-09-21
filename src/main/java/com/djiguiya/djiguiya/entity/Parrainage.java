package com.djiguiya.djiguiya.entity;

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
public class Parrainage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    @Column(nullable = false)
    private Date date;
    @Column(nullable = false)
    private int fonds;
}
