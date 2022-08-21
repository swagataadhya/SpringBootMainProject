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
    @NotEmpty(message = "Please enter asset name")
    private String asset_name;
    @Column
    @NotEmpty(message = "Please enter type")
    private String asset_type;
    @Column
    @NotEmpty(message = "Please enter number")
    private String asset_price;
    @Column
    @NotEmpty(message = "Please enter copyright")
    private String asset_copyright;
    @Column
    private int orgid;
}
