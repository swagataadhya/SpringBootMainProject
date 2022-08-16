package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import com.program.EmployeeManagementSystem.Service.EmployeeInterface;
import com.program.EmployeeManagementSystem.Service.OrganizationInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeInterface employeeInterface;
    @Autowired
    OrganizationInterface organizationInterface;

    @GetMapping(value = "/{email}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable("email") String email) {
        try {
            EmployeeModel emp = employeeInterface.getEmployee(email);
            return new ResponseEntity<>(emp,HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeModel employeeModel)
    {
        organizationInterface.addEmployee(employeeModel);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @DeleteMapping("/{email}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("email")String email) {
        organizationInterface.deleteEmployee(email);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<EmployeeModel>> getAllEmployee() {
        try {
            return new ResponseEntity<>(organizationInterface.getAll(), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeModel employeeModel) {
            organizationInterface.updateEmployee(employeeModel);
        return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
    }

}
