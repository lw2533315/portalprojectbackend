package com.service;

import com.model.Vacation;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(Vacation vacation) {
        System.out.println("service:rabbitservice  send  " + vacation.getVacationId());
        rabbitTemplate.convertAndSend("vacationDirectExg", "abcde", vacation);
    }
}
