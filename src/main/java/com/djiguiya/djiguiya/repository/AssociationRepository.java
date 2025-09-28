package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Association;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface AssociationRepository extends JpaRepository<Association,Long> {
    Optional<Association> findByUsername(String username);
}
