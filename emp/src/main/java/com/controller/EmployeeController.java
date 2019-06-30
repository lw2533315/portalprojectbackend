package com.controller;

import com.model.User;
import com.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/emp")
public class EmployeeController {
    @Autowired
    private AuthService authService;


    boolean doesAuthencitated(User user){

    }

    @GetMapping("timesheet")
    public TimeSheet(@PathVariable("token") String token){
        return null;
    }
}
