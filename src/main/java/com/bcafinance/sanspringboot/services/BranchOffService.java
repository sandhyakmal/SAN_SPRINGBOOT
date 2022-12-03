package com.bcafinance.sanspringboot.services;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/3/2022 3:54 PM
Last Modified on 12/3/2022 3:54 PM
Version 1.0
*/


import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.models.Provinces;
import com.bcafinance.sanspringboot.repos.BranchOffRepo;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BranchOffService {

    private BranchOffRepo branchOffRepo;

    @Autowired BranchOffService(BranchOffRepo branchOffRepo){
        this.branchOffRepo = branchOffRepo;
    }

    public void saveBranchOff(BranchOffs branchOffs) throws Exception{

        if(branchOffs.getOfficeCode()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        Optional<BranchOffs> branchofficeCode = branchOffRepo.findByofficeCode(branchOffs.getOfficeCode());
        if(branchofficeCode.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_BRANCH_OFFICE_CODE_EXIST);
        }

        branchOffRepo.save(branchOffs);
    }
}
