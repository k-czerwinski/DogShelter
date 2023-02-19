package com.example.dogshelter.controllers;

import com.example.dogshelter.models.Dog;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import com.example.dogshelter.services.DogService;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class DogController {
    private final DogService dogService;

    @Autowired
    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @RequestMapping("/")
    public String mainPage() {
        return "index";
    }

    @RequestMapping("/addDogForm")
    public String addDog(Model model) {
        model.addAttribute("dog", new Dog());
        return "add-dog-form";
    }

    @PostMapping("/addDog")
    public String addDogToDb(@Valid @ModelAttribute("dog") Dog dog, BindingResult result) {
        if(result.hasErrors())
            return "add-dog-form";
        dogService.addDog(dog);
        return "index";
    }

    @GetMapping(path = "/dog/{id}")
    @ResponseBody
    public Optional<Dog> getDog(@PathVariable("id") Long id) {
        return dogService.getDogById(id);
    }

    @GetMapping(path = "/viewAll")
    public String viewAll(Model model) {
        model.addAttribute("dogs", dogService.viewAll());
        return "view-all-dogs";
    }

    @GetMapping("/adoptDogForm")
    public String adoptDog(Model model){
        model.addAttribute("dogs",dogService.viewAll());
        return "adopt-dog-form";
    }

//    For now not working properly
    @PutMapping("/adoptDog")
    public String updateAdoptedStatus(@PathVariable("id") Long id, BindingResult result){
        if(dogService.updateAdoptionStatus(id,true))
            return "index";
        result.addError(new ObjectError("id","Incorrect ID chosen"));
        return "adopt-dog-form";
    }

}
