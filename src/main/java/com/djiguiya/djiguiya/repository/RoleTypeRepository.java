package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.RoleType;
import com.djiguiya.djiguiya.entity.enums.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleTypeRepository extends CrudRepository<RoleType, Integer> {
    Optional<RoleType> findByLibelle(Role libelle);
}
