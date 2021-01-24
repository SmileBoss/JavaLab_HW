package ru.itis.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
@RequestMapping("/image")
public class ImagesController {
    @GetMapping
    public void getImagesPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie[] cookies = request.getCookies();

        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("fileUploaded")) {
                File file = new File("/Users/nikitashirmanov/Pictures/" + cookie.getValue());
                response.setContentType("image/jpeg");
                response.setContentLength((int) file.length());
                response.setHeader("Content-Disposition", "filename=\"" + cookie.getValue() + "\"");
                Files.copy(file.toPath(), response.getOutputStream());
                response.flushBuffer();
            }
        }
    }
}
