package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;

import java.util.List;

public interface OrganizationInterface {
    public boolean addEmployee(EmployeeModel employeeModel);

    public boolean deleteEmployee(String email);

    public List<EmployeeModel> getAll();

    public boolean updateEmployee(String email,EmployeeModel employeeModel);
}
