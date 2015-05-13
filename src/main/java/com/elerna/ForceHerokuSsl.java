package com.elerna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class ForceHerokuSsl extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle was called");

        String proto = request.getHeader("x-forwarded-proto");

        System.out.println("proto:" + proto);

        return proto == null || "https".equalsIgnoreCase(proto);
    }
}