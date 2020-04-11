package org.example.controller;

import j2html.tags.ContainerTag;
import org.example.listener.MyContextListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static j2html.TagCreator.*;

@WebServlet(name ="welcome", urlPatterns = "/", loadOnStartup = 1)
public class WelcomeController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(WelcomeController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        log.info("Visited Welcome page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag welcomeHtml = html(
                head(
                        title("Welcome"),
                        link()
                                .withRel("stylesheet")
                                .withHref("https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css")
                ),
                body(
                        main(attrs("#main.content"),
                                h1("Hello IT Cluster Java 9 group!").withClass("text-success")
                        )
                )
        );
        response.getWriter().println(welcomeHtml.render());
    }
}