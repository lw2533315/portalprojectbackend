package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Project implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    int projId;
    String projName;
    String projDescription;
    LocalDate dueDay;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    List<Tasksheet> tasksheets = new ArrayList<>();

}
