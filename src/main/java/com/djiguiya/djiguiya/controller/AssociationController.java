package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.dto.requestDto.AssociationRequest;
import com.djiguiya.djiguiya.dto.requestDto.ChildRegisteurDTO;
import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.AssociationResponse;
import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
import com.djiguiya.djiguiya.entity.Document;
import com.djiguiya.djiguiya.service.AssociationService;
import com.djiguiya.djiguiya.service.DocumentService;
import com.djiguiya.djiguiya.service.EnfantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("association")
public class AssociationController {

    private final AssociationService associationService;

   //Get all associations
    @GetMapping
    public ResponseEntity<List<AssociationResponse>> getAllAssociation(){
        return ResponseEntity.ok(associationService.getAssociations());
    }
    //Get association
    @GetMapping("/{associationId}")
    public ResponseEntity<AssociationResponse> getAAssociation(@PathVariable int associationId){
        return ResponseEntity.ok(associationService.getAnAssociation(associationId));
    }
    //update association
    @PutMapping("/{associationId}")
    public ResponseEntity<AssociationResponse> updateAssociation(@PathVariable int associationId, @RequestBody AssociationRequest association){
        return ResponseEntity.ok(associationService.updateAssociation(associationId,association));
    }
    //delete association
    @DeleteMapping("/{associationId}")
    public boolean deleteAssociation(@PathVariable int associationId){
        return associationService.deleteAssociation(associationId);
    }

}
