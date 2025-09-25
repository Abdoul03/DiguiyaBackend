package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Matiere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface MatiereRepository extends JpaRepository<Matiere, Integer> {
}
