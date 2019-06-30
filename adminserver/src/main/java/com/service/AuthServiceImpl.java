package com.service;

import com.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public boolean addEmployee(User user) {
        System.out.println( "not find the method");
        return false;
    }

    @Override
    public boolean doesAuthorizised(User user) {
        System.out.println( "not find the method");
        return false;
    }
}