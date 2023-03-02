package com.example.dogshelter.repositories;

import com.example.dogshelter.models.Dog;
import com.example.dogshelter.models.enums.DogBreed;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class DogRepositoryTest {
    @Autowired
    private DogRepository underTest;

    @Test
    void testIfAdoptionStatusWithIdWasUpdated() {
        //given
        Dog dog = new Dog(
                1L,
                DogBreed.MONGREL,
                "Rex",
                LocalDate.of(2011, 11, 11),
                LocalDate.of(2020, 12, 31),
                false
        );
        underTest.save(dog);

        //It is necessary because database generate ID automatically
        Long dogID = underTest.findAll().stream().findFirst().orElseThrow().getId();
        //when

        underTest.updateAdoptionStatusForDogWithId(dogID, true);

        //then
        assertTrue(underTest.getReferenceById(dogID).isAdopted());
    }

    @Test
    void testFindAllByAdoptedOrderById() {
        //given
        List<Dog> dogs = List.of(
                new Dog(DogBreed.MONGREL, "AAAA", LocalDate.of(2011, 11, 11)),
                new Dog(DogBreed.PUG, "BBBBB", LocalDate.of(2021, 12, 1)),
                new Dog(DogBreed.BOXER, "CCCC", LocalDate.of(2021, 10, 1)),
                new Dog(DogBreed.MONGREL, "DDDD", LocalDate.of(2011, 11, 11)),
                new Dog(DogBreed.PUG, "EEEE", LocalDate.of(2021, 12, 1)),
                new Dog(DogBreed.BOXER, "FFFF", LocalDate.of(2021, 10, 1))
        );
        //We are saving all dogs
        underTest.saveAll(dogs);
        //We set adoption status to true for dogs which are PUG breed
        dogs.stream().filter(d -> d.getDogBreed() == DogBreed.PUG).forEach(d -> d.setAdopted(true));
        //We are selecting dogs which have adopted filed set to false
        List<Dog> sortedDogs = underTest.findAll().stream().filter(d -> d.isAdopted() == false).sorted((o1, o2) -> Objects.equals(o1.getId(), o2.getId()) ? 0 : (o1.getId() > o2.getId() ? 1 : -1)).toList();

        //when
        List<Dog> dogsFromDb = underTest.findAllByAdoptedOrderById(false);

        //then
        assertIterableEquals(sortedDogs, dogsFromDb);
    }
}