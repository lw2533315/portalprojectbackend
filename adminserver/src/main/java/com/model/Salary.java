package com.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class Salary implements Serializable {
    @Id

    int empId;
    String empName;
    int payDate;
    double payAmount;




}
