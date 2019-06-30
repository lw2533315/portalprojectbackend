package com;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients  //feign 需要额外的打开
public class DemofeignApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemofeignApplication.class, args);
	}



}
