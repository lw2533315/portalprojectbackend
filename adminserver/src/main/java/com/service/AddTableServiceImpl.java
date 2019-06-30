package com.service;

import com.model.Address;
import com.model.Employee;
import com.model.Info;
import com.model.Project;
import com.repository.AddressRepository;
import com.repository.EmployeeRepository;
import com.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AddTableServiceImpl implements AddTableService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    ProjectRepository projectRepository;

    @Override
    public void SaveEmployeeAndAddress(Info info) {
        Employee employee = new Employee();
        employee = info.getEmployee();
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
}
