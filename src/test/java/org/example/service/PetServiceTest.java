package org.example.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PetServiceTest {

    private PetService petService = new PetService();

    @Test
    void serviceReturnListOfPetsSuccessfully() {
        Assertions.assertEquals(3, petService.getAllPets().size());
    }
}
