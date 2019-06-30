package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class TimeSheet {
    @Id
    int employeeCode;
    String employeeName;
    int projectCode;
    String projectHours;
    LocalDate endDate;
    String action;
}