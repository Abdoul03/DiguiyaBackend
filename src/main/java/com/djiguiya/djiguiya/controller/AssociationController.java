package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.dto.requestDto.AssociationRequest;
import com.djiguiya.djiguiya.dto.requestDto.ChildRegisteurDTO;
import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.AssociationResponse;
import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
import com.djiguiya.djiguiya.service.AssociationService;
import com.djiguiya.djiguiya.service.EnfantService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("association")
public class AssociationController {

    private final AssociationService associationService;
    private final EnfantService enfantService;

   //Get all associations
    @GetMapping
    public ResponseEntity<List<AssociationResponse>> getAllAssociation(){
        return ResponseEntity.ok(associationService.getAssociations());
    }
    //Get a association
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

    // ajouter un enfant
    @PostMapping("/enfant")
    public ResponseEntity<ChildResponseDTO> addChild(@RequestBody ChildRegisteurDTO enfant) {
        return ResponseEntity.ok(enfantService.createChild(enfant));
    }

    //update Child
    @PutMapping("/enfant/{childId}")
    public ResponseEntity<ChildResponseDTO> updateChild(
            @PathVariable long childId, @RequestBody ChildRegisteurDTO enfant) throws AccessDeniedException {
        return ResponseEntity.ok(enfantService.updateChild(childId, enfant));
    }

    //Delete child
    @DeleteMapping("/enfant/{childId}")
    public void deleteChild (@PathVariable long childId) throws Exception {
        enfantService.deleteChild(childId);
    }
}
