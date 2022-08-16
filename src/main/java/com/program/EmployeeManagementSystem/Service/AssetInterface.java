package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.AssetModel;

import java.util.List;

public interface AssetInterface {
    void addAsset(AssetModel am);

    List<AssetModel> getAll();

    void deleteAsset(AssetModel am);

    void updateAsset(AssetModel am);
}
