package com.program.EmployeeManagementSystem.Repository;

import com.program.EmployeeManagementSystem.Model.AssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssetRepo extends JpaRepository<AssetModel, Integer> {
    AssetModel findById(int id);
}
