package com.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

@Service
public class AuthServiceImpl implements AuthService {
    @Override
    public boolean doesAuthorizised(@RequestHeader(value="jwtHeader") String token) {
        System.out.println("cannot find the method in authentication");
        return false;
    }
}
