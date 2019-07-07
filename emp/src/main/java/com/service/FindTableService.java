package com.service;

import com.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FindTableService {
    public List<TimeSheet> getTimesheet(String userName);
    public Employee getEmployeeByUsername(String username);

     public List<Project> getProjects();
     public Salary getSalary(String username);

    public List<Vacation> getVacations(String username);

    public Employee  gettEmployeeByEmail (String email);
}
