package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.AssetModel;
import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import com.program.EmployeeManagementSystem.Model.OrganizationModel;

import com.program.EmployeeManagementSystem.Repository.AssetRepo;
import com.program.EmployeeManagementSystem.Repository.EmployeeRepo;
import com.program.EmployeeManagementSystem.Service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/organization")
@Validated
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;
    @Autowired
    EmployeeRepo employeeRepo;
    @Autowired
    AssetRepo assetRepo;
    @PostMapping
    public ResponseEntity<String>addOrganization(@RequestBody @Valid OrganizationModel organizationModel)
    {
        organizationService.addOrganization(organizationModel);
        //return new ResponseEntity<>(HttpStatus.CREATED);
        return new ResponseEntity<>("Created",HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteOrganization(@PathVariable("id") int id)
    {
        try {
            organizationService.deleteOrganization(id);
            List<EmployeeModel> emp=employeeRepo.deleteData(id);
            for(EmployeeModel e:emp)
            {
                employeeRepo.deleteById(e.getId());
            }

            List<AssetModel>ass=assetRepo.findAllByOrgid(id);
            for(AssetModel a:ass)
            {
                assetRepo.deleteById(a.getId());
            }
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Data not found",HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<OrganizationModel>>getAll()
    {
        try {
            return new ResponseEntity<List<OrganizationModel>>(organizationService.getAllOrganization(),HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String>updateOrganization(@PathVariable("id") int id,@RequestBody @Valid OrganizationModel organizationModel)
    {
        try {
            organizationService.updateOrganization(id,organizationModel);
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
