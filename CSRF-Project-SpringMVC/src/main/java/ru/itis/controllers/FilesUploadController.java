package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Controller
@RequestMapping("/files")
public class FilesUploadController {
    @GetMapping
    public String getFilesUpload(){
        return "fileUpload";
    }
    @PostMapping
    public String postFilesUpload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Part part = request.getPart("file");
        System.out.print(part.getSubmittedFileName() + " ");
        System.out.print(part.getContentType() + " ");
        System.out.println(part.getSize());
        Files.copy(part.getInputStream(), Paths.get("/Users/nikitashirmanov/Documents/Projects/" + part.getSubmittedFileName()));
        Cookie cookie = new Cookie("fileUploaded", part.getSubmittedFileName());
        response.addCookie(cookie);
        return "redirect:/files";

    }
}
