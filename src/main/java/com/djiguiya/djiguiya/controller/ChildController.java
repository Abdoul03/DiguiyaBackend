package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.dto.responseDto.ChildResponseDTO;
import com.djiguiya.djiguiya.service.EnfantService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("enfant")
public class ChildController {
    private EnfantService enfantService;

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
}
