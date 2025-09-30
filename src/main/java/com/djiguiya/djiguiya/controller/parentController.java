package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.service.ParentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("parent")
public class parentController {

    private ParentService parentService;

    //Get all parents
    @GetMapping
    public ResponseEntity<List<UtilisateurResponseDTO>> getAllParent (){
        return ResponseEntity.ok(parentService.getAllParent());
    }
    //Get a parent
    @GetMapping("/{parentId}")
    public ResponseEntity<UtilisateurResponseDTO> getAParent(@PathVariable long parentId){
        return ResponseEntity.ok(parentService.getParent(parentId));
    }
    //update Parent
    @PutMapping
    public ResponseEntity<UtilisateurResponseDTO> updateParent(UtilisateurRegisteurDto parent){
        return ResponseEntity.ok(parentService.updateParent(parent));
    }
    //delete a Parent
    public boolean deleteParent(long parentId){
        return parentService.deleteParent(parentId);
    }
}
