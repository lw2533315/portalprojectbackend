package com.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Address implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    int addrId;
    String address;
    String city;
    String state;
    String postCode;
    String country;

    @OneToMany(mappedBy = "address")
    List<Employee> employees = new ArrayList<>();


}
