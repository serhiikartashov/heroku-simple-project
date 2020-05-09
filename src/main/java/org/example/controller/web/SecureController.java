package org.example.controller.web;

import j2html.tags.ContainerTag;
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

@WebServlet(name = "secure", urlPatterns = "/secure", loadOnStartup = 1)
@ServletSecurity(@HttpConstraint(rolesAllowed = "admin"))
public class SecureController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(SecureController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Home page!");
        /*
        getRemoteUser determines the user name with which the client authenticated. The getRemoteUser method returns the name of the remote user (the caller) associated by the container with the request. If no user has been authenticated, this method returns null.
        isUserInRole determines whether a remote user is in a specific security role. If no user has been authenticated, this method returns false. This method expects a String user role-name parameter.
        The security-role-ref element should be declared in the deployment descriptor with a role-name subelement containing the role name to be passed to the method. Using security role references is discussed in Declaring and Linking Role References.
        getUserPrincipal determines the principal name of the current user and returns a java.security.Principal object. If no user has been authenticated, this method returns null. Calling the getName method on the Principal returned by getUserPrincipal returns the name of the remote user.
         */
        request.isUserInRole("admin");
        request.getUserPrincipal();
        request.getRemoteUser();
        request.getAuthType();
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
