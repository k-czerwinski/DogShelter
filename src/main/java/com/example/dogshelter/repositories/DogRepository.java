package com.example.dogshelter.repositories;


import com.example.dogshelter.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends JpaRepository<Dog,Long> {
    @Modifying
    @Query("update Dog dog set dog.wasAdopted = :adoptionStatus where dog.id = :id")
    void updateAdoptionStatus(@Param(value = "id") long id, @Param(value = "adoptionStatus") boolean adoptionStatus);
}
