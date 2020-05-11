package org.example.controller.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// https://www.codejava.net/coding/upload-files-to-database-servlet-jsp-mysql
@WebServlet(name = "upload", urlPatterns = "/upload")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024,      // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15 // 15 MB
)
public class FileUploadController extends HttpServlet {

    private static Logger log = LoggerFactory.getLogger(FileUploadController.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        // handles file upload into DB
    }
}
