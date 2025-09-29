package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Admin;
import com.djiguiya.djiguiya.entity.Utilisateurs;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Optional<Admin> findByUsername(String username);
}
