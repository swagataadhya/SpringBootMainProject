package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.AssetModel;
import com.program.EmployeeManagementSystem.Model.AssetOperation;
import com.program.EmployeeManagementSystem.Service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    AssetServiceImpl assetServiceImpl;

    @PostMapping
    public ResponseEntity<String> addAsset(@RequestBody AssetOperation ao) {
        if (ao.getRole().equals("admin") || ao.getRole().equals("manager")) {
            AssetModel am = new AssetModel();
            am.setAsset_name(ao.getAsset_name());
            am.setAsset_price(ao.getAsset_price());
            am.setAsset_type(ao.getAsset_type());
            am.setAsset_copyright(ao.getAsset_copyright());
            assetServiceImpl.addAsset(am);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<List<AssetModel>> getAsset() {
        try {
            List<AssetModel> am = assetServiceImpl.getAll();
            return new ResponseEntity<>(am, HttpStatus.OK);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAsset(@RequestBody AssetOperation ao) {
        if (ao.getRole().equals("admin")) {
            AssetModel am = new AssetModel();
            am.setId(ao.getAsset_id());
            assetServiceImpl.deleteAsset(am);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateasset(@RequestBody AssetOperation ao) {
        try {
            if (ao.getRole().equals("admin") || ao.getRole().equals("manager")) {
                AssetModel am = new AssetModel();
                am.setId(ao.getAsset_id());
                am.setAsset_name(ao.getAsset_name());
                am.setAsset_price(ao.getAsset_price());
                am.setAsset_type(ao.getAsset_type());
                am.setAsset_copyright(ao.getAsset_copyright());
                assetServiceImpl.updateAsset(am);
                return new ResponseEntity<>(HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
