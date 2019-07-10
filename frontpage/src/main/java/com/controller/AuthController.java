package com.controller;

import com.model.User;
import com.service.AuthService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@CrossOrigin
@EnableFeignClients
@RequestMapping(value = "/auth", method = {RequestMethod.POST, RequestMethod.GET})
public class AuthController {
    @Autowired
    private AuthService authService;


    @PostMapping("/emplogin")

    public String empLogin(@RequestBody User user, HttpServletRequest req){
        System.out.println(req.getHeader("Content-Type"));
        return authService.empLogin(user);
    }







}
