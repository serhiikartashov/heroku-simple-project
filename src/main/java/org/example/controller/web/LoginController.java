package org.example.controller.web;

import j2html.tags.ContainerTag;
import org.example.entity.User;
import org.example.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static j2html.TagCreator.body;
import static j2html.TagCreator.html;
import static org.example.utils.HTMLTemplates.*;

@WebServlet(name = "login", urlPatterns = "/login", loadOnStartup = 1)
public class LoginController extends HttpServlet {

    private UserService userService;

    private static Logger log = LoggerFactory.getLogger(LoginController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        log.info("Visited Home page!");
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

//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//
//        String email = request.getParameter("username");
//        String pass = request.getParameter("passwd");
//
//        if (userService.checkUser(email, pass)) {
//            RequestDispatcher rs = request.getRequestDispatcher("home");
//            rs.forward(request, response);
//        } else {
//            log.debug("Username or Password incorrect");
//            RequestDispatcher rs = request.getRequestDispatcher("login");
//            rs.include(request, response);
//        }
//    }
}
