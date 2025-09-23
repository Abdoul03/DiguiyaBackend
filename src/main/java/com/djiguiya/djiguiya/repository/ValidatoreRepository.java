package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Validatore;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ValidatoreRepository extends CrudRepository<Validatore,Integer> {
    Optional<Validatore> findByCode(String code);
}
