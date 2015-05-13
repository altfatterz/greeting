package com.elerna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
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

            StringBuilder location = new StringBuilder();
            location.append("https://");
            location.append(request.getRequestURL().substring("http://".length()));
            if (!StringUtils.isEmpty(request.getQueryString())) {
                location.append("?").append(request.getQueryString());
            }

            System.out.println("Location:" + location);
            response.sendRedirect(location.toString());

            result = false;
        }

        return result;
    }
}