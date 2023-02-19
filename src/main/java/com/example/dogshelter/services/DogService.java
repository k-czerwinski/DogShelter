package com.example.dogshelter.services;

import com.example.dogshelter.models.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.dogshelter.repositories.DogRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DogService {
    private final DogRepository dogRepository;

    @Autowired
    public DogService(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    public void addDog(Dog dog){
        dogRepository.save(dog);
    }

    public Optional<Dog> getDogById(Long id){
        return  dogRepository.findById(id);
    }

    public List<Dog> viewAll(){
        return dogRepository.findAll();
    }

    //Return true if adoption status has been set succesfully, otherwise return false
    public boolean updateAdoptionStatus(Long id, boolean adoptionStatus){
        if (dogRepository.existsById(id)){
            dogRepository.updateAdoptionStatus(id,adoptionStatus);
            return true;
        }
        return false;
    }
}
