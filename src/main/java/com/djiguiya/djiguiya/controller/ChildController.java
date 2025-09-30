package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.dto.requestDto.ChildRegisteurDTO;
import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
import com.djiguiya.djiguiya.service.EnfantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("enfant")
public class ChildController {
    private EnfantService enfantService;

    // ajouter un enfant
    @PostMapping("/enfant")
    public ResponseEntity<ChildResponseDTO> addChild(@RequestBody ChildRegisteurDTO enfant) {
        return ResponseEntity.ok(enfantService.createChild(enfant));
    }

    //Afficher tous les enfant
    @GetMapping
    public ResponseEntity<List<ChildResponseDTO>> getAllChild (){
        return ResponseEntity.ok(enfantService.getChildren());
    }
    //Afficher un enfant
    @GetMapping("/{childId}")
    public ResponseEntity<ChildResponseDTO> getAChild(long childId){
        return ResponseEntity.ok(enfantService.getChild(childId));
    }
    //get all child
    @GetMapping("/enfant")
    public ResponseEntity<List<ChildResponseDTO>> getAssociationAllChild () throws AccessDeniedException {
        return ResponseEntity.ok(enfantService.getAssoChild());
    }

    //update Child
    @PutMapping("/{childId}")
    public ResponseEntity<ChildResponseDTO> updateChild(
            @PathVariable long childId, @RequestBody ChildRegisteurDTO enfant) throws AccessDeniedException {
        return ResponseEntity.ok(enfantService.updateChild(childId, enfant));
    }
    //Delete child
    @DeleteMapping("/{childId}")
    public void deleteChild (@PathVariable long childId) throws Exception {
        enfantService.deleteChild(childId);
    }
}
