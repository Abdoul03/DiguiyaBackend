package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Utilisateurs;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Utilisateurs,Integer> {
}
