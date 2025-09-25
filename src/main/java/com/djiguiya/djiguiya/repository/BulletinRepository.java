package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Bulletin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BulletinRepository extends JpaRepository<Bulletin, Integer> {
}
