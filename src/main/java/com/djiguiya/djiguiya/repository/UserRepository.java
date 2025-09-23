package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Utilisateurs;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Utilisateurs,Integer> {
    Optional<Utilisateurs> findByUsername(String username);
}
