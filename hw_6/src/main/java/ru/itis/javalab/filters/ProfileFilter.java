package ru.itis.javalab.filters;

import org.springframework.context.ApplicationContext;
import ru.itis.javalab.models.User;
import ru.itis.javalab.repositories.UserRepository;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebFilter("/Profile")
public class ProfileFilter implements Filter {
    UserRepository userRepository;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ServletContext servletContext = filterConfig.getServletContext();
        ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("context");
        userRepository = applicationContext.getBean(UserRepository.class);
    }


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Optional<User> user = Optional.empty();
        Cookie[] cookies = request.getCookies();
        String auth = null;
        boolean cookieChecker = false;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("auth".equals(cookie.getName())) {
                    auth = cookie.getValue();
                    user = userRepository.findByAuth(auth);
                    break;
                }
            }
        }
        if (auth != null){
            cookieChecker = true;
            if (user.isPresent()){
                session.setAttribute("id", user.get().getId());
                session.setAttribute("email", user.get().getEmail());
                session.setAttribute("nickname", user.get().getNickname());
            }
        }
        if (((session == null || session.getAttribute("email") == null) && (!cookieChecker))) {
            response.sendRedirect("/itis/Login");
        } else {
        filterChain.doFilter(request, response);
        }
    }
}
