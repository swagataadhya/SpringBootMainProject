package com.program.EmployeeManagementSystem.Model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "asset")
public class AssetModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private String asset_name;
    @Column
    private String asset_type;
    @Column
    private String asset_price;
    @Column
    private String asset_copyright;
}
