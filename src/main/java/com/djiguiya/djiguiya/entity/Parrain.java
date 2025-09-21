package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.entity.enums.PayementMethode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Parrain extends Utilisateurs{
    private PayementMethode methodeDePayment;
}
