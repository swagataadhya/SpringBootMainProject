package com.program.EmployeeManagementSystem.Repository;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeModel, Integer> {
    EmployeeModel findAllByEmail(String email);

    EmployeeModel findByEmail(String email);

    @Query(nativeQuery = true,value = "SELECT * FROM `employee` WHERE orgid=:id")
    List<EmployeeModel> deleteData(@Param(value = "id") int id);
}
