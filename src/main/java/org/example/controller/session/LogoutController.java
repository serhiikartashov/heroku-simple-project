package org.example.controller.session;

import org.apache.catalina.realm.GenericPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.example.utils.Utils.requestInitialized;

@WebServlet(name = "logout", urlPatterns = "/logout", loadOnStartup = 1)
public class LogoutController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(LogoutController.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        requestInitialized(request, log);
        GenericPrincipal principal = (GenericPrincipal) request.getSession().getAttribute("principal");
        log.debug("logout: {}", principal);
        if (principal!=null) {
            request.logout();
            request.getSession().removeAttribute("principal");
        }
        response.sendRedirect("/home");
    }
}
