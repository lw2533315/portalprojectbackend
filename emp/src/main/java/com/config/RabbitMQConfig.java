package com.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collection;
import java.util.Map;

@Configuration
public class RabbitMQConfig {
    /*param1: queue的名字   param2: 永久化   param3：是否排他性， param：是否自动终端
    * */
    @Bean
    public Queue vacationQ(){
        return new Queue("vacationQ", true, false, false );
    }

    @Bean
    public DirectExchange vacationDirectExg(){
        return new DirectExchange("vacationDirectExg", true, false);
    }

    @Bean
    public Binding bind(){
        return BindingBuilder.bind(vacationQ()).to(vacationDirectExg()).with("abcde");
    }
}
