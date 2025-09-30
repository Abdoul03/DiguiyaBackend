package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.entity.Document;
import com.djiguiya.djiguiya.service.DocumentService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("document")
public class DocumentController {
    private final DocumentService documentService;

    // Upload fichier (image ou pdf)
    @PostMapping("/upload")
    public ResponseEntity<Document> uploadFile(@RequestParam("file") MultipartFile file, @PathVariable long childId) throws IOException {
        Document document = documentService.uploadFile(file,childId);
        return ResponseEntity.ok(document);
    }

    // Récupérer les documents de l’association
    @GetMapping("/association")
    public ResponseEntity<List<Document>> getAssociationDocuments() {
        return ResponseEntity.ok(documentService.getAssociationDocument());
    }

    // Get a child's document
    @GetMapping("/enfant/{childId}")
    public ResponseEntity<List<Document>> getChildDocument(long childId){
        return ResponseEntity.ok(documentService.getChildDocument(childId));
    }
}
