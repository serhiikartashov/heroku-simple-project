package org.example.controller.web;

import j2html.tags.ContainerTag;
import org.apache.catalina.realm.GenericPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static j2html.TagCreator.body;
import static j2html.TagCreator.html;
import static org.example.utils.HTMLTemplates.*;

// https://github.com/andreacomo/tomcat-jwt-security
@WebServlet(name = "secure", urlPatterns = "/secure", loadOnStartup = 1)
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class SecureController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(SecureController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Secure page!");
        request.getSession().setAttribute("principal", (GenericPrincipal)request.getUserPrincipal());
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
