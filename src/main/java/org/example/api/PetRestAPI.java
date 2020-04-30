package org.example.api;

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

@WebServlet(name ="api_pets", urlPatterns = "/api/pets")
public class PetRestAPI extends HttpServlet {

    /*
    CRUD
    C - post (json object)
    R - get (json object or list json's objects)
    U - put (json object)
    D - delete (id)
     */

    private static Logger log = LoggerFactory.getLogger(org.example.controller.PetController.class);

    PetService petService = new PetService();

    /**
     * Show all pets.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String contextPath = request.getContextPath();
        PrintWriter printWriter = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");


        List<Pet> allPets = petService.getAllPets();
        String json = allPets.stream().map(Pet::toString).collect(Collectors.toList()).toString();

        log.info("return pets: {}", json);

        printWriter.println(json);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
//        Stream<String> lines = request.getReader().lines();

        List<Pet> allPets = petService.getAllPets();
        String json = allPets.stream().map(Pet::toString).collect(Collectors.toList()).toString();

        log.info("return pets: {}", json);

        printWriter.println(json);
    }
}