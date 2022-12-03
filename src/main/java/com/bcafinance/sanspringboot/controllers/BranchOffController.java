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
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.services.BranchOffService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if(branchOffs==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        branchOffService.saveBranchOffs(branchOffs);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }
//
//    @PostMapping("v2/branchoff/bat")
//    public ResponseEntity<Object>
//    saveAllBranchOff(@RequestBody List<BranchOff> branchOffs) throws Exception {
//
//        if(branchOffs==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
//        branchOffService.saveAllBranchOff(branchOffs);
//        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
//    }
//
//    @GetMapping("/v1/branchoff/datas/search/{officeName}")
//    public ResponseEntity<Object> getBranchOffByName(@PathVariable("officeName") String officeName)throws Exception{
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,branchOffService.findByBranchOffsName(officeName),null,null);
//    }
}
