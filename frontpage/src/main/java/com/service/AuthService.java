package com.service;

import com.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;


//当服务器authentication 挂掉了，就只能通过fall back 后面的class来实现这个借口
//@FeignClient(value =  "AUTHENTICATION",  fallback = AuthServiceImpl.class )
//@FeignClient(name = "xyz", url="localhost:9001/auth")
@FeignClient(value = "authentication-server")
public interface AuthService {


//    @Headers({"Content-Type: application/json","Accept: application/json"})
    @PostMapping(value ="/auth/emplogin")
    public String empLogin(@RequestBody User user);



}

