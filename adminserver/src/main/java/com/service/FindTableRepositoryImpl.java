package com.service;

import com.model.Employee;
import com.model.Project;
import com.repository.EmployeeRepository;
import com.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindTableRepositoryImpl implements FindTableRepository {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public List<Employee> findAllEmployees() {

        return employeeRepository.findAll();
    }

    @Override
    public List<Project> findAllProjects() {

        return projectRepository.findAll();
    }
}
