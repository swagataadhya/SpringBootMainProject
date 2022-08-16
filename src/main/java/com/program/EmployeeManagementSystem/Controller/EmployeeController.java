package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import com.program.EmployeeManagementSystem.Model.EmployeeOperation;
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

    @GetMapping(value = "/{id}/{password}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable("id") int id,
                                                     @PathVariable("password") String pass) {
        try {
            EmployeeModel emp = employeeInterface.getEmployee(id);
            if (emp.getEmployee_password().equals(pass)) {
                return new ResponseEntity<>(emp, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeOperation employeeOperation) {
        if (employeeOperation.getRole().equalsIgnoreCase("admin") || employeeOperation.getRole().equalsIgnoreCase("manager")) {
            EmployeeModel emp = new EmployeeModel();
            emp.setEmployee_name(employeeOperation.getEmployee_name());
            emp.setEmployee_gender(employeeOperation.getEmployee_gender());
            emp.setEmployee_password(employeeOperation.getEmployee_password());
            emp.setEmployee_nationality(employeeOperation.getEmployee_nationality());
            emp.setEmployee_address(employeeOperation.getEmployee_address());
            emp.setEmployee_phoneno(employeeOperation.getEmployee_phoneno());
            emp.setRole(employeeOperation.getEmprole());
            emp.setOrgId(employeeOperation.getOrgid());
            emp.setEmail(employeeOperation.getEmail());
            organizationInterface.addEmployee(emp);
            return new ResponseEntity<>("Created", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteEmployee(@RequestBody EmployeeOperation employeeOperation) {
        if (employeeOperation.getRole().equalsIgnoreCase("admin") || employeeOperation.getRole().equalsIgnoreCase("manager")) {
            int id = employeeOperation.getId();
            organizationInterface.deleteEmployee(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
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
    public ResponseEntity<String> updateEmployee(@RequestBody EmployeeOperation employeeOperation) {
        if (employeeOperation.getRole().equalsIgnoreCase("admin") || employeeOperation.getRole().equalsIgnoreCase("manager")) {
            EmployeeModel emp = new EmployeeModel();
            emp.setId(employeeOperation.getId());
            emp.setEmployee_name(employeeOperation.getEmployee_name());
            emp.setEmployee_gender(employeeOperation.getEmployee_gender());
            emp.setEmployee_password(employeeOperation.getEmployee_password());
            emp.setEmployee_nationality(employeeOperation.getEmployee_nationality());
            emp.setEmployee_address(employeeOperation.getEmployee_address());
            emp.setEmployee_phoneno(employeeOperation.getEmployee_phoneno());
            emp.setRole(employeeOperation.getEmprole());
            emp.setOrgId(employeeOperation.getOrgid());
            emp.setEmail(employeeOperation.getEmail());
            organizationInterface.updateEmployee(emp);
            return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
