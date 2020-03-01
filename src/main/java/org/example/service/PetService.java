package org.example.service;

import org.example.entity.Pet;

import java.util.ArrayList;
import java.util.List;

public class PetService {
    private List<Pet> pets = new ArrayList<>();

    public PetService(){
        pets.add(new Pet("Nelson", 1, "cat"));
        pets.add(new Pet("Guru", 2, "rat"));
        pets.add(new Pet("Justin", 1, "dog"));
    }

    public List<Pet> getAllPets() {
        return List.copyOf(pets);
    }
}
