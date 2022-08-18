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

    @GetMapping(value = "/login/{email}", produces = MediaType.APPLICATION_XML_VALUE)
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
        if(employeeModel.getEmployee_password().length()>0&&employeeModel.getEmployee_address().length()>0&&employeeModel.getEmployee_nationality().length()>0&&employeeModel.getEmployee_name().length()>0&&employeeModel.getEmployee_password().length()>0&&employeeModel.getEmployee_gender().length()>0&&employeeModel.getEmail().length()>0&&employeeModel.getPhoneNumber().length()>0&&employeeModel.getRole().length()>0&&!employeeModel.getPassword().contains("-")&&employeeModel.getPhoneNumber().length()<=10)
        {
            if(organizationInterface.addEmployee(employeeModel))
                return new ResponseEntity<>("Created", HttpStatus.CREATED);
            else
                return new ResponseEntity<>("EMAIL ID ALREADY EXISTS",HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>("Please enter valid data",HttpStatus.BAD_REQUEST);
        }
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
    public ResponseEntity<String> updateEmployee(@PathVariable("email")String email,@RequestBody EmployeeModel employeeModel) {
        if(organizationInterface.updateEmployee(email,employeeModel))
            return new ResponseEntity<>("Updated", HttpStatus.ACCEPTED);
        else
            return new ResponseEntity<>("RECORD NOT FOUND",HttpStatus.BAD_REQUEST);
    }

}
