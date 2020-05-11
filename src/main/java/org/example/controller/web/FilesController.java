package org.example.controller.web;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static j2html.TagCreator.body;
import static j2html.TagCreator.html;
import static org.example.utils.HTMLTemplates.*;

@WebServlet(name = "files", urlPatterns = "/files")
public class FilesController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(FilesController.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(HEAD,
                body(
                        NAV(request),
                        FOOTER
                )
        );
        response.getWriter().println(homeHtml.render());
    }
}
