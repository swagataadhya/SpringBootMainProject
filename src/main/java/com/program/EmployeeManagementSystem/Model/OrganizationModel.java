package com.program.EmployeeManagementSystem.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "organization")
public class OrganizationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String orgname;
}
