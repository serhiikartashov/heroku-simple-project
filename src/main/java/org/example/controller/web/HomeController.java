package org.example.controller.web;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static j2html.TagCreator.*;
import static org.example.utils.HTMLTemplates.*;

@WebServlet(name = "home", urlPatterns = "/home", loadOnStartup = 1)
public class HomeController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(HomeController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Home page!");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(HEAD,
                body(
                        NAV,
                        FOOTER
                )
        );
        response.getWriter().println(homeHtml.render());
    }
}
