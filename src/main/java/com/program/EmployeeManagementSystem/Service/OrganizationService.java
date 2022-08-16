package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import com.program.EmployeeManagementSystem.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService implements OrganizationInterface {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void addEmployee(EmployeeModel employeeModel) {
        employeeModel.setEmployee_password(passwordEncoder.encode(employeeModel.getEmployee_password()));
        employeeRepo.save(employeeModel);
    }

    @Override
    public void deleteEmployee(String email) {
        EmployeeModel emp=employeeRepo.findByEmail(email);
        employeeRepo.deleteById(emp.getId());
    }

    @Override
    public List<EmployeeModel> getAll() {
        return employeeRepo.findAll();
    }

    @Override
    public void updateEmployee(EmployeeModel employeeModel)
    {
        EmployeeModel e1 = employeeRepo.findById(employeeModel.getId()).orElseThrow();
        e1.setRole(employeeModel.getRole());
        e1.setEmployee_name(employeeModel.getEmployee_name());
        e1.setEmployee_address(employeeModel.getEmployee_address());
        e1.setPhoneNumber(employeeModel.getPhoneNumber());
        e1.setEmployee_nationality(employeeModel.getEmployee_nationality());
        e1.setEmployee_gender(employeeModel.getEmployee_gender());
        e1.setEmployee_password(passwordEncoder.encode(employeeModel.getEmployee_password()));
        e1.setOrgId(employeeModel.getOrgId());
        employeeRepo.save(e1);
    }
}
