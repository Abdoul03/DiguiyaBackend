package com.djiguiya.djiguiya.entity;

import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
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
public class Enfant extends Utilisateurs{
    @Column(nullable = false)
    private String classe;
    @Column(nullable = false)
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "association_id", nullable = false)
    private Association association;

    /*public ChildResponseDTO  toResponse(){
        return new ChildResponseDTO(getId(),getNom(),getPrenom(),getUsername(),getClasse(),getAge(),getTelephone(),getEmail(),getGenre(),getRole(), getAssociation().getId());
    }*/
}
