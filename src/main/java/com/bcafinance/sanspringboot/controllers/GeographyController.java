package com.bcafinance.sanspringboot.controllers;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 11/30/2022 2:05 PM
Last Modified on 11/30/2022 2:05 PM
Version 1.0
*/


import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.services.GeographyService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.Collection;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class GeographyController {

    @Getter
    private GeographyService geographyService;

    public GeographyController(){

    }

    @Autowired
    public GeographyController(GeographyService geographyService){
        this.geographyService = geographyService;
    }

    @GetMapping("/geographys/{id}")
    public ResponseEntity<Object> getGeographyById(@PathVariable("id") long id) throws Exception {
        Geographys geographys = geographyService.findByIdGeography(id);

        if(geographys != null)
        {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,geographys,null,null);
        }
        else
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

    @PostMapping("/geographys")
    public ResponseEntity<Object>
    saveGeography(@RequestBody Geographys geographys,
                @RequestHeader Map<String,String> headers,
                @RequestParam Map<String,String> params,
                WebRequest request) throws Exception {

        String[] attributeNames = request.getAttributeNames(0);
        for (String a:
                attributeNames) {
            System.out.println("ATTR 1 => "+a);
        }
        System.out.println("SESSION ID -> "+request.getSessionId());

        if(geographys==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);

        geographyService.saveGeography(geographys);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/geographys/datas/all")
    public ResponseEntity<Object> findAllGeography()throws Exception{

        int data = 0;
        Iterable<Geographys> lsGeographys = geographyService.findAllGeography();

        if(lsGeographys instanceof Collection<Geographys>)
        {
            data = ((Collection<Geographys>) lsGeographys).size();
        }
        if(data==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsGeographys,null,null);
    }

    @GetMapping("/geographys/datas/{province}")
    public ResponseEntity<Object> getGeographysByName(@PathVariable("province") String province)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findByProvinceName(province),null,null);
    }

    @GetMapping("/geographys/city/sl/{name}")
    public ResponseEntity<Object> getCitysLike(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findCityLikes(name),null,null);
    }

    @GetMapping("/geographys/city/nl/{name}")
    public ResponseEntity<Object> getCitysNotLike(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findCityNotLikes(name),null,null);
    }

    @GetMapping("/geographys/city/sw/{name}")
    public ResponseEntity<Object> getCitysStartWith(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findCityStartWiths(name),null,null);
    }

    @GetMapping("/geographys/city/ew/{name}")
    public ResponseEntity<Object> getCitysEndWith(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findCityEndWiths(name),null,null);
    }

    @PutMapping("/geographys/update")
    public ResponseEntity<Object> updateGeographyByID(@RequestBody Geographys geographys)throws Exception{
        geographyService.updateGeographyById(geographys);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

}
