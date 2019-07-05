package com.service;

import com.model.Vacation;

import java.util.List;

public interface RabbitMQService {
    public List<Vacation> recieveAllVacations();
}
