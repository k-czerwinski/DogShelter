package com.example.dogshelter.controllers;

import com.example.dogshelter.models.Dog;

import com.example.dogshelter.repositories.DogRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.dogshelter.services.DogService;

@Controller
@RequestMapping("/")
public class DogController {
    private final DogService dogService;
    private final DogRepository dogRepository;

    @Autowired
    public DogController(DogService dogService, DogRepository dogRepository) {
        this.dogService = dogService;
        this.dogRepository = dogRepository;
    }

    @RequestMapping("/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping("/addDogForm")
    public String addDogForm(Model model) {
        model.addAttribute("dog", new Dog());
        return "add-dog-form";
    }

    @PostMapping("/addDog")
    public String addDog(@Valid @ModelAttribute("dog") Dog dog, BindingResult result, Model model) {
        if (result.hasErrors())
            return "add-dog-form";
        dogRepository.save(dog);
        model.addAttribute("message", "Dog successfully added");
        return "message-template";
    }

    @GetMapping(path = "/viewAll")
    public String viewAll(Model model) {
        model.addAttribute("dogs", dogRepository.findAll());
        return "view-all-dogs";
    }

    @GetMapping("/adoptDogForm")
    public String adoptDogForm(Model model) {
        model.addAttribute("dogs", dogRepository.findAllByAdoptedOrderById(false));
        return "adopt-dog-form";
    }

    //    For now not working properly
    @PostMapping("/adoptDog")
    public String adoptDog(@RequestParam(value = "id", required = false, defaultValue = "0") Long id, Model model) {
        if (dogService.updateAdoptionStatus(id, true)) {
            model.addAttribute("message", "Dog successfully adopted");
            return "message-template";
        }
        model.addAttribute("dogs", dogRepository.findAllByAdoptedOrderById(false));
        model.addAttribute("errorMessage", "Incorrect ID chosen");
        return "adopt-dog-form";
    }
}
