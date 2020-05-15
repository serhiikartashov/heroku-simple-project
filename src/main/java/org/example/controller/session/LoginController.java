package org.example.controller.session;

import j2html.tags.ContainerTag;
import org.example.entity.User;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

import static j2html.TagCreator.body;
import static j2html.TagCreator.html;
import static org.example.utils.HTMLTemplates.*;
import static org.example.utils.Utils.requestInitialized;

// http://archive.apachecon.com/eu2007/materials/UnderstandingTomcatSecurity.pdf
@WebServlet(name = "login", urlPatterns = "/login", loadOnStartup = 1)
public class LoginController extends HttpServlet {

    private UserService userService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Login page!");
        requestInitialized(request, log);
        if (userService == null) {
            userService = new UserService((DataSource) request.getServletContext().getAttribute("datasource"));
        }
        List<User> users = userService.getUsers();

        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_OK);
        ContainerTag homeHtml = html(HEAD,
                body(
                        NAV(request),
                        LOGINFORM(request),
                        FOOTER
                )
        );
        response.getWriter().println(homeHtml.render());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.login(request.getParameter("username"),
                          request.getParameter("passwd"));
        request.getSession().setAttribute("principal", request.getUserPrincipal());
        requestInitialized(request, log);
        response.sendRedirect("/home");
//        request.logout();
    }
}
