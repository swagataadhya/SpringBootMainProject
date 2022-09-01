package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.AssetModel;
import com.program.EmployeeManagementSystem.Model.OrganizationModel;
import com.program.EmployeeManagementSystem.Repository.AssetRepo;
import com.program.EmployeeManagementSystem.Repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssetServiceImpl implements AssetInterface {
    @Autowired
    AssetRepo assetRepo;
    @Autowired
    OrganizationRepo organizationRepo;

    @Override
    public void addAsset(AssetModel am) {
        boolean found=false;
        for (OrganizationModel o:organizationRepo.findAll())
        {
            if (o.getId()==am.getOrgid())
            {
                found=true;
                break;
            }
        }
        if (!found)
        {
            throw new NullPointerException("Organization not found");
        }
        assetRepo.save(am);
    }

    @Override
    public List<AssetModel> getAll() {
        return assetRepo.findAll();
    }

    @Override
    public void deleteAsset(int id) {
        assetRepo.deleteById(id);
    }

    @Override
    public void updateAsset(int id,AssetModel am) {
        boolean found=false;
        for (OrganizationModel o:organizationRepo.findAll())
        {
            if (o.getId()==am.getOrgid())
            {
                found=true;
                break;
            }
        }
        if (!found)
        {
            throw new NullPointerException("Organization not found");
        }
        AssetModel am2 = assetRepo.findById(id);
        am2.setAsset_type(am.getAsset_type());
        am2.setAsset_copyright(am.getAsset_copyright());
        am2.setAsset_price(am.getAsset_price());
        am2.setAsset_name(am.getAsset_name());
        am2.setOrgid(am.getOrgid());
        assetRepo.save(am2);
    }
}