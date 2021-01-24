package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

import static ru.itis.filters.ResponseUtil.sendForbidden;

public class CsrfFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getMethod().equals("POST")) {
            String requestCsrf = req.getParameter("_csrf_token");
            String sessionCsrf = (String) req.getSession(false).getAttribute("_csrf_token");
            if (sessionCsrf.equals(requestCsrf)) {
                chain.doFilter(request, response);
                return;
            } else {
                sendForbidden(req, resp);
                return;
            }
        }
        if (req.getMethod().equals("GET")) {
            String csrf = UUID.randomUUID().toString();
            req.setAttribute("_csrf_token", csrf);
            req.getSession().setAttribute("_csrf_token", csrf);
        }
        chain.doFilter(request, response);
    }
}
