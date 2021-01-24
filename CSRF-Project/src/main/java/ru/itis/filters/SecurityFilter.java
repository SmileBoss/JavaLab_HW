package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ru.itis.filters.ResponseUtil.sendForbidden;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse) response;
        System.out.println("In SecurityFilter " + req.getRequestURI() + "?" + req.getQueryString());
        if (!isProtected(req.getRequestURI())) {
            chain.doFilter(request, response);
        } else {
            HttpSession session = req.getSession(false);
            if (session != null) {
                Boolean authenticated = (Boolean) session.getAttribute("authenticated");
                if (authenticated != null && authenticated) {
                    chain.doFilter(request, response);
                    return;
                }
            }
            sendForbidden(req, resp);
        }
    }

    private boolean isProtected(String path) {
        return !path.startsWith("/signIn") && !path.equals("/favicon.ico");
    }
}
