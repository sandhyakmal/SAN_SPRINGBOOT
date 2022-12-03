package com.bcafinance.sanspringboot.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/2/2022 2:51 PM
Last Modified on 12/2/2022 2:51 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.repos.BranchOffRepo;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BranchOffService {

    @Getter
    private BranchOffRepo branchOffRepo;

    @Autowired
    public void BranchOffService(BranchOffRepo branchOffRepo){
        this.branchOffRepo =branchOffRepo;
    }

    public void saveBranchOffs(BranchOffs branchOffs) throws Exception{
//        if(branchOff.getOfficeName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if(branchOff.getOfficeType()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if(branchOff.getDescription()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//
//        Optional<BranchOff> branchOffCode = branchOffRepo.findByBranchOffCode(branchOff.getOfficeCode());
//        if(branchOffCode.isPresent())
//        {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_BRANCH_OFFICE_CODE_EXIST);
//        }

        branchOffRepo.save(branchOffs);
    }
//
//    @Transactional(rollbackFor = {Exception.class})
//    public void saveAllBranchOff(List<BranchOff> ls){
//        branchOffRepo.saveAll(ls);
//    }
//
//    public BranchOff findByBranchOffsName(String officeName) throws Exception
//    {
//        return branchOffRepo.findByBranchOffName(officeName).orElseThrow(()->
//                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
//    }
}
