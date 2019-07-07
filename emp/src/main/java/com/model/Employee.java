package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String salution;
    String status;
    String department;
    String firstName;
    String middleName;
    String lastName;
    String gender;
    String birth;
    String nationality;
    @Column(nullable = false)
    String email;
    String mobile;
    String username;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    List<Tasksheet> tasksheets = new ArrayList<>();



    @JsonIgnore    //往前段扔可能需要转json 那么没有这个就会死循环
    @ManyToOne
    Address address;

//    @JsonIgnore
//    @ManyToOne
//    Project project;

//    @JsonIgnore
//    @OneToOne(mappedBy = "employee")
//    Salary salary;




}
