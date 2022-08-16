package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.AssetModel;

import java.util.List;

public interface AssetInterface {
    public void addAsset(AssetModel am);

    public List<AssetModel> getAll();

    public void deleteAsset(AssetModel am);

    public void updateAsset(AssetModel am);
}
