package com.program.EmployeeManagementSystem.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id")
@Table(name = "asset")
public class AssetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    @NotEmpty(message = "Please enter Asset name")
    private String asset_name;
    @Column
    @NotEmpty(message = "Please enter Type")
    private String asset_type;
    @Column
    @NotEmpty(message = "Please enter Price")
    private String asset_price;
    @Column
    @NotEmpty(message = "Please enter Copyright")
    private String asset_copyright;
    @NotNull(message = "Please enter Organization Id")
    @Column
    private int orgid;
}
