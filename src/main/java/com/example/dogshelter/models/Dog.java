package com.example.dogshelter.models;

import com.example.dogshelter.models.enums.DogBreed;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
@Table(name = "dogs")
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private DogBreed dogBreed;
    @NotEmpty(message = "Dog's name cannot be empty")
    @Size(min = 2, max = 20)
    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    @NotNull(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDate inShelterSinceWhen;
    @Column(columnDefinition = "boolean default false")
    private boolean adopted;

    public Dog() {
    }

    public Dog(Long id, DogBreed dogBreed, String name, LocalDate dateOfBirth, LocalDate inShelterSinceWhen, boolean adopted) {
        this.id = id;
        this.dogBreed = dogBreed;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.inShelterSinceWhen = inShelterSinceWhen;
        this.adopted = adopted;
    }

    public Dog(DogBreed dogBreed, String name, LocalDate dateOfBirth) {
        this.dogBreed = dogBreed;
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DogBreed getDogBreed() {
        return dogBreed;
    }

    public void setDogBreed(DogBreed dogBreed) {
        this.dogBreed = dogBreed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDate getInShelterSinceWhen() {
        return inShelterSinceWhen;
    }

    public void setInShelterSinceWhen(LocalDate inShelterSinceWhen) {
        this.inShelterSinceWhen = inShelterSinceWhen;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "id=" + id +
                ", dogBreed=" + dogBreed +
                ", name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", inShelterSinceWhen=" + inShelterSinceWhen +
                ", adopted=" + adopted +
                '}';
    }
}
