package com.example.dogshelter.services;

import com.example.dogshelter.models.Dog;
import com.example.dogshelter.models.enums.DogBreed;
import com.example.dogshelter.repositories.DogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DogServiceTest {

    @Mock
    private DogRepository dogRepository;
    private DogService underTest;

    @BeforeEach
    void setUp() {
        underTest = new DogService(dogRepository);
    }

    //It checks if updateAdoptionStatusForDogWithId is not involved when there is no dog with given ID
    @Test
    void checkUpdateAdoptionStatusNegativeCase() {
        //when
        underTest.updateAdoptionStatus(1L, true);
        //then
        verify(dogRepository, never()).updateAdoptionStatusForDogWithId(1L, true);
    }

    //It checks if updateAdoptionStatusForDogWithId is not involved when there is dog with given ID
    @Test
    void checkUpdateAdoptionStatusPositiveCase() {
        //given
        Long dogId = 1L;
        List<Dog> dogList = List.of(new Dog(
                dogId,
                DogBreed.MONGREL,
                "Rex",
                LocalDate.of(2011, 11, 11),
                LocalDate.of(2020, 12, 31),
                false
        ));

        when(dogRepository.existsById(any(Long.class))).thenReturn(dogList.stream().anyMatch(d -> d.getId().equals(dogId)));
        doAnswer(a -> {
            dogList.stream()
                    .filter(d -> d.getId().equals(dogId))
                    .findFirst().orElseThrow()
                    .setAdopted(true);
            return null;
        }).when(dogRepository).updateAdoptionStatusForDogWithId(dogId, true); //We can also use (any(Long.class),any(boolean.class))

        //when
        underTest.updateAdoptionStatus(dogId, true);

        //then
        assertTrue(dogList.stream().filter(d -> d.getId().equals(dogId)).findFirst().orElseThrow().isAdopted());
    }
}