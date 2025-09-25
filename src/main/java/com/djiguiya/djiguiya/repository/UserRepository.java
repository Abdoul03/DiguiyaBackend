package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface UserRepository extends JpaRepository<Utilisateurs,Integer> {
    Optional<Utilisateurs> findByUsername(String username);
}
