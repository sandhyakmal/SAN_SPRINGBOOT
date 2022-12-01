package com.bcafinance.sanspringboot.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/1/2022 3:01 PM
Last Modified on 12/1/2022 3:01 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.models.GeoProvince;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.repos.GeoProvinceRepo;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GeoProvinceService {

    @Getter
    private GeoProvinceRepo geoProvinceRepo;

    @Autowired
    public void setGeoProvinceRepo(GeoProvinceRepo geoProvinceRepo) {
        this.geoProvinceRepo = geoProvinceRepo;
    }

    public void saveGeographyProvince(GeoProvince geoProvince) throws Exception{
        if(geoProvince.getProvince()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(geoProvince.getProvinceCode()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        Optional<GeoProvince> geoProvinceCode = geoProvinceRepo.findByProvinceCode(geoProvince.getProvinceCode());
        if(geoProvinceCode.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_PROVINCE_CODE_EXIST);
        }

        geoProvinceRepo.save(geoProvince);
    }

    public List<GeoProvince> findAllGeoProvince()
    {
        return geoProvinceRepo.findAll();
    }
}
