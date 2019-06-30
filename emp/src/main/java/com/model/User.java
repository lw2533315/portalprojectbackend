package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;


@Data
@Entity
public class User {
    @Id
    String username;
    String password;
    String role;

    @OneToOne
    Employee employee;

}
