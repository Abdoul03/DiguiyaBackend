package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Besoin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BesoinRepository extends JpaRepository<Besoin, Integer> {
}
