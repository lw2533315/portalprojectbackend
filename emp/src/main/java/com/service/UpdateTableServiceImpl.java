package com.service;

import com.model.*;
import com.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private UserRepository userRepository;

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


    /*1. update vacation table
    * 2. call rabbitMq send message
    * */
    @Override
    public void addVacation(Vacation vacation, String username) {
        Employee employee = employeeRepository.findByUsername(username);
        vacation.setEmpId(employee.getId());
        vacation.setEmpName(employee.getFirstName() + " " + employee.getLastName());
        Vacation temp = vacationRepository.saveAndFlush(vacation);
//        vacationRepository.save()
        rabbitMQService.send(temp);
    }


    /*  persistent the password for the employee
    *   find the employee by token  from tabke PasswordResetToken to get employee's id
    *   then get username by the employee (find employee by id)
    *   add encode by Bcryptpasswordencoder();
    *
    *   @param    token (UUID token)
    * */
    @Override
    public void updatePassword(int id, String token, String password) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if(passwordResetToken.getEmpId() != id ){
            throw new RuntimeException("id, token not match");
        }
        Employee employee = employeeRepository.findById(passwordResetToken.getEmpId());
        User user = userRepository.findByUsername(employee.getUsername());
        user.setPassword(new BCryptPasswordEncoder().encode(password));
    }

    @Override
    public void deleteUsedTokenFrom(int id) {
       passwordResetTokenRepository.deleteAllByEmpId(id);
    }


}
