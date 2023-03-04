package com.example.dogshelter.models.enums;

public enum DogBreed {
    BOXER("Boxer"),
    DOBERMANN("Doberman"),
    PUG("Pug"),
    CHIHUAHUA("Chihuahua"),
    ROTTWEILER("Rottweiler"),
    MONGREL("Mongrel");

    private final String displayValue;

    private DogBreed(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return this.displayValue;
    }
}
