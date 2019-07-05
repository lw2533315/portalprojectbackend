package com.controller;

import com.model.*;
import com.service.AddTableService;
import com.service.AuthService;
import com.service.FindTableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/auth", method = {RequestMethod.POST, RequestMethod.GET})
public  class AdminController {
    @Autowired
    AuthService authService;

    @Autowired
    AddTableService addTableService;

    @Autowired
    TokenAndUsername tokenAndUsername;

    @Autowired
    FindTableService findTableService;


    //for thread safty user ThreadLocal variable for the share local variable
    //need override the initialValue() method, it default return null
    //for multipal package to use it
//    public static ThreadLocal<TokenAndUsername> tl = new ThreadLocal<TokenAndUsername>();


    /*
    * purpose: add/update a timesheet
    * */
    @PostMapping("/admin/timesheets")
    public List<TimeSheet> findAllTimesheets(HttpServletRequest req){
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                try {
                    return findTableService.findAllTimesheets();
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }
        }catch(Exception e){
            System.out.println("find exceptioion~~~~~~~~~~");
            return null;
        }
    }


    /*
    * purpose: find all timesheet table
    * */
    @PostMapping("/admin/timesheet")
    public String addTimesheet(@RequestBody TimeSheet timeSheet, HttpServletRequest req){
        System.out.println("controller: addtimesheet  timesheet.duedate " + timeSheet.getEmployeeName());

        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                try {
                    addTableService.saveTimesheet(timeSheet);
                    return "true";
                } catch (Exception e) {
                    return "false";
                }
            } else {
                return "false";
            }
        }catch(Exception e){
            System.out.println("find exceptioion~~~~~~~~~~");
            return "error";
        }
    }


    //1. call authservice verified token if pass save user to db
    //2. and call add tableservice save employee and address to db
    @PostMapping("/admin/emp")
    public String addEmployee(@RequestBody Info info, HttpServletRequest req){
        System.out.println("in controller");
        System.out.println(info.getEmployee().getSalution());
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                try {
                    addTableService.SaveEmployeeAndAddress(info);
                    return "true";
                } catch (Exception e) {
                    return "false";
                }
            } else {
                return "false";
            }
        }catch(Exception e){
            System.out.println("find exceptioion~~~~~~~~~~");
            return "error";
        }
    }

    /*
    *purpose: find all projects
    * */
    @GetMapping("/proj")
    public List<Project> findAllProject(HttpServletRequest req){
        tokenAndUsername.setToken(req.getHeader("jwtHeader"));

        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                return findTableService.findAllProjects();
            } else {
                return new ArrayList<Project>();
            }
        }catch(Exception e){
            return null;
        }

    }


    /*
    * purpose: find all employees's info
    * */
    @GetMapping("/emp")
    public List<Employee> findAllEmployees(HttpServletRequest req){
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("adimcontroller: findall employee: valid token");
                return findTableService.findAllEmployees();
            } else {
                return new ArrayList<Employee>();
            }
        }catch(Exception e){
            return null;
        }

    }


    /*
    * add/update a employee's payment info
    * */
    @PostMapping("/admin/salary")
    public String addSalary(@RequestBody Salary salary, HttpServletRequest req){
//        System.out.println("controller: addsalary " + salary.getEmpId());

        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("controller: addsalary employee: valid token");
                addTableService.addSalary(salary);
                return "true";
            } else {
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }



    @PostMapping("/admin/salaries")
    public List<Salary> findAllSalaries(HttpServletRequest req){
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                try {
                    return findTableService.findAllSalaries();
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }
        }catch(Exception e){
            System.out.println("find exceptioion~~~~~~~~~~");
            return null;
        }
    }

    /*
    *purpose : receive pending  leave request from rabbitMq
    * */
    @GetMapping("/pendingVacation")
    public List<Vacation> findPendingVacation(HttpServletRequest req){
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("adimcontroller: findpending vacations: valid token");
                return findTableService.findPendingVacation();
            } else {
                return new ArrayList<Vacation>();
            }
        }catch(Exception e){
            return null;
        }

    }

    /*
    * purpose: receive the list of vacation from frontend, then send to service to update db
    * */
    @PostMapping("/admin/vacation")
    public String permitVacation(@RequestBody List<Vacation> vacations, HttpServletRequest req){
//        System.out.println("controller: addleave " + salary.getEmpId());

        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("controller: addleave employee: valid token" + vacations.size());
                addTableService.permitVacation(vacations);
                System.out.println("after insert data ");
                return "true";
            } else {
                return null;
            }
        }catch(Exception e){
            return null;
        }
    }

    @PostMapping("/admin/vacations")
    public List<Vacation> findAllVacation(HttpServletRequest req){
        System.out.println("controller: findallvacation");
        try {
            if (authService.doesAuthorizised(req.getHeader("jwtHeader"))) {
                System.out.println("controller: findallvaction   pass authorization");
                try {
                    return findTableService.findAllVacation();
                } catch (Exception e) {
                    return null;
                }
            } else {
                return null;
            }
        }catch(Exception e){
            System.out.println("find exceptioion~~~~~~~~~~");
            return null;
        }
    }


}
