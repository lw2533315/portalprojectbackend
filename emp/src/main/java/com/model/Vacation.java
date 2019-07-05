package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Vacation implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int vacationId;

    int empId;
    String empName;
    String reason;
    String description;
    String fromDate;
    String endDate;
    String status;

}
