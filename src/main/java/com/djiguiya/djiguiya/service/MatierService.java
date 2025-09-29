package com.djiguiya.djiguiya.service;

import com.djiguiya.djiguiya.entity.Matiere;
import com.djiguiya.djiguiya.repository.MatiereRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class MatierService {
    private MatiereRepository matiereRepository;

    //create a subject
    public Matiere createSubject(Matiere matiere){
        return matiereRepository.save(matiere);
    }
    //Get All subject
    public List<Matiere> getAllSubject(){
        List<Matiere> allSubject = matiereRepository.findAll();
        return allSubject.stream().toList();
    }
    //Get a subject
    public Matiere getSubject(int subjectId){
        return matiereRepository.findById(subjectId).orElseThrow(()-> new EntityNotFoundException("Modul nom trouver"));
    }
    //delete subject
    public boolean deleteSubject (int subjectId){
        Matiere subject = matiereRepository.findById(subjectId).orElseThrow(()-> new EntityNotFoundException("Modul nom trouver"));
        return true;
    }
}
