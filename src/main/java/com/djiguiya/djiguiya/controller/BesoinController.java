package com.djiguiya.djiguiya.controller;

import com.djiguiya.djiguiya.dto.requestDto.BesoinRequest;
import com.djiguiya.djiguiya.dto.responseDto.BesoinResponse;
import com.djiguiya.djiguiya.service.BesoinService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("besoin")
public class BesoinController {
    private BesoinService besoinService;

    @PostMapping
    public ResponseEntity<BesoinResponse> addBesoin(@RequestBody BesoinRequest besoinRequest){
        return ResponseEntity.ok(besoinService.createNeed(besoinRequest));
    }

    @GetMapping
    public ResponseEntity<List<BesoinResponse>> getAllNeed(){
        return ResponseEntity.ok(besoinService.getAllNeed());
    }

    @GetMapping("/{besoinId}")
    public ResponseEntity<BesoinResponse> getANeed(@PathVariable int besoinId){
        return ResponseEntity.ok(besoinService.getANeed(besoinId));
    }

    @PutMapping("/{besoinId}")
    public ResponseEntity<BesoinResponse> updateNeed(@PathVariable int besoinId, @RequestBody BesoinRequest besoinRequest){
        return ResponseEntity.ok(besoinService.updateNeed(besoinId,besoinRequest));
    }

    @DeleteMapping("/{besoinId}")
    public boolean deleteNeed(@PathVariable int besoinId){
        return besoinService.deleteNeed(besoinId);
    }
}
