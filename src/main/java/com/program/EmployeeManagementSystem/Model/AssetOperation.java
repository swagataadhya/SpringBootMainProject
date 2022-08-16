package com.program.EmployeeManagementSystem.Model;

import lombok.Data;

@Data
public class AssetOperation {
    private String role;
    private int asset_id;
    private String asset_name;
    private String asset_type;
    private String asset_price;
    private String asset_copyright;
}
