package com.service;

import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(url="localhost:9001/auth")
public class AuthService {
}
