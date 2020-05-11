package org.example.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// https://www.codejava.net/java-ee/servlet/java-servlet-to-download-file-from-database
@WebServlet(name = "download", urlPatterns = "/download")
public class FileDownloadController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(FileDownloadController.class);

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        // handles file download from DB
    }
}
