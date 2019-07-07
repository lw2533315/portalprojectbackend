package com.controller;

import com.model.*;
import com.service.AuthService;
import com.service.FindTableService;
import com.service.PasswordService;
import com.service.UpdateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping(value = "/auth/emp",  method ={RequestMethod.GET,RequestMethod.POST}  )
public class EmployeeController {

    //reset page url in frontend
    @Value("${spring.my.empserver.resetpasswordurl}")
    private String resetUrl;

    @Autowired
    private AuthService authService;
    @Autowired
    private FindTableService findTableService;
    @Autowired
    private TokenAndUsername tokenAndUsername;
    @Autowired
    private UpdateTableService updateTableService;
    @Autowired
    private PasswordService passwordService;
    @Autowired
    private JavaMailSenderImpl mailSender;
    @Autowired
    private MessageSource messages;
    @Autowired
    private Environment env;


    /*purpose: find all timesheets for the employees
     *@param: emplpoyee's username
     * */
    @GetMapping("/timesheet/{id}")
    public List<TimeSheet> getTimeSheet(@PathVariable("id") String username, HttpServletRequest req) {
        System.out.println("employeecontroller : gettimesheet username is " + username);
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("employeeController: getTimesheet pass authorizazied");
                return findTableService.getTimesheet(username);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @GetMapping(value = "/salary/{id}")
    public Salary getSalary(@PathVariable("id") String username, HttpServletRequest req){
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("employeeController: getsalary pass authorizazied");
                    return findTableService.getSalary(username);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }


    @GetMapping(value = "/vacations/{id}")
    public List<Vacation> getVacations(@PathVariable("id") String username, HttpServletRequest req){
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("employeeController: getVacations pass authorizazied");
                return findTableService.getVacations(username);
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    /*
    * purpose: get all projects and return
    * */
    @GetMapping(value = "/project")
    public List<Project> getProjects(HttpServletRequest req) {
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("employeeController: getprojects pass authorizazied");
                return findTableService.getProjects();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    @PutMapping(value = "/timesheet")
    public String updateTimesheet(@RequestBody TimeSheet timeSheet, HttpServletRequest req) {
        System.out.println("controller:udpatetiemsheet: tiemsheet: " + timeSheet.getAction());
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("employeeController: updatetimesheet pass authorizazied");
                updateTableService.updateTimesheet(timeSheet);
                return "true";
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

    }

    /*
    * purpose: employee apply a new vacation
    * */
    @PostMapping(value="/vacation/{id}")
    public String addVacation(@RequestBody Vacation vacation, @PathVariable("id") String username,  HttpServletRequest req){
        System.out.println("controller: addvacation: ");
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("employeeController: addvaction pass authorizazied");
                updateTableService.addVacation(vacation, username);
                return "true";
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    /*ask send reset password email
    * */
    @GetMapping("/email")
    public GenericResponse sendEmail(@RequestParam("email") String email){
        System.out.println("controller:   in  email " + email  );
        Employee employee = findTableService.gettEmployeeByEmail(email);
        if(employee == null){
            return null;
        }
        String token = UUID.randomUUID().toString();
        passwordService.createPasswordToken(token, employee.getId());
        mailSender.send(passwordService.constructResetTokenEmail(resetUrl, token, employee));
        return new GenericResponse();
    }

    @GetMapping("/changepassword")
    public void showChangePasswordPage(HttpServletResponse resp,
                                               @RequestParam("id") long id, @RequestParam("token") String token)  {
        System.out.println("recieve change requirement  id is " + id + " token is " + token);
        String result = passwordService.validatePasswordResetToken(id, token);
        if (!result.equals("valid")) {
            System.out.println("controller result is " + result);
            try {
                resp.sendRedirect(env.getProperty("spring.my.angular.resetpasswordurl"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                resp.sendRedirect(env.getProperty("spring.my.angular.updatepasswordurl") + "?id="+id+"&token=" + token);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*save new password
    * any exception means save false return "";
    * */
    @PostMapping("/password")
    public String updatePassword(@RequestParam(value ="id") int id, @RequestParam(value = "token") String token, @RequestParam(value = "password") String password){
        System.out.println("controller: updatepassword: " + token + " password" + password);
        try {
            updateTableService.updatePassword(id, token, password);
            updateTableService.deleteUsedTokenFrom(id);
            return "done";
        }catch(Exception e){
            return "";
        }

    }

}
