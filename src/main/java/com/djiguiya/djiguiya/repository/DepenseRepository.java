package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Depense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DepenseRepository extends JpaRepository<Depense,Integer> {
}
