package com.program.EmployeeManagementSystem.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "organization")
public class OrganizationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotEmpty(message = "Please enter Organization Name")
    @Column
    private String orgname;
}
