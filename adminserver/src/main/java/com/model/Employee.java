package com.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {
    @Id
    @GeneratedValue
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
    String email;
    String mobile;

    @JsonIgnore    //往前段扔可能需要转json 那么没有这个就会死循环
    @ManyToOne
    Address address;

    @JsonIgnore
    @ManyToOne
    Project project;


}
