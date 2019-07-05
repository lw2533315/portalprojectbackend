package com.service;

import com.model.*;
import com.repository.EmployeeRepository;
import com.repository.ProjectRepository;
import com.repository.TasksheetRepository;
import com.repository.VacationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional(rollbackOn = Exception.class)
public class UpdateTableServiceImpl implements UpdateTableService {
    @Autowired
    private TasksheetRepository tasksheetRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private VacationRepository vacationRepository;
    @Autowired
    private RabbitMQService rabbitMQService;

    @Override
    public void updateTimesheet(TimeSheet timesheet) {
        Tasksheet tasksheet = new Tasksheet();
        tasksheet.setId(timesheet.getId());
        tasksheet.setAction(timesheet.getAction());
        tasksheet.setEndDate(timesheet.getEndDate());
        tasksheet.setTask(timesheet.getTask());
        tasksheet.setTaskDescription(timesheet.getTaskDescription());
        Employee employee = employeeRepository.findById(timesheet.getEmployeeCode());
        Project project = projectRepository.findByProjId(timesheet.getProjectCode());
        tasksheet.setEmployee(employee);
        tasksheet.setProject(project);
        tasksheetRepository.save(tasksheet);
    }

    @Override
    public void addVacation(Vacation vacation, String username) {
        Employee employee = employeeRepository.findByUsername(username);
        vacation.setEmpId(employee.getId());
        vacation.setEmpName(employee.getFirstName() + " " + employee.getLastName());
        Vacation temp = vacationRepository.saveAndFlush(vacation);
//        vacationRepository.save()
        rabbitMQService.send(temp);
    }


}
