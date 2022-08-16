package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import com.program.EmployeeManagementSystem.Repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService implements EmployeeInterface {
    @Autowired
    EmployeeRepo employeeRepo;

    @Override
    public EmployeeModel getEmployee(String email) {
        return employeeRepo.findAllByEmail(email);
    }
}
