package com.elerna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ForceHerokuSsl extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String proto = request.getHeader("x-forwarded-proto");

        boolean result = true;

        if ("http".equalsIgnoreCase(proto)) {

            System.out.println("URL:" + request.getRequestURL());
            System.out.println("URI:" + request.getRequestURI());
            System.out.println("PathInfo:" + request.getPathInfo());
            System.out.println("Path translated:" + request.getPathTranslated());
            System.out.println("Context path:" + request.getContextPath());
            System.out.println("Servlet path:" + request.getServletPath());
            System.out.println("Query string:" + request.getQueryString());

            String location = "https://" + request.getRequestURL().substring("http://".length());
            System.out.println("Location:" + location);
            response.sendRedirect(location);

            result = false;
        }

        return result;
    }
}