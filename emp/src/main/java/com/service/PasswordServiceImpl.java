package com.service;

import com.model.Employee;
import com.model.PasswordResetToken;
import com.repository.PasswordResetTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PasswordServiceImpl implements PasswordService {
    @Value("${spring.my.company.email}")
    private String companyEmail;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    //work for I18
    @Autowired
    private MessageSource messgaes;

    //get properties configuratio from properites file
    @Autowired
    private Environment env;

    @Override
    public void createPasswordToken(String token, int empId) {
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, System.currentTimeMillis(), empId);
        passwordResetTokenRepository.save(passwordResetToken);
    }

    /*create mail content
    *  subject, body, target email,  email from
    * */
    @Override
    public SimpleMailMessage constructResetTokenEmail(String path,  String token, Employee employee) {

        String url = path+"/changepassword?id=" + employee.getId() + "&token=" + token;
        System.out.println("passwordServiceimpl : constructresettoeknemail  path" + url);
//        String message = messgaes.getMessage("message.resetPassword", null, locale);
        System.out.println("passwordServiceimpl : constructresettoeknemail  test ");
        SimpleMailMessage email = new SimpleMailMessage();
        email.setSubject("reset password ");
//        email.setText(message + "\r\n" + url)  ;
        email.setText(url);
        //send email to
        email.setTo(employee.getEmail());
        //send email from
        email.setFrom(env.getProperty("spring.my.company.email"));
        return email;

    }

    //to find the current server's name, port number, and contextPath()
    //to combine a url
    @Override
    public String getAppUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    @Override
    public String validatePasswordResetToken(long id, String token) {
        System.out.println("passwordserviceImp  test0");
        PasswordResetToken passToken =
                passwordResetTokenRepository.findByToken(token);
        System.out.println("passwordserviceImp  test1");
        if ((passToken == null) || (passToken.getEmpId() != id) || !token.equals(passToken.getToken())) {
            return "invalidToken";
        }
        //check the expiration
        System.out.println(System.currentTimeMillis());
        System.out.println(passToken.getCurTimestamp());
        System.out.println(Long.parseLong(env.getProperty("spring.my.expiration")));
        if(System.currentTimeMillis() - passToken.getCurTimestamp() > Long.parseLong(env.getProperty("spring.my.expiration"))){
            System.out.println("passwordserviceImp expiration");
            return "expired";
        }
        return "valid";
    }
}
