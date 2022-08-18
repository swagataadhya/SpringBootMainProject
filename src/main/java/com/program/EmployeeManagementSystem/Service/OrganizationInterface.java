package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import com.program.EmployeeManagementSystem.Model.OrganizationModel;

import java.util.List;

public interface OrganizationInterface {
    public boolean addEmployee(EmployeeModel employeeModel);

    public boolean deleteEmployee(String email);

    public List<EmployeeModel> getAll();

    public boolean updateEmployee(String email,EmployeeModel employeeModel);

    //FOR ORGANIZATION
    void addOrganization(OrganizationModel org);
    void deleteOrganization(int id);
    List<OrganizationModel> getAllOrganization();
    void updateOrganization(int id,OrganizationModel organizationModel);
}
