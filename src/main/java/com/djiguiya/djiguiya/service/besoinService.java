package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.mapper.BesoinMapper;
import com.djiguiya.djiguiya.dto.requestDto.BesoinRequest;
import com.djiguiya.djiguiya.dto.responseDto.BesoinResponse;
import com.djiguiya.djiguiya.entity.Besoin;
import com.djiguiya.djiguiya.repository.BesoinRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class besoinService {
    private BesoinRepository besoinRepository;

    //create need
    public BesoinResponse createNeed(BesoinRequest besoinRequest){
        Besoin besoin = BesoinMapper.toEntity(besoinRequest, new Besoin());
        besoinRepository.save(besoin);
        return BesoinMapper.toResponse(besoin);
    }
    //Get all Needs
    public List<BesoinResponse> getAllNeed(){
        List<Besoin> besoin = besoinRepository.findAll();
        return besoin.stream().map(BesoinMapper::toResponse).toList();
    }
    //Get a Need
    public BesoinResponse getANeed(int needId){
        Besoin need = besoinRepository.findById(needId).orElseThrow(()-> new EntityNotFoundException("Besoin introuvable"));
        return BesoinMapper.toResponse(need);
    }
    //Update a need
    public BesoinResponse updateNeed (int needId, BesoinRequest besoin){
        Besoin need = besoinRepository.findById(needId).orElseThrow(()-> new EntityNotFoundException("Besoin introuvable"));
        need.setNom(besoin.nom());
        need.setDescription(besoin.description());
        need.setMontant_necessaire(besoin.montant());
        besoinRepository.save(need);
        return BesoinMapper.toResponse(need);
    }
    //Delete a need
    public boolean deleteNeed(int needId){
        Besoin need = besoinRepository.findById(needId).orElseThrow(()-> new EntityNotFoundException("Besoin introuvable"));
        return true;
    }

}
