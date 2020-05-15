package org.example.controller.web;

import j2html.tags.ContainerTag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static j2html.TagCreator.body;
import static j2html.TagCreator.html;
import static org.example.utils.HTMLTemplates.*;
import static org.example.utils.Utils.hasRoles;
import static org.example.utils.Utils.requestInitialized;

// https://github.com/andreacomo/tomcat-jwt-security
@WebServlet(name = "admin", urlPatterns = "/admin", loadOnStartup = 1)
public class AdminController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(AdminController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Secure page!");
        requestInitialized(request, log);
        if (!hasRoles(request, List.of("admin"))) {
            response.sendRedirect(request.getHeader("referer"));
        } else {
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
}
