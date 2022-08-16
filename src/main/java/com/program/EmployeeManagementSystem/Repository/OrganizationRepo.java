package com.program.EmployeeManagementSystem.Repository;

import com.program.EmployeeManagementSystem.Model.OrganizationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizationRepo extends JpaRepository<OrganizationModel, Integer> {

}
