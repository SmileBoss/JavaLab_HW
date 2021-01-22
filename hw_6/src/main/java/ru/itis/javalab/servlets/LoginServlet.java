package ru.itis.javalab.servlets;

import org.springframework.context.ApplicationContext;
import ru.itis.javalab.dto.UserDto;
import ru.itis.javalab.dto.UserForm;
import ru.itis.javalab.models.CookieUser;
import ru.itis.javalab.services.CookieService;
import ru.itis.javalab.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    UserService userService;
    CookieService cookieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("context");
        userService = applicationContext.getBean(UserService.class);
        cookieService = applicationContext.getBean(CookieService.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserForm userForm = UserForm.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("pass"))
                .build();
        UserDto userDto = userService.singIn(userForm);
        HttpSession httpSession = null;

        if (userDto != null) {
            if (req.getParameter("rememberMe") != null) {
                String auth;
                Optional<CookieUser> cookieUser = cookieService.getByUserId(userDto.getId());
                if (cookieUser.isPresent()) {
                    auth = cookieUser.get().getAuth();
                } else {
                    do {
                        auth = UUID.randomUUID().toString();
                    } while (cookieService.getByAuth(auth).isPresent());
                    CookieUser cUser = CookieUser.builder().user_id(userDto.getId()).auth(auth).build();
                    cookieService.addCookie(cUser);
                }
                Cookie cookie = new Cookie("auth", auth);
                cookie.setMaxAge(60 * 60 * 24 * 365);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
            httpSession = req.getSession();
            httpSession.setAttribute("id", userDto.getId());
            httpSession.setAttribute("email", userDto.getEmail());
            httpSession.setAttribute("nickname", userDto.getNickname());
            resp.sendRedirect("/itis/HomePage");
        } else {
            resp.sendRedirect("/itis/Login");
        }
    }
}
