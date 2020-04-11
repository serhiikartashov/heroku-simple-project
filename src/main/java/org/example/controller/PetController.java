package org.example.controller;

import j2html.tags.ContainerTag;
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
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

import static j2html.TagCreator.*;

/**
 * https://readlearncode.com/servlet-4-features/
 * Look at push feature (parallel pushes for content, images, others)
 */
@WebServlet(name ="pets", urlPatterns = "/pets")
public class PetController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(PetController.class);

    /**
     * Show all pets.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();

        PetService petService = new PetService();
        List<Pet> allPets = petService.getAllPets();
        ContainerTag petHtmlpage = html(
                head(
                        title("Pet List"),
                        link()
                                .withRel("stylesheet")
                                .withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css")
                ),
                body(
                        div(attrs("#pets"),
                                p("Some sibling element"),
                                each(allPets, pet ->
                                        div(attrs(".pet"),
                                                h2(pet.getName()),
                                                h2(String.valueOf(pet.getAge())),
//                                                img().withSrc(pet.getImage()),
                                                p(pet.getBreed())
                                        )
                                )
                        )
                )
        );
        log.info("return pets: {}", allPets.stream().map(Pet::toString).collect(Collectors.toList()).toString());
        try {
            Connection connection = PetController.getConnection();
            if (connection!=null) {
                log.info("got connection to DB");
            }
        } catch (URISyntaxException | SQLException e) {
            log.error("Can't connect to DB", e);
        }
        printWriter.println(petHtmlpage.render());
    }

    private static Connection getConnection() throws URISyntaxException, SQLException {
        String dbUrl = System.getenv("JDBC_DATABASE_URL");
        return DriverManager.getConnection(dbUrl);
    }
}
