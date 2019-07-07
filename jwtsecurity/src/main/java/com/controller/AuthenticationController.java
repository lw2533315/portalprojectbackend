package com.controller;

import com.jwt.JwtTokenUtil;
import com.model.User;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin
@RequestMapping(value ="/auth" , method = {RequestMethod.GET, RequestMethod.POST}, produces = "application/json")
public class AuthenticationController {
    @Autowired
    UserService userService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/admin/addemp")
    public boolean addEmployee(@RequestBody User user, HttpServletRequest req) throws Exception{

        System.out.println("in add function");
        String username = req.getHeader("username");
        System.out.println("username is " + username);
        User user1 = new User();
        user1.setUsername(username);
        if(userService.doesAuthorizised(req.getHeader(jwtTokenUtil.getHeader()))){
            System.out.println("controller: authenticationfunc");
            user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
            user.setRole("ROLE_" + user.getRole());
            userService.save(user);

            return true;
        }else{
            System.out.println("expiration");
            return false;
        }

    }

    //1. check token is valid or not and the role must be admin or emp 将两个权限的方法写在一起了
    //2. user httpservletRequest to add one more param, since need get token from the httpheader
    @PostMapping(value ={"admin", "emp"})
    public boolean doesAuthorizised(@RequestHeader(value="jwtHeader") String token, HttpServletRequest req)  throws  Exception{
        System.out.println("authencationcontroll: doesauthorizaied " + req.getHeader("jwtHeader"));
        return userService.doesAuthorizised(req.getHeader(jwtTokenUtil.getHeader()));


    }


    //if login user info matching with db and generate string
    //else return ""
    @PostMapping("/emplogin")
    public String empLogin(@RequestBody User user){
        System.out.println("in login");

        String token = userService.login(user);
                System.out.println(token);
        return token;
    }


}
