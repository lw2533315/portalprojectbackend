package com.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.sound.midi.Soundbank;
import java.util.Enumeration;

@Configuration
public class HeaderInterceptor {

//    @Bean
//    public RequestInterceptor requestInterceptor() {
//        return requestTemplate -> {
//            ServletRequestAttributes attrs = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//            if (attrs != null) {
//                System.out.println("not null");
//                HttpServletRequest request = attrs.getRequest();
//                Enumeration<String> headerNames = request.getHeaderNames();
//                if (headerNames != null) {
//                    while (headerNames.hasMoreElements()) {
//                        String name = headerNames.nextElement();
//                        String value = request.getHeader(name);
//                        /**
//                         * 遍历请求头里面的属性字段，将logId和token添加到新的请求头中转发到下游服务
//                         * */
//                        if ("jwtHeader".equalsIgnoreCase(name) || "Content-Type".equalsIgnoreCase(name)) {
//                            System.out.println("添加自定义请求头key:" + name + ",value:" + value);
//                            requestTemplate.header(name, value);
//                        } else {
//                            System.out.println("FeignHeadConfiguration 非自定义请求头key:" + name +
//                                    ",value:" + value + "不需要添加!");
//
//                        }
//                    }
//                } else {
//                    System.out.println("FeignHeadConfiguration 获取请求头失败！");
//                }
//            }
//            System.out.println("is null");
//        };
//    }
}
