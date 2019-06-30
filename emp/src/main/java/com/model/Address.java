package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
public class Address {
    @Id
    int addrId;
    String address;
    String city;
    String state;
    String postCode;
    String country;

    @OneToOne
    Employee employee;


}
