package com.service;

import com.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "adminserver", url="localhost:9001/auth")
public interface AuthService {

    @PostMapping(value = "/admin/addemp")
//    @Headers({"Content-Type: application/json"})
    public boolean addEmployee(@RequestBody User user);

    @PostMapping(value = "/admin")
    public boolean doesAuthorizised(@RequestBody User user);

}
