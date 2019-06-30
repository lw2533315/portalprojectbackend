package com.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Employee {
    @Id @GeneratedValue
    int id;
    String salution;
    String status;
    String department;
    String firstName;
    @Column(nullable = false)
    String middleName;
    String lastName;
    String gender;
    String birth;
    String nationality;
    @Column(nullable = false)
    String email;
    @Column(nullable = false)
    String mobile;

    @OneToOne
    Address address;

    @OneToOne
    User user;

}
