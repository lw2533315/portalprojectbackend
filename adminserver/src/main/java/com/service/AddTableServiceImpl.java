package com.service;

import com.model.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional(rollbackOn = Exception.class)
public class AddTableServiceImpl implements AddTableService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProjectRepository projectRepository;
    @Autowired
    TasksheetRepository tasksheetRepository;

    @Autowired
    SalaryRepository salaryRepository;

    @Autowired
    VacationRepository vacationRepository;



    @Override
    public void SaveEmployeeAndAddress(Info info) {
        Employee employee = new Employee();
        employee = info.getEmployee();
        employee.setUsername(info.getUser().getUsername());
        Address address = new Address();
        address = info.getAddress();
        employee.setAddress(address);
        address.getEmployees().add(employee);
        employeeRepository.save(employee);
        addressRepository.save(address);

    }

    @Override
    public void saveProjct(Project project) {
        projectRepository.save(project);
    }

    @Override
    public void saveTimesheet(TimeSheet timesheet) {
        Tasksheet tasksheet = new Tasksheet();
        tasksheet.setAction(timesheet.getAction());
        tasksheet.setEndDate(timesheet.getEndDate());
        tasksheet.setTask(timesheet.getTask());
        tasksheet.setTaskDescription(timesheet.getTaskDescription());
        Employee employee = employeeRepository.findById(timesheet.getEmployeeCode());
        Project project = projectRepository.findByProjId(timesheet.getProjectCode());
        tasksheet.setEmployee(employee);
        tasksheet.setProject(project);
        employee.getTasksheets().add(tasksheet);
        project.getTasksheets().add(tasksheet);
        employeeRepository.save(employee);
        projectRepository.save(project);
        tasksheetRepository.save(tasksheet);


    }

    @Override
    public void addSalary(Salary salary) {

        salaryRepository.save(salary);
    }

    @Override
    public void permitVacation(List<Vacation> vacations) {
        System.out.println("addtableservice: permitvacation");
        vacationRepository.saveAll(vacations);
    }


}
