package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;

import java.util.List;

public interface OrganizationInterface {
    public void addEmployee(EmployeeModel employeeModel);

    public void deleteEmployee(String email);

    public List<EmployeeModel> getAll();

    public void updateEmployee(EmployeeModel employeeModel);
}
