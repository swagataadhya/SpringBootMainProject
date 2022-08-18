package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.OrganizationModel;
import com.program.EmployeeManagementSystem.Service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    OrganizationService organizationService;
    @PostMapping
    public ResponseEntity<String>addOrganization(@RequestBody OrganizationModel organizationModel)
    {
        organizationService.addOrganization(organizationModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteOrganization(@PathVariable int id)
    {
        try {
            organizationService.deleteOrganization(id);
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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
    public ResponseEntity<String>updateOrganization(@PathVariable int id,@RequestBody OrganizationModel organizationModel)
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