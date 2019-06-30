package com.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyAop {
    private final String POINTCUT1 = "execution(* com.controller.AdminController.addEmployee(..))";
    @Pointcut(POINTCUT1)
    public void pointcut1(){}

    @AfterReturning(value = POINTCUT1, returning = "ret")
    public void persistUserTable(JoinPoint joinPoint, Object ret){

    }
}
