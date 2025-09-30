package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.service.ParrainService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("parrain")
public class ParrainController {

    private ParrainService parrainService;

    //Get all parrain
    @GetMapping
    public ResponseEntity<List<UtilisateurResponseDTO>> gettAllParrain () {
        return ResponseEntity.ok(
            parrainService.getAllSponsor()
        );
    }

    //Get a Parrain
    @GetMapping("/{idParrain}")
    public ResponseEntity<UtilisateurResponseDTO> getAParrain ( @PathVariable long idParrain) {
        return ResponseEntity.ok(
                parrainService.getParent(idParrain)
        );
    }

    //upddate a Parrain
    @PutMapping("/{idParrain}")
    public ResponseEntity<UtilisateurResponseDTO> updateParrainIformation (
            @PathVariable long idParrain,
            @RequestBody UtilisateurRegisteurDto sponsorInfo
        ) {
        return ResponseEntity.ok(
                parrainService.updateSponsorInformation(idParrain, sponsorInfo)
        );
    }

    //delete a Parrain
    @DeleteMapping("/{idParrain}")
    public boolean deleteParrain(@PathVariable long idParrain){
        parrainService.deleteSponsor(idParrain);
        return true;
    }

}
