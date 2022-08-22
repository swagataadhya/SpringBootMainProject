package com.program.EmployeeManagementSystem.Model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
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
    @Column
    @NotEmpty(message = "Please enter Organization Id")
    private int orgid;
}
