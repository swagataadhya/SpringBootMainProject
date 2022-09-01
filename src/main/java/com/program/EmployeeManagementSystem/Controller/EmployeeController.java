package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.EmployeeModel;
import com.program.EmployeeManagementSystem.Service.EmployeeInterface;
import com.program.EmployeeManagementSystem.Service.OrganizationInterface;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeInterface employeeInterface;
    @Autowired
    OrganizationInterface organizationInterface;
    @GetMapping(value = "/login/{email}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<EmployeeModel> getEmployee(@PathVariable("email") String email) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (email.equals(authentication.getName())) {
                EmployeeModel emp = employeeInterface.getEmployee(email);
                return new ResponseEntity<>(emp, HttpStatus.OK);
            }
            else
            {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> addEmployee(@RequestBody @Valid EmployeeModel employeeModel)
    {
        if(employeeModel.getOrgId()!=0) {
            if (organizationInterface.addEmployee(employeeModel))
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            else
                return new ResponseEntity<>("Email already exist",HttpStatus.BAD_REQUEST);
        }
        else
            return new ResponseEntity<>("Please enter organization id",HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @DeleteMapping("{email}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("email")String email) {
        if(organizationInterface.deleteEmployee(email))
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        else
            return new ResponseEntity<>("THIS ID NOT IN RECORD",HttpStatus.BAD_REQUEST);
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<EmployeeModel>> getAllEmployee() {
        try {
            return new ResponseEntity<>(organizationInterface.getAll(), HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{email}")
    public ResponseEntity<String> updateEmployee(@PathVariable("email")String email,@RequestBody @Valid EmployeeModel employeeModel) {
        if(organizationInterface.updateEmployee(email,employeeModel))
            return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("RECORD NOT FOUND",HttpStatus.BAD_REQUEST);
    }

}
