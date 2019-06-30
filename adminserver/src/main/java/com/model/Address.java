package com.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Address {
    @Id @GeneratedValue

    int addrId;
    String address;
    String city;
    String state;
    String postCode;
    String country;

    @OneToMany(mappedBy = "address")
    List<Employee> employees = new ArrayList<>();


}
