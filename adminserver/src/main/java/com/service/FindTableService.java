package com.service;

import com.model.*;

import java.util.List;

public interface FindTableService {
    public List<Employee> findAllEmployees();
    public List<Project> findAllProjects();

    public List<TimeSheet> findAllTimesheets();

    public List<Salary> findAllSalaries();

    public List<Vacation> findAllVacation();

    public List<Vacation> findPendingVacation();
}
