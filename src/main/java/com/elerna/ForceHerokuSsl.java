package com.elerna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ForceHerokuSsl extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String proto = request.getHeader("x-forwarded-proto");

        boolean result = false;

        if ("https".equalsIgnoreCase(proto)) {
            String location = "https://" + request.getRequestURI().substring("http://".length());
            System.out.println("Location:" + location);
            response.sendRedirect(location);

            result = true;
        }

        return result;
    }
}