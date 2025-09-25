package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Payement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface PayementRepository extends JpaRepository<Payement, Integer> {
}
