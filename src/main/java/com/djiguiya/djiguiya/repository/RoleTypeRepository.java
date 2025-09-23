package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.RoleType;
import com.djiguiya.djiguiya.entity.Utilisateurs;
import com.djiguiya.djiguiya.entity.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleTypeRepository extends JpaRepository<RoleType, Integer> {
    Optional<RoleType> findByLibelle(Role libelle);
}
