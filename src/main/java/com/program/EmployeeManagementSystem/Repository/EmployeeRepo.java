package com.program.EmployeeManagementSystem.Repository;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel, Integer> {
    EmployeeModel findAllById(int id);

    EmployeeModel findByEmail(String email);
}
