package com.example.dogshelter.repositories;

import com.example.dogshelter.models.Dog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Dog dog set dog.adopted = :adoptionStatus where dog.id = :id")
    void updateAdoptionStatusForDogWithId(@Param(value = "id") Long id, @Param(value = "adoptionStatus") boolean adoptionStatus);

    List<Dog> findAllByAdoptedOrderById(boolean adopted);
}
