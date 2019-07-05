package com.service;

import com.model.Vacation;

public interface RabbitMQService {
    public void send(Vacation vacation);
}
