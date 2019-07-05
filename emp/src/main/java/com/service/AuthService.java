package com.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;

@FeignClient(name="empserver",  url="localhost:9001/auth", fallback = AuthServiceImpl.class)
public interface AuthService {
    @PostMapping(value = "/emp")
    public boolean doesAuthorizised(@RequestHeader(value="jwtHeader") String token);

}
