package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.entity.enums.Role;
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
public class RoleType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private  int id;

    private Role libelle;
}
