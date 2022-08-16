package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.AssetModel;

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
    public ResponseEntity<String> addAsset(@RequestBody AssetModel assetModel) {
        assetServiceImpl.addAsset(assetModel);
        return new ResponseEntity<>(HttpStatus.CREATED);
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
    public ResponseEntity<String> deleteAsset(@RequestBody AssetModel assetModel) {
        assetServiceImpl.deleteAsset(assetModel);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<String> updateasset(@RequestBody AssetModel assetModel) {
        try {
            assetServiceImpl.updateAsset(assetModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
