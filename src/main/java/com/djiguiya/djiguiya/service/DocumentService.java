package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.entity.Association;
import com.djiguiya.djiguiya.entity.Document;
import com.djiguiya.djiguiya.entity.Enfant;
import com.djiguiya.djiguiya.repository.AssociationRepository;
import com.djiguiya.djiguiya.repository.DocumentRepository;
import com.djiguiya.djiguiya.repository.EnfantRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@AllArgsConstructor
@Service
public class DocumentService {
    private DocumentRepository documentRepository;
    private AssociationRepository associationRepository;
    private EnfantRepository enfantRepository;

    @Value("${upload.dir}")
    private String uploadDir;

    public Document uploadFile(MultipartFile file, long enfantId) throws IOException {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long associationId = Long.valueOf(authentication.getPrincipal().toString());

        if (authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ASSOCIATION"))) {
            throw new RuntimeException("Seules les associations peuvent ajouter des documents");
        }

        Association association = associationRepository.findById(associationId).orElseThrow(()-> new RuntimeException("Association non trouver"));
        Enfant enfant = enfantRepository.findById(enfantId).orElseThrow(()-> new RuntimeException("Enfant non trouver"));

        // Sauvegarde du fichier sur disque
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        // Enregistrer en base
        Document document = new Document();

        document.setFileName(file.getOriginalFilename());
        document.setFileType(file.getContentType());
        document.setFilePath(filePath.toString());
        document.setAssociation(association);
        document.setEnfant(enfant);

        return documentRepository.save(document);
    }

    public List<Document> getAssociationDocument(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long associationId = Long.valueOf(authentication.getPrincipal().toString());

        if (authentication.getAuthorities().stream()
                .noneMatch(a -> a.getAuthority().equals("ROLE_ASSOCIATION"))) {
            throw new RuntimeException("Seules les associations peuvent acceder a ces documents");
        }

        return documentRepository.findByAssociationId(associationId);

    }

    public List<Document> getChildDocument(long childId){
        return documentRepository.findByEnfantId(childId);
    }
}
