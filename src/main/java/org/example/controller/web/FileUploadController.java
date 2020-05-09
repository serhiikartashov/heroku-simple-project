package org.example.controller.web;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "uploadFiles", urlPatterns = "/uploadFiles")
@MultipartConfig(
        fileSizeThreshold   = 1024 * 1024,      // 1 MB
        maxFileSize         = 1024 * 1024 * 10, // 10 MB
        maxRequestSize      = 1024 * 1024 * 15, // 15 MB
        location            = "D:/Uploads"
)
public class FileUploadController extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        // handles file upload into DB
    }
}
