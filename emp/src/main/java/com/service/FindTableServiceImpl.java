package com.service;


import com.model.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
@Service
@Transactional(rollbackOn = Exception.class)
public class FindTableServiceImpl implements FindTableService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private TasksheetRepository tasksheetRepository;
    @Autowired
    private VacationRepository vacationRepository;

    /*purpuse: find the list<Timesheet>
    * 1. the timesheet get from db, no employeename and projectname need to fill them
    * */
    @Override
    public List<TimeSheet> getTimesheet(String username) {
        Employee employee = getEmployeeByUsername(username);
        if(employee.getTasksheets().size() == 0){
            return new ArrayList<TimeSheet>();
        }
        List<TimeSheet> timeSheets = new ArrayList<>();
        System.out.println(employee.getTasksheets().size());
        for(Tasksheet ts: employee.getTasksheets()){
            TimeSheet timeSheet = new TimeSheet();
            timeSheet.setId(ts.getId());
            timeSheet.setAction(ts.getAction());
            timeSheet.setEndDate(ts.getEndDate());
            timeSheet.setTask(ts.getTask());
            timeSheet.setTaskDescription(ts.getTaskDescription());
            timeSheet.setEmployeeCode(ts.getEmployee().getId());
            timeSheet.setEmployeeName(ts.getEmployee().getFirstName() + " " + ts.getEmployee().getLastName());
            timeSheet.setProjName(ts.getProject().getProjName());
            timeSheet.setProjectCode(ts.getProject().getProjId());
            timeSheets.add(timeSheet);
        }
        return timeSheets;

    }

    @Override
    public Employee getEmployeeByUsername(String username) {

        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Project> getProjects() {
        return projectRepository.findAll();

    }


    /*
    * purpose: find the salary for the employee
    * 1. if find return
    * 2. not set for the guy return a empty obj
    *
    * */
    @Override
    public Salary getSalary(String username) {
        Employee employee = getEmployeeByUsername(username);

        //if not set salary for the employee
        if(salaryRepository.findByEmpId(employee.getId())==null){
            System.out.println("is null");
            return new Salary();
        }

        return salaryRepository.findByEmpId(employee.getId());
    }

    @Override
    public List<Vacation> getVacations(String username) {
        Employee employee = getEmployeeByUsername(username);
        List<Vacation> vacations = vacationRepository.findByEmpId(employee.getId());
        return vacations;
    }

    @Override
    public Employee  gettEmployeeByEmail (String email) {
       return employeeRepository.findByEmail(email);

    }
}
