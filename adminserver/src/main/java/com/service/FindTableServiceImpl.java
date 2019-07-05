package com.service;

import com.model.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FindTableServiceImpl implements FindTableService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TasksheetRepository tasksheetRepository;
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private RabbitMQService rabbitMQService;

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public List<TimeSheet> findAllTimesheets() {
        List<Tasksheet> tasksheets = tasksheetRepository.findAll();
        List<TimeSheet> timeSheets = new ArrayList<>();
        for(Tasksheet ts: tasksheets){
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
    public List<Salary> findAllSalaries() {
        System.out.println("salary size " + salaryRepository.findAll().size());
        return salaryRepository.findAll();
    }

    @Override
    public List<Vacation> findAllVacation() {
        return vacationRepository.findAll();
    }

    @Override
    public List<Vacation> findPendingVacation() {
        return rabbitMQService.recieveAllVacations();
    }
}
