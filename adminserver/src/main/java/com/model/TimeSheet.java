package com.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TimeSheet {
    long id;
    int employeeCode;
    int projectCode;
    String employeeName;
    String projName;
    String task;
    String taskDescription;
    LocalDate endDate;
    String action;
}