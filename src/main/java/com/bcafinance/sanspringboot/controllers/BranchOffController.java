package com.bcafinance.sanspringboot.controllers;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/3/2022 3:56 PM
Last Modified on 12/3/2022 3:56 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.dbo.BranchOffDTO;
import com.bcafinance.sanspringboot.dbo.GeoProvinceDTO;
import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.models.Provinces;
import com.bcafinance.sanspringboot.services.BranchOffService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")

public class BranchOffController {

    @Getter
    private BranchOffService branchOffService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public BranchOffController(BranchOffService branchOffService){
        this.branchOffService = branchOffService;
    }

    @PostMapping("/v1/branchoff")
    public ResponseEntity<Object>
    saveBranchOff( @RequestBody BranchOffs branchOffs) throws Exception {
        branchOffService.saveBranchOff(branchOffs);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @PostMapping("v1/branchoff/bat")
    public ResponseEntity<Object>
    saveAllBranchOff(@Valid @RequestBody List<BranchOffs> listBranchoff) throws Exception {

        if(listBranchoff==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        branchOffService.saveAllBranchOff(listBranchoff);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/branchoff/datas/all")
    public ResponseEntity<List<BranchOffs>> getfindAll() {
        try {
            List<BranchOffs> lsBranchOff = branchOffService.findAllBranchOff();
            if (lsBranchOff.isEmpty()) {
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
            }
            return new ResponseEntity<>(lsBranchOff, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/branchoff/dto/datas/all")
    public ResponseEntity<Object> findAllBranchOffDTO()throws Exception{

        List<BranchOffs> lsBranchOff = branchOffService.findAllBranchOff();

        if(lsBranchOff.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<BranchOffDTO> lsBranchOffDTO =  modelMapper.map(lsBranchOff, new TypeToken<List<BranchOffDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsBranchOffDTO,null,null);
    }

    @GetMapping("/v1/branchoff/datas/{officeName}")
    public ResponseEntity<Object> getBranchOffByName(@PathVariable("officeName") String officeName)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK, branchOffService.findByofficeName(officeName),null,null);
    }

    @GetMapping("/v1/branchoff/{id}")
    public ResponseEntity<Object> getBranchOffById(@PathVariable("id") long id) throws Exception {
        BranchOffs branchOffs = branchOffService.findByIdBranchOff(id);

        if(branchOffs != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, branchOffs,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @GetMapping("/v1/branchoff/sl/{officeName}")
    public ResponseEntity<Object> getOfficeNameContaining(@PathVariable("officeName") String officeName)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,branchOffService.findByofficeName(officeName),null,null);
    }

    @PutMapping("/v1/branchoff/update")
    public ResponseEntity<Object> updateBranchOfficeByID(@Valid @RequestBody BranchOffs branchOffs)throws Exception{
        branchOffService.updateBranchOfficeById(branchOffs);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

}
