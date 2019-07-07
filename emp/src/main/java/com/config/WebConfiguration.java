package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class WebConfiguration extends WebMvcConfigurationSupport {
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/auth/emp/**")
//                .allowedOrigins("http:localhost:4200")
//                .allowedMethods("GET", "POST", "PUT")
//                .allowCredentials(false).maxAge(3600);
//    }
}
