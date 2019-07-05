package com.service;

import com.model.*;

import java.util.List;

public interface AddTableService {
    public void SaveEmployeeAndAddress(Info info);
    public void saveProjct(Project project);
    public void saveTimesheet(TimeSheet timesheet);
    public void addSalary(Salary salary);


    public void permitVacation(List<Vacation> vacations);
}
