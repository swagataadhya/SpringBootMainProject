package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.AssetModel;

import com.program.EmployeeManagementSystem.Service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    AssetServiceImpl assetServiceImpl;

    @PostMapping
    public ResponseEntity<String> addAsset(@RequestBody @Valid AssetModel assetModel) {
        if (assetModel.getOrgid()!=0) {
            if (!assetModel.getAsset_price().contains("-")) {
                assetServiceImpl.addAsset(assetModel);
                return new ResponseEntity<>("Created",HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Price should not be negative",HttpStatus.BAD_REQUEST);
            }
        }
        else
            return new ResponseEntity<>("Please enter Organization Id",HttpStatus.INTERNAL_SERVER_ERROR);
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAsset(@PathVariable("id") int id) {
        try
        {
            assetServiceImpl.deleteAsset(id);
            return new ResponseEntity<>("DELETED",HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<String>("NOT FOUND",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAsset(@PathVariable int id,@RequestBody @Valid AssetModel assetModel) {
        try {
            if(!assetModel.getAsset_price().contains("-")) {
                assetServiceImpl.updateAsset(id, assetModel);
                return new ResponseEntity<>("Updated",HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("price should not be negative",HttpStatus.BAD_REQUEST);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
