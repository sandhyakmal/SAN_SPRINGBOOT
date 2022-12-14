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
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.models.Provinces;
import com.bcafinance.sanspringboot.repos.ProvinceRepo;
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
public class ProvinceService {

    @Getter
    private ProvinceRepo provinceRepo;

    @Autowired
    public void setProvinceRepo(ProvinceRepo provinceRepo) {
        this.provinceRepo = provinceRepo;
    }

    public void saveProvince(Provinces provinces) throws Exception{

//        if(provinces.getProvince()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//
//        Optional<Provinces> ProvinceName = provinceRepo.findByProvince(
//                provinces.getProvince());
//        if(ProvinceName.isPresent())
//        {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_PROVINCE_NAME_EXIST);
//        }

        provinceRepo.save(provinces);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllProvince(List<Provinces> ls){
        provinceRepo.saveAll(ls);
    }

    public List<Provinces> findAllGeoProvince()
    {
        return  provinceRepo.findAll();
    }

    public Provinces findByProvinceName(String province) throws Exception
    {
        return provinceRepo.findByProvince(province).orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public Provinces findByIdGeoProvince(Long id) throws Exception
    {
        return provinceRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public List<Provinces> findByProvinceContaining(String province)
    {
        return provinceRepo.findByProvinceContaining(province);
    }

    @org.springframework.transaction.annotation.Transactional
    public void updateGeoProvinceById(Provinces c) throws Exception {

        Provinces provinces = provinceRepo.findById(c.getId()).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_GEOGRAPHY_NOT_FOUND));

        provinces.setModifiedBy("1");
        provinces.setModifiedDate(new Date());

//        if(provinces.getProvince()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//
//        Optional<Provinces> ProvinceName = provinceRepo.findByProvince(provinces.getProvince());
//        if(ProvinceName.isPresent())
//        {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_PROVINCE_NAME_EXIST);
//        }

        if (c.getProvince() != null
                && !Objects.equals(provinces.getProvince(), c.getProvince())
                && !c.getProvince().equals("")) {
            provinces.setProvince(c.getProvince());//BERARTI ADA PERUBAHAN DI SINI
        }

//        if (c.getProvinceCode() != null
//                && !Objects.equals(geoProvince.getProvinceCode(), c.getProvinceCode())
//                && !c.getProvinceCode().equals("")) {
//            geoProvince.setProvinceCode(c.getProvinceCode());//BERARTI ADA PERUBAHAN DI SINI
//        }


    }
    public void addBranchOff(BranchOffs branchOffs,Long provinceId) throws Exception {
        Provinces provinces = provinceRepo.findById(provinceId).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_BRANCH_OFFICE_NOT_FOUND));
        provinces.getBranchoffs().add(branchOffs);
        saveProvince(provinces);
    }
}
