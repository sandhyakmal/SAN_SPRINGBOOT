package com.bcafinance.sanspringboot.controllers;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/2/2022 4:21 PM
Last Modified on 12/2/2022 4:21 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.BranchOff;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.services.BranchOffService;
import com.bcafinance.sanspringboot.services.ProvinceService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class BranchOffController {

    @Getter
    private BranchOffService branchOffService;

    @Autowired
    private ModelMapper modelMapper;
    public BranchOffController(){
    }

    @Autowired
    public BranchOffController(BranchOffService branchOffService){
        this.branchOffService = branchOffService;
    }

    @PostMapping("/v2/branchoff")
    public ResponseEntity<Object>
    saveBranchOff(@Valid @RequestBody BranchOff branchOff) throws Exception {
        if(branchOff==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        branchOffService.saveBranchOffice(branchOff);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @PostMapping("v2/branchoff/bat")
    public ResponseEntity<Object>
    saveAllBranchOff(@RequestBody List<BranchOff> branchOffs) throws Exception {

        if(branchOffs==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        branchOffService.saveAllBranchOff(branchOffs);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }
}
