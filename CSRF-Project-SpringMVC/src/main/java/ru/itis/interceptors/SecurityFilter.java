package ru.itis.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SecurityFilter implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) throws Exception {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
        System.out.println("In SecurityFilter " + request.getRequestURI() + "?" + request.getQueryString());
        if (!isProtected(request.getRequestURI())) {
            return true;
        } else {
            HttpSession session = request.getSession(false);
            if (session != null) {
                Boolean authenticated = (Boolean) session.getAttribute("authenticated");
                if (authenticated != null && authenticated) {
                    return true;
                }
            }
            ResponseUtil.sendForbidden(request, response);
            return false;
        }
    }

    private boolean isProtected(String path) {
        return !path.startsWith("/signIn") && !path.equals("/favicon.ico");
    }
}
