package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Validatore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidatoreRepository extends JpaRepository<Validatore,Integer> {
    Optional<Validatore> findByCode(String code);
}
