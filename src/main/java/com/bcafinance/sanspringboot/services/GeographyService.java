package com.bcafinance.sanspringboot.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 11/30/2022 2:23 PM
Last Modified on 11/30/2022 2:23 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.models.Products;
import com.bcafinance.sanspringboot.repos.GeographyRepo;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class GeographyService {

    @Getter
    private GeographyRepo geographyRepo;

    @Autowired
    public void setGeographyRepoRepo(GeographyRepo geographyRepo) {
        this.geographyRepo = geographyRepo;
    }

    public void saveGeography(Geographys geographys) throws Exception{
        if(geographys.getRegionname()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(geographys.getProvinceCode()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(geographys.getProvince()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(geographys.getCity()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        Optional<Geographys> geoProvince = geographyRepo.findByProvinceCode(geographys.getProvinceCode());
        if(geoProvince.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_PROVINCE_CODE_EXIST);
        }

        Optional<Geographys> geoPostal = geographyRepo.findByPostalCode(geographys.getPostalCode());
        if(geoPostal.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_POSTAL_CODE_EXIST);
        }

        geographyRepo.save(geographys);
    }

    public Geographys findByProvinceName(String province) throws Exception
    {
        return geographyRepo.findByProvince(province).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public Geographys findByIdGeography(Long id) throws Exception
    {
        return geographyRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }
    public List<Geographys> findAllGeography()
    {
        return geographyRepo.findAll();
    }

    public void deleteByIdGeography(Long id) throws Exception{
        geographyRepo.deleteById(id);
    }


    public List<Geographys> findCityLikes(String name)
    {
        return geographyRepo.findByCityLike(name);
    }

    public List<Geographys> findCityNotLikes(String name)
    {
        return geographyRepo.findByCityNotLike(name);
    }

    public List<Geographys> findCityStartWiths(String name)
    {
        return geographyRepo.searchByCityStartsWith(name);
    }

    public List<Geographys> findCityEndWiths(String name)
    {
        return geographyRepo.searchByCityEndsWith(name);
    }


    @org.springframework.transaction.annotation.Transactional
    public void updateGeographyById(Geographys c) throws Exception{

        Geographys geographys = geographyRepo.findById(c.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_CUSTOMER_NOT_FOUND));

        geographys.setModifiedBy("1");
        geographys.setModifiedDate(new Date());
        if(c.getRegionname() != null
                && !Objects.equals(geographys.getRegionname(),c.getRegionname())
                && !c.getRegionname().equals(""))
        {
            geographys.setRegionname(c.getRegionname());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getProvince() != null
                && !Objects.equals(geographys.getProvince(),c.getProvince())
                && !c.getProvince().equals(""))
        {
            geographys.setProvince(c.getProvince());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getProvinceCode() != null
                && !Objects.equals(geographys.getProvinceCode(),c.getProvinceCode())
                && !c.getProvinceCode().equals(""))
        {
            geographys.setProvinceCode(c.getProvinceCode());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getCity() != null
                && !Objects.equals(geographys.getCity(),c.getCity())
                && !c.getCity().equals(""))
        {
            geographys.setCity(c.getCity());//BERARTI ADA PERUBAHAN DI SINI
        }

//        if(c.getPostalcode() != null
//                && !Objects.equals(geographys.getCity(),c.getCity())
//                && !c.getCity().equals(""))
//        {
//            geographys.setCity(c.getCity());//BERARTI ADA PERUBAHAN DI SINI
//        }

    }
}
