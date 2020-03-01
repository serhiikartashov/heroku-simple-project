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
//        arg0.getServletContext().setInitParameter("log4jConfiguration", "WEB-INF/classes/log4j2.xml");
        log.info("context initialized");
    }
}
