package org.example.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MyContextListener implements ServletContextListener {

    private static Logger log = LoggerFactory.getLogger(MyContextListener.class);

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        // only once when applications is going to shut down
        log.info("context destroyed");
    }

    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        // only once when applications is starting
        // TODO DB migration
//        arg0.getServletContext().setInitParameter("log4jConfiguration", "WEB-INF/classes/log4j2.xml");
        log.info("context initialized");
    }
}

// https://stackoverflow.com/questions/3541077/design-patterns-web-based-applications/
// https://stackoverflow.com/questions/39218951/map-servlet-programmatically-instead-of-using-web-xml-or-annotations?answertab=votes#tab-top
// https://stackoverflow.com/questions/10776599/servletcontainerinitializer-vs-servletcontextlistener
