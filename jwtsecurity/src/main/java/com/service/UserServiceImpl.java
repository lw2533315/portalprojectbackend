package com.service;

import com.jwt.JwtTokenUtil;
import com.model.User;
import com.repository.MyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    MyRepository myRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUserDetailsServiceImpl jwtUserDetailsService;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    public User findByUsername(String username) {
        return myRepository.findByUsername(username);
    }

    @Override
    public void save(User user) {

        System.out.println("service: save ()");
        myRepository.save(user);
    }

    @Override
    //pass authentication return token otherwise return ""
    public String login(User user) throws AuthenticationException {
        try {
            System.out.println("~~~~~~~~~1");
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
            System.out.println("~~~~~~~~~2");
            Authentication auth = authenticationManager.authenticate(upToken);
            System.out.println("~~~~~~~~~3");
            SecurityContextHolder.getContext().setAuthentication(auth);
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(user.getUsername());
            return jwtTokenUtil.generateToken(userDetails);
        }catch (Exception e){
            return "";
        }
    }

    //check the token is valid or not after configur match test
    @Override
    public boolean doesAuthorizised(String token){
        System.out.println("servie: userServiceimp: doesauthorizied");
//        return !jwtTokenUtil.isTokenExpired(token);
          if(jwtTokenUtil.isTokenExpired(token)){
              System.out.println("false~~~~~~~~~");
              return false;
          }else{
              System.out.println("true~~~~~~~~~~~");
              return true;
          }
    }

}
