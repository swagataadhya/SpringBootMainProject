package com.program.EmployeeManagementSystem.Service;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import com.program.EmployeeManagementSystem.Model.OrganizationModel;
import com.program.EmployeeManagementSystem.Repository.EmployeeRepo;
import com.program.EmployeeManagementSystem.Repository.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrganizationService implements OrganizationInterface {
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    OrganizationRepo organizationRepo;

    @Override
    public boolean addEmployee(EmployeeModel employeeModel) {
        List<EmployeeModel>emp=employeeRepo.findAll();
        boolean isFound=false;
        for (EmployeeModel e:emp)
        {
            if (e.getEmail().equals(employeeModel.getEmail())){
                isFound=true;
                break;
            }
        }

        if (isFound)
        {
            return false;
        }
        else {
            employeeModel.setEmployee_password(passwordEncoder.encode(employeeModel.getEmployee_password()));
            employeeRepo.save(employeeModel);
            return true;
        }

    }

    @Override
    public boolean deleteEmployee(String email) {
        try
        {
            EmployeeModel emp=employeeRepo.findByEmail(email);
            employeeRepo.deleteById(emp.getId());
            return true;
        }
        catch (NullPointerException e)
        {
            return false;
        }
    }

    @Override
    public List<EmployeeModel> getAll() {
        List<EmployeeModel>emp= employeeRepo.findAll();
        for (EmployeeModel e:emp)
        {
            e.setEmployee_password("THIS INFORMATION CANNOT BE LEAKED");
        }
        return emp;
    }

    @Override
    public boolean updateEmployee(String email,EmployeeModel employeeModel)
    {
        List<EmployeeModel> e1 = employeeRepo.findAll();
        boolean isFound=false;
        for (EmployeeModel e:e1){
            if (e.getEmail().equals(email))
            {
                e.setRole(employeeModel.getRole());
                e.setEmployee_name(employeeModel.getEmployee_name());
                e.setEmployee_address(employeeModel.getEmployee_address());
                e.setPhoneNumber(employeeModel.getPhoneNumber());
                e.setEmployee_nationality(employeeModel.getEmployee_nationality());
                e.setEmployee_gender(employeeModel.getEmployee_gender());
                e.setEmployee_password(passwordEncoder.encode(employeeModel.getEmployee_password()));
                e.setOrgId(employeeModel.getOrgId());
                employeeRepo.save(e);
                isFound=true;
                break;
            }
        }

        if (isFound)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

    //ORGANIZATION OPERATION

    @Override
    public void addOrganization(OrganizationModel org) {
        organizationRepo.save(org);
    }

    @Override
    public void deleteOrganization(int org) {
        organizationRepo.deleteById(org);
    }

    @Override
    public List<OrganizationModel> getAllOrganization() {
        return organizationRepo.findAll();
    }

    @Override
    public void updateOrganization(int id, OrganizationModel organizationModel) {
        OrganizationModel o=organizationRepo.findById(id).orElseThrow();
        o.setOrgname(organizationModel.getOrgname());
        organizationRepo.save(o);
    }
}
