package com.service;

import com.model.Employee;
import com.model.Project;

import java.util.List;

public interface FindTableRepository {
    public List<Employee> findAllEmployees();
    public List<Project> findAllProjects();
}
