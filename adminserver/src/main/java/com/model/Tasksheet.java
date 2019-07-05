package com.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
public class Tasksheet implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    Project project;

    @ManyToOne
    Employee employee;

    String task;
    String taskDescription;
    LocalDate endDate;
    String action;
}
