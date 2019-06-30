package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project {
    @Id@GeneratedValue
    int projId;
    String projName;
    String projDescription;
    LocalDate dueDay;

    @OneToMany(mappedBy = "project")
    List<Employee> employees = new ArrayList<>();
}
