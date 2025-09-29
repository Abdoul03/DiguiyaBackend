package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.dto.mapper.UtilisateurMapper;
import com.djiguiya.djiguiya.dto.requestDto.UtilisateurRegisteurDto;
import com.djiguiya.djiguiya.dto.responseDto.UtilisateurResponseDTO;
import com.djiguiya.djiguiya.entity.Parrain;
import com.djiguiya.djiguiya.entity.enums.Role;
import com.djiguiya.djiguiya.repository.ParrainRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ParrainService {

    private final ParrainRepository parrainRepository;
    private final PasswordEncoder passwordEncoder;

    //Create a sponsor
    public UtilisateurResponseDTO createParrain(UtilisateurRegisteurDto user){
        Parrain parrain = UtilisateurMapper.toEntity(user, new Parrain());
        parrain.setRole(Role.ROLE_PARRAIN);
        parrain.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
        parrain.setActif(true);
        Parrain saved = parrainRepository.save(parrain);
        return UtilisateurMapper.toDTO(saved);
    }
    //Get all sponsor
    public List<UtilisateurResponseDTO> getAllSponsor(){
        List<Parrain> sponsor = parrainRepository.findAll();
        if (sponsor.isEmpty()) return new ArrayList<>();
        return sponsor.stream().map(UtilisateurMapper::toDTO).toList();
    }
    //Get a sponsor
    public UtilisateurResponseDTO getParent(long sponsorId){
        Parrain sponsor = parrainRepository.findById(sponsorId).orElseThrow(()-> new EntityNotFoundException("Parrain non trouvé"));

        return UtilisateurMapper.toDTO(sponsor);
    }
    //Update sponsor information
    public UtilisateurResponseDTO updateSponsorInformation(long sponsorId, UtilisateurRegisteurDto sponsorInfo){
        Parrain sponsor = parrainRepository.findById(sponsorId).orElseThrow(()-> new EntityNotFoundException("Parrain non trouvé"));

        sponsor.setNom(sponsorInfo.getNom());
        sponsor.setPrenom(sponsorInfo.getPrenom());
        sponsor.setMotDePasse(passwordEncoder.encode(sponsorInfo.getMotDePasse()));
        sponsor.setTelephone(sponsorInfo.getTelephone());
        sponsor.setEmail(sponsorInfo.getEmail());
        sponsor.setGenre(sponsorInfo.getGenre());

        parrainRepository.save(sponsor);
        return UtilisateurMapper.toDTO(sponsor);
    }
    //Delete Sponsor
    public boolean deleteSponsor(long sponsorId){
        Parrain sponsor = parrainRepository.findById(sponsorId).orElseThrow(()-> new EntityNotFoundException("Parrain non trouvé"));
        parrainRepository.delete(sponsor);
        return  true;
    }
}
