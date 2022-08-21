package com.program.EmployeeManagementSystem.Repository;

import com.program.EmployeeManagementSystem.Model.AssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepo extends JpaRepository<AssetModel, Integer> {
    AssetModel findById(int id);
    List<AssetModel> findAllByOrgid(int id);
}
