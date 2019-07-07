package com.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


/*create a table to save the token and empid for future validate the return request from
*user with correct info
*
* */
@Entity
@Data
public class PasswordResetToken {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String token;
    private long curTimestamp;
    //employee id
    @Column(nullable = false)
    private int empId;
    public PasswordResetToken(){}

    public PasswordResetToken(String token, long timestamp, int empId ){
        this.token = token;
        this.curTimestamp = timestamp;
        this.empId = empId;
    }
}
