package com.bcafinance.sanspringboot.controllers;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/3/2022 3:56 PM
Last Modified on 12/3/2022 3:56 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.services.BranchOffService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("api")

public class BranchOffController {

    @Getter
    private BranchOffService branchOffService;

    @Autowired
    public BranchOffController(BranchOffService branchOffService){
        this.branchOffService = branchOffService;
    }

    @PostMapping("/v1/branchoff")
    public ResponseEntity<Object>
    saveBranchOff(@Valid @RequestBody BranchOffs branchOffs) throws Exception {
        branchOffService.saveBranchOff(branchOffs);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }
}
