package org.example.controller.api;

import org.example.entity.Pet;
import org.example.service.PetService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * CRUD operations for pets
 * https://software.danielwatrous.com/restful-java-servlet-serializing-tofrom-json-with-jackson/
 */
@WebServlet(name ="api_pets", urlPatterns = "/api/pets")
public class PetRestAPI extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(PetRestAPI.class);

    /**
     * Show all pets.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");

        PetService petService = new PetService();
        List<Pet> allPets = petService.getAllPets();
        String json = allPets.stream().map(Pet::toString).collect(Collectors.toList()).toString();

        log.info("return pets: {}", json);

        printWriter.println(json);
    }
}