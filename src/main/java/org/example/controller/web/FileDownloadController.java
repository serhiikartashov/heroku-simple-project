package org.example.controller.web;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "downloadFile", urlPatterns = "/downloadFile")
public class FileDownloadController extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        // handles file download from DB
    }
}
