package com.example.dogshelter.services;

import org.springframework.stereotype.Service;
import com.example.dogshelter.repositories.DogRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DogService {
    private final DogRepository dogRepository;

    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    //Return true if adoption status has been set successfully, otherwise return false
    @Transactional
    public boolean updateAdoptionStatus(Long id, boolean adoptionStatus) {
        if (dogRepository.existsById(id)) {
            dogRepository.updateAdoptionStatusForDogWithId(id, adoptionStatus);
            return true;
        }
        return false;
    }
}
