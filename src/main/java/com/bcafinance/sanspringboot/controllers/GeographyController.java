package com.bcafinance.sanspringboot.controllers;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 11/30/2022 2:05 PM
Last Modified on 11/30/2022 2:05 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.dbo.BranchOffDTO;
import com.bcafinance.sanspringboot.dbo.GeographyDTO;
import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.services.GeographyService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
public class GeographyController {

    @Getter
    private GeographyService geographyService;

    public GeographyController(){
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public GeographyController(GeographyService geographyService){
        this.geographyService = geographyService;
    }

    @GetMapping("/v1/geographys/{id}")
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

    @PostMapping("/v2/geographys")
    public ResponseEntity<Object>
    saveGeography(@Valid @RequestBody Geographys geographys) throws Exception {
        if(geographys==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        geographyService.saveGeography(geographys);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @PostMapping("v2/geographys/bat")
    public ResponseEntity<Object>
    saveAllGeographys(@Valid @RequestBody List<Geographys> listgeographys) throws Exception {

        if(listgeographys==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        geographyService.saveAllGeographys(listgeographys);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @PostMapping("/v1/geographys")
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

    @GetMapping("/v1/geographys/datas/all")
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

    @GetMapping("/v1/geographys/dto/datas/all")
    public ResponseEntity<Object> findAllGeographyDTO()throws Exception{

        List<Geographys> lsGeography = geographyService.findAllGeography();

        if(lsGeography.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<GeographyDTO> lsGeographyDTO =  modelMapper.map(lsGeography, new TypeToken<List<GeographyDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsGeographyDTO,null,null);
    }

//    @GetMapping("/v1/geographys/datas/{province}")
//    public ResponseEntity<Object> getGeographysByName(@PathVariable("province") String province)throws Exception{
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findByProvinceName(province),null,null);
//    }

    @GetMapping("/v1/geographys/region/sl/{regionname}")
    public ResponseEntity<Object> getRegionContaining(@PathVariable("regionname") String regionname)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findRegionNameContainings(regionname),null,null);
    }

    @GetMapping("/v1/geographys/city/nl/{name}")
    public ResponseEntity<Object> getCitysNotLike(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findCityNotLikes(name),null,null);
    }

    @GetMapping("/v1/geographys/city/sw/{name}")
    public ResponseEntity<Object> getCitysStartWith(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findCityStartWiths(name),null,null);
    }

    @GetMapping("/v1/geographys/city/ew/{name}")
    public ResponseEntity<Object> getCitysEndWith(@PathVariable("name") String name)throws Exception{

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,geographyService.findCityEndWiths(name),null,null);
    }

    @PutMapping("/v1/geographys/update")
    public ResponseEntity<Object> updateGeographyByID(@RequestBody Geographys geographys)throws Exception{
        geographyService.updateGeographyById(geographys);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

}
