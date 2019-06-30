package com.controller;

import com.model.Employee;
import com.model.Info;
import com.model.TokenAndUsername;
import com.model.User;
import com.service.AddTableService;
import com.service.AuthService;
import com.service.FindTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping(value = "/auth", method = {RequestMethod.POST, RequestMethod.GET})
public class AdminController {
    @Autowired
    AuthService authService;

    @Autowired
    AddTableService addTableService;

    @Autowired
    TokenAndUsername tokenAndUsername;

    @Autowired
    FindTableRepository findTableRepository;


    //1. call authservice verified token if pass save user to db
    //2. and call addtableservice save employee and address to db
    @PostMapping("/admin/emp")
    public String addEmployee(@RequestBody Info info, HttpServletRequest req){
        System.out.println("in controller");
        System.out.println(req.getHeader("Content-Type"));
        System.out.println(req.getHeader("jwtHeader"));
        tokenAndUsername.setToken(req.getHeader("jwtHeader"));
        tokenAndUsername.setUsername(req.getHeader("username"));
        System.out.println(info.getEmployee().getSalution());
        try {
            if (authService.addEmployee(info.getUser())) {
                try {
                    addTableService.SaveEmployeeAndAddress(info);
//                    Project project = new Project();
//                    LocalDate ld = LocalDate.now().plusMonths(6);
//                    project.setDueDay(ld);
//                    project.setProjDescription("build a new website for neweggs");
//                    project.setProjName("future new egg");
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

    @GetMapping("emp")
    public List<Employee> findAllEmployees(HttpServletRequest req){
        tokenAndUsername.setToken(req.getHeader("jwtHeader"));
        tokenAndUsername.setUsername(req.getHeader("username"));
        User user = new User();
        user.setUsername(tokenAndUsername.getUsername());
        try {
            if (authService.doesAuthorizised(user)) {
                System.out.println("adimcontroller: findall employee: valid token");
                return findTableRepository.findAllEmployees();
            } else {
                return new ArrayList<Employee>();
            }
        }catch(Exception e){
            return null;
        }

    }
}
