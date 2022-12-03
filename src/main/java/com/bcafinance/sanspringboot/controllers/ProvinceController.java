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
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.models.Provinces;
import com.bcafinance.sanspringboot.services.ProvinceService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api")

public class ProvinceController {

    @Getter
    private ProvinceService provinceService;

    @Autowired
    private ModelMapper modelMapper;
    public ProvinceController(){
    }

    @Autowired
    public ProvinceController(ProvinceService provinceService){
        this.provinceService = provinceService;
    }

    @PostMapping("/v1/geoprovinces")
    public ResponseEntity<Object>
    saveProvince(@Valid @RequestBody Provinces provinces) throws Exception {
        provinceService.saveProvince(provinces);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }


    @GetMapping("/v1/geoprovince/datas/all")
    public ResponseEntity<List<Provinces>> getfindAll() {
        try {
            List<Provinces> lsProvinces = provinceService.findAllGeoProvince();
            if (lsProvinces.isEmpty()) {
                //return new ResponseEntity<>(HttpStatus.NO_CONTENT);
                throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
            }
            return new ResponseEntity<>(lsProvinces, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/geoprovince/dto/datas/all")
    public ResponseEntity<Object> findAllGeoProvinceDTO()throws Exception{

        List<Provinces> lsProvinces = provinceService.findAllGeoProvince();

        if(lsProvinces.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<GeoProvinceDTO> lsGeoProvinceDTO = modelMapper.map(lsProvinces, new TypeToken<List<GeoProvinceDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsGeoProvinceDTO,null,null);
    }

    @GetMapping("/v1/geoprovince/datasDTO/all")
    public ResponseEntity<Object> findAllGeoProvinceDTO2()throws Exception{

        List<Provinces> lsProvinces = provinceService.findAllGeoProvince();

        if(lsProvinces.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        TypeMap<Provinces, GeoProvinceDTO> propertyMapper = modelMapper.createTypeMap(Provinces.class, GeoProvinceDTO.class);
        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getGeographys().getCity(), GeoProvinceDTO::setCity)
        );

        propertyMapper.addMappings(
                mapper -> mapper.map(src -> src.getGeographys().getPostalCode(), GeoProvinceDTO::setPostalCode)
        );

        List<GeoProvinceDTO> lsGeoProvinceDTO = modelMapper.map(lsProvinces, new TypeToken<List<GeoProvinceDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsGeoProvinceDTO,null,null);
    }

    @GetMapping("/v2/geoprovince/datas/all")
    public ResponseEntity<Object> findAllGeoProvince()throws Exception{

        int data = 0;
        Iterable<Provinces> lsGeoProvince = provinceService.findAllGeoProvince();

        if(lsGeoProvince instanceof Collection<Provinces>)
        {
            data = ((Collection<Provinces>) lsGeoProvince).size();
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
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK, provinceService.findByProvinceName(province),null,null);
    }

    @GetMapping("/v1/geoprovince/{id}")
    public ResponseEntity<Object> getGeoProvinceById(@PathVariable("id") long id) throws Exception {
        Provinces provinces = provinceService.findByIdGeoProvince(id);

        if(provinces != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, provinces,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @PutMapping("/v1/geoprovince/update")
    public ResponseEntity<Object> updateGeoProvinceByID(@Valid @RequestBody Provinces provinces)throws Exception{
        provinceService.updateGeoProvinceById(provinces);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/v1/province/branchoff/{id}")
    public void addBranchOff(@RequestBody BranchOffs branchOffs, @PathVariable("provinceId") Long provinceId) throws Exception {
        provinceService.addBranchOff(branchOffs,provinceId);
    }

}
