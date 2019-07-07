package com.service;

import com.model.Employee;
import org.springframework.mail.SimpleMailMessage;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public interface PasswordService {
    //save passwordresettoken table to db
    public void createPasswordToken(String token, int empId);
    public SimpleMailMessage constructResetTokenEmail(String path,  String token , Employee employee);
    public  String getAppUrl(HttpServletRequest request);
    public String validatePasswordResetToken(long id, String token);
}
