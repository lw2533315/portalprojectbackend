package com.service;

import com.model.Vacation;
import com.repository.VacationRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RabbitMQServiceImpl implements RabbitMQService {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private VacationRepository vacationRepository;

    @Override
    public List<Vacation> recieveAllVacations() {
        List<Vacation> vacations = new ArrayList<>();
        Set<Integer> checkDuplicate = new HashSet<>();
        System.out.println("service: rabbitMqserviceimpl " );
        Vacation temp = (Vacation) rabbitTemplate.receiveAndConvert("vacationQ");

        //1.check the object from the rabbitMq is duplicate because
        //instant push to queue (by apply leave in emp server) it is possible that not be clear by admin in daytime, and the
        //scheduler pushed again at the end of the day
        //2.check the db because, the waiting time of the obj in the queue, maybe the db has changed so need to check first
        while(temp != null){
            if(!checkDuplicate.contains(temp.getVacationId()) && vacationRepository.findByVacationId(temp.getVacationId()).getStatus().equalsIgnoreCase("pending")){
                vacations.add(temp);
                checkDuplicate.add(temp.getVacationId());
            }
            temp = (Vacation) rabbitTemplate.receiveAndConvert("vacationQ");
        }


//        System.out.println("service: rabbitMqservice    size " + vacations.get(0).getVacationId());
        return vacations;
    }
}
