package com.elerna;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Zoltan on 13/05/15.
 */
@Configuration
@Production
public class MyWebMvcConfig extends WebMvcConfigurerAdapter {

    public void addInterceptors(InterceptorRegistry registry) {

        System.out.println("addInterceptors was called");
        registry.addInterceptor(new ForceHerokuSsl());
    }

}
