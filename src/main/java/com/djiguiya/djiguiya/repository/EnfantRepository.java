package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Enfant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface EnfantRepository extends JpaRepository<Enfant, Long> {
}
