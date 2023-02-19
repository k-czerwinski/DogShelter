package com.example.dogshelter;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
public class DogShelterApplication {

    public static void main(String[] args) {
        SpringApplication.run(DogShelterApplication.class, args);
    }

}
