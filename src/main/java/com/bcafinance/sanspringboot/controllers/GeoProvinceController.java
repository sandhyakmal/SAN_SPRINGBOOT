package com.bcafinance.sanspringboot.controllers;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/1/2022 3:04 PM
Last Modified on 12/1/2022 3:04 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.dbo.GeoProvinceDTO;
import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.GeoProvince;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.services.GeoProvinceService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("api")

public class GeoProvinceController {

    @Getter
    private GeoProvinceService geoProvinceService;

    @Autowired
    private ModelMapper modelMapper;
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
    public ResponseEntity<List<GeoProvince>> getfindAll() {
        try {
            List<GeoProvince> lsGeoProvince = geoProvinceService.findAllGeoProvince();
            if (lsGeoProvince.isEmpty()) {
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
            }
            return new ResponseEntity<>(lsGeoProvince, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/geoprovince/dto/datas/all")
    public ResponseEntity<Object> findAllGeoProvinceDTO()throws Exception{

        List<GeoProvince> lsGeoProvince = geoProvinceService.findAllGeoProvince();

        if(lsGeoProvince.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<GeoProvinceDTO> lsGeoProvinceDTO = modelMapper.map(lsGeoProvince, new TypeToken<List<GeoProvinceDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsGeoProvinceDTO,null,null);
    }
    @GetMapping("/v2/geoprovince/datas/all")
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

    @GetMapping("/v1/geoprovince/datas/{province}")
    public ResponseEntity<Object> getGeographysByName(@PathVariable("province") String province)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geoProvinceService.findByProvinceName(province),null,null);
    }

    @GetMapping("/v1/geoprovince/{id}")
    public ResponseEntity<Object> getGeoProvinceById(@PathVariable("id") long id) throws Exception {
        GeoProvince geoProvince = geoProvinceService.findByIdGeoProvince(id);

        if(geoProvince != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,geoProvince,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @PutMapping("/v1/geoprovince/update")
    public ResponseEntity<Object> updateGeoProvinceByID(@Valid @RequestBody GeoProvince geoProvince)throws Exception{
        geoProvinceService.updateGeoProvinceById(geoProvince);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

}
