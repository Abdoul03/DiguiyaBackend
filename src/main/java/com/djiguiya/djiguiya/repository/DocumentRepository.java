package com.djiguiya.djiguiya.repository;

import com.djiguiya.djiguiya.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByAssociationId(Long associationId);
    List<Document> findByEnfantId(Long enfantId);
}
