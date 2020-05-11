package org.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@WebListener
public class ServletRequestLogger implements ServletRequestListener {

    private static Logger log = LoggerFactory.getLogger(ServletRequestLogger.class);

    @Override
    public void requestInitialized(ServletRequestEvent event) {
        HttpServletRequest request = (HttpServletRequest) event.getServletRequest();
        log.debug("Request Path: {}", request.getRequestURI());
        log.debug("Auth header: {}", request.getHeader("Authorization"));
        // isUserInRole determines whether a remote user is in a specific security role. If no user has been authenticated,
        // this method returns false. This method expects a String user role-name parameter.
        boolean isAdmin = request.isUserInRole("admin");
        log.debug("User has role admin: {}", isAdmin);
        // getUserPrincipal determines the principal name of the current user and returns a java.security.Principal object.
        // If no user has been authenticated, this method returns null. Calling the getName method on the Principal
        // returned by getUserPrincipal returns the name of the remote user.
        Principal userPrincipal = request.getUserPrincipal();
        log.debug("Principal: {}", userPrincipal);
        // a boolean indicating whether this request was made using a secure channel, such as HTTPS.
        boolean isSecure = request.isSecure();
        log.debug("Is Secure (HTTPS): {}", isSecure);
        // getRemoteUser determines the user name with which the client authenticated. The getRemoteUser method returns
        // the name of the remote user (the caller) associated by the container with the request.
        // If no user has been authenticated, this method returns null.
        String remoteUser = request.getRemoteUser();
        log.debug("User has role admin: {}", remoteUser);
        String authType = request.getAuthType();
        log.debug("User has role admin: {}", authType);
    }

    @Override
    public void requestDestroyed(ServletRequestEvent event) {
        log.info("request destroyed");
    }

}
