package com.config;

import com.model.TokenAndUsername;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class FeignInterceptor implements RequestInterceptor {
    @Autowired
    TokenAndUsername tokenAndUsername;
    @Override
    public void apply(RequestTemplate requestTemplate) {
        System.out.println("test~~~~~~~~~~~~~~~");
        requestTemplate.header("jwtHeader", tokenAndUsername.getToken());
        requestTemplate.header("username", tokenAndUsername.getUsername());
    }
}
