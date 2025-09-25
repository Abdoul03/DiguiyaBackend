package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Parrain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ParrainRepository extends JpaRepository<Parrain, Integer> {
}
