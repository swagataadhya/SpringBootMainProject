package com.program.EmployeeManagementSystem.Controller;

import com.program.EmployeeManagementSystem.Model.AssetModel;

import com.program.EmployeeManagementSystem.Service.AssetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class AssetController {
    @Autowired
    AssetServiceImpl assetServiceImpl;

    @PostMapping(produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<String> addAsset(@RequestBody @Valid AssetModel assetModel) {
        if (assetModel.getAsset_name().length()>0&&assetModel.getAsset_copyright().length()>0&&assetModel.getAsset_price().length()>0&&assetModel.getAsset_type().length()>0)
        {
            assetServiceImpl.addAsset(assetModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>("Please enter valid data",HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<String> deleteAsset(@RequestBody AssetModel assetModel) {
        try
        {
            assetServiceImpl.deleteAsset(assetModel);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateAsset(@RequestBody @Valid AssetModel assetModel) {
        try {
            assetServiceImpl.updateAsset(assetModel);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
