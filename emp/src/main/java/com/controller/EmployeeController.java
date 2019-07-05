package com.controller;

import com.model.*;
import com.service.AuthService;
import com.service.FindTableService;
import com.service.UpdateTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(value = "http://localhost:4200")
@RequestMapping(value = "/auth/emp",  method ={RequestMethod.GET,RequestMethod.POST}  )
public class EmployeeController {
    @Autowired
    private AuthService authService;
    @Autowired
    private FindTableService findTableService;
    @Autowired
    private TokenAndUsername tokenAndUsername;
    @Autowired
    private UpdateTableService updateTableService;


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
}
