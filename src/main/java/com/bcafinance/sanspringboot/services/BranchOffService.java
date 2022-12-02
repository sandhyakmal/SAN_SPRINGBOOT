package com.bcafinance.sanspringboot.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/2/2022 2:51 PM
Last Modified on 12/2/2022 2:51 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.models.BranchOff;
import com.bcafinance.sanspringboot.models.Province;
import com.bcafinance.sanspringboot.repos.BranchOffRepo;
import com.bcafinance.sanspringboot.repos.ProvinceRepo;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BranchOffService {

    @Getter
    private BranchOffRepo branchOffRepo;

    @Autowired
    public void setBranchOffRepo(BranchOffRepo branchOffRepo) {
        this.branchOffRepo = branchOffRepo;
    }

    public void saveBranchOffice(BranchOff branchOff) throws Exception{
        if(branchOff.getOfficeName()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(branchOff.getOfficeType()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
        if(branchOff.getDescription()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        Optional<BranchOff> branchOffCode = branchOffRepo.findByofficeCode(branchOff.getOfficeCode());
        if(branchOffCode.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_BRANCH_OFFICE_CODE_EXIST);
        }

        branchOffRepo.save(branchOff);
    }
}
