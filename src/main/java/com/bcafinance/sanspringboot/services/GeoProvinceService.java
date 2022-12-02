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

import java.util.Date;
import java.util.List;
import java.util.Objects;
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

    public GeoProvince findByProvinceName(String province) throws Exception
    {
        return geoProvinceRepo.findByProvince(province).orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public GeoProvince findByIdGeoProvince(Long id) throws Exception
    {
        return geoProvinceRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    @org.springframework.transaction.annotation.Transactional
    public void updateGeoProvinceById(GeoProvince c) throws Exception {

        GeoProvince geoProvince = geoProvinceRepo.findById(c.getId()).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_GEOGRAPHY_NOT_FOUND));

        geoProvince.setModifiedBy("1");
        geoProvince.setModifiedDate(new Date());
        if (c.getProvince() != null
                && !Objects.equals(geoProvince.getProvince(), c.getProvince())
                && !c.getProvince().equals("")) {
            geoProvince.setProvince(c.getProvince());//BERARTI ADA PERUBAHAN DI SINI
        }

//        if (c.getProvinceCode() != null
//                && !Objects.equals(geoProvince.getProvinceCode(), c.getProvinceCode())
//                && !c.getProvinceCode().equals("")) {
//            geoProvince.setProvinceCode(c.getProvinceCode());//BERARTI ADA PERUBAHAN DI SINI
//        }

        Optional<GeoProvince> geoProvinceCode = geoProvinceRepo.findByProvinceCode(geoProvince.getProvinceCode());
        if(geoProvinceCode.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_PROVINCE_CODE_EXIST);
        }
    }
}
