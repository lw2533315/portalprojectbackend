package com.service;


import com.model.Vacation;
import com.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

public class SchedulerServiceImpl implements SchedulerService {
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private RabbitMQService rabbitMQService;


    //everyday the last second check all the leave requirement if it is pending sent to rabbitMq
    @Override
    @Scheduled(cron="59 59 23 * * Mon-Fri")
    public void checkLeaveStatus() {
        List<Vacation> vacations = vacationRepository.findByStatus("pending");
        for(Vacation v: vacations){
            rabbitMQService.send(v);
        }


    }
}
