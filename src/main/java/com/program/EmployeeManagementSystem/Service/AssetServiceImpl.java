package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.AssetModel;
import com.program.EmployeeManagementSystem.Repository.AssetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetInterface {
    @Autowired
    AssetRepo assetRepo;

    @Override
    public void addAsset(AssetModel am) {
        assetRepo.save(am);
    }

    @Override
    public List<AssetModel> getAll() {
        return assetRepo.findAll();
    }

    @Override
    public void deleteAsset(AssetModel am) {
        assetRepo.deleteById(am.getId());
    }

    @Override
    public void updateAsset(AssetModel am) {
        AssetModel am2 = assetRepo.findById(am.getId());
        am2.setAsset_type(am.getAsset_type());
        am2.setAsset_copyright(am.getAsset_copyright());
        am2.setAsset_price(am.getAsset_price());
        am2.setAsset_name(am.getAsset_name());
        am2.setOrgid(am.getOrgid());
        assetRepo.save(am2);
    }
}