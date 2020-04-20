package org.example.service;

import org.example.entity.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetService {
    private List<Pet> pets = new ArrayList<>();

    public PetService(){
        pets.add(new Pet(1, "Nelson", 1, "cat"));
        pets.add(new Pet(2, "Guru", 2, "rat"));
        pets.add(new Pet(3, "Justin", 1, "dog"));
    }

    public List<Pet> getAllPets() {
        return List.copyOf(pets);
    }
}
