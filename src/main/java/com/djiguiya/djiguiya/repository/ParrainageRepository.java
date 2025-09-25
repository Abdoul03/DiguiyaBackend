package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Parrainage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ParrainageRepository extends JpaRepository<Parrainage,Integer> {
}
