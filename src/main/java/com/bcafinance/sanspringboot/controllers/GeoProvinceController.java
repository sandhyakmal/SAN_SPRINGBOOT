package com.bcafinance.sanspringboot.controllers;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/1/2022 3:04 PM
Last Modified on 12/1/2022 3:04 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.GeoProvince;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.services.GeoProvinceService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("api")

public class GeoProvinceController {

    @Getter
    private GeoProvinceService geoProvinceService;

    public GeoProvinceController(){
    }

    @Autowired
    public GeoProvinceController(GeoProvinceService geoProvinceService){
        this.geoProvinceService = geoProvinceService;
    }

    @PostMapping("/v1/geoprovince")
    public ResponseEntity<Object>
    saveGeoProvince(@Valid @RequestBody GeoProvince geoProvince) throws Exception {
        if(geoProvince==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        geoProvinceService.saveGeographyProvince(geoProvince);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/geoprovince/datas/all")
    public ResponseEntity<Object> findAllGeoProvince()throws Exception{

        int data = 0;
        Iterable<GeoProvince> lsGeoProvince = geoProvinceService.findAllGeoProvince();

        if(lsGeoProvince instanceof Collection<GeoProvince>)
        {
            data = ((Collection<GeoProvince>) lsGeoProvince).size();
        }
        if(data==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsGeoProvince,null,null);
    }

}
