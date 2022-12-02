package com.bcafinance.sanspringboot.repos;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/2/2022 2:46 PM
Last Modified on 12/2/2022 2:46 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.models.BranchOff;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BranchOffRepo extends JpaRepository<BranchOff, Long> {

    Optional<BranchOff> findByofficeName(String officeName);
    Optional<BranchOff> findByofficeCode(String officeCode);
}
