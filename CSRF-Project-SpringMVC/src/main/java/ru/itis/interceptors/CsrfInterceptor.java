package ru.itis.interceptors;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static ru.itis.interceptors.ResponseUtil.sendForbidden;

public class CsrfInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getMethod().equals("POST")) {
            String requestCsrf = request.getParameter("_csrf_token");
            String sessionCsrf = (String) request.getSession(false).getAttribute("_csrf_token");
            if (sessionCsrf.equals(requestCsrf)) {
                return true;
            } else {
                sendForbidden(request, response);
                return false;
            }
        }
        if (request.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            request.setAttribute("_csrf_token", csrf);
            request.getSession().setAttribute("_csrf_token", csrf);
        }
        return true;
    }
}
