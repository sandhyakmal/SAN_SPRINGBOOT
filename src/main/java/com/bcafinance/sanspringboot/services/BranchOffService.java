package com.bcafinance.sanspringboot.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/3/2022 3:54 PM
Last Modified on 12/3/2022 3:54 PM
Version 1.0
*/


import com.bcafinance.sanspringboot.handler.FormatValidation;
import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.models.Provinces;
import com.bcafinance.sanspringboot.repos.BranchOffRepo;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BranchOffService {

    private BranchOffRepo branchOffRepo;

    @Autowired
    BranchOffService(BranchOffRepo branchOffRepo){
        this.branchOffRepo = branchOffRepo;
    }

    public void saveBranchOff(BranchOffs branchOffs) throws Exception{

        if(branchOffs.getOfficeName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        FormatValidation.phoneNumberFormatValidation(branchOffs.getOfficeFax());

        Optional<BranchOffs> OfficeName = branchOffRepo.findByofficeName(branchOffs.getOfficeName());
        if(OfficeName.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_BRANCH_OFFICE_NAME_EXIST);
        }

        branchOffRepo.save(branchOffs);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllBranchOff(List<BranchOffs> ls){

        branchOffRepo.saveAll(ls);
    }

    public List<BranchOffs> findAllBranchOff()
    {
        return branchOffRepo.findAll();
    }

    public BranchOffs findByofficeName(String officeName) throws Exception
    {
        return branchOffRepo.findByofficeName(officeName).orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public BranchOffs findByIdBranchOff(Long id) throws Exception
    {
        return branchOffRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

    public List<BranchOffs> findByofficeNameContaining(String officeName)
    {
        return branchOffRepo.findByofficeNameContaining(officeName);
    }

    @Transactional(rollbackFor = {Exception.class, SQLException.class})
    public void updateBranchOfficeById(BranchOffs s) throws Exception{
        BranchOffs branchOffs = branchOffRepo.findById(s.getId()).orElseThrow(()->
                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));

        branchOffs.setModifiedBy("1");
        branchOffs.setModifiedDate(new Date());

        branchOffs.setOfficeName(s.getOfficeName());
        branchOffs.setOfficeCode(s.getOfficeCode());
        branchOffs.setOfficeType(s.getOfficeType());
        branchOffs.setDescription(s.getDescription());

//        if(branchOffs.getOfficeName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//
//        Optional<BranchOffs> OfficeName = branchOffRepo.findByofficeName(branchOffs.getOfficeName());
//        if(OfficeName.isPresent())
//        {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_BRANCH_OFFICE_NAME_EXIST);
//        }
    }
}
