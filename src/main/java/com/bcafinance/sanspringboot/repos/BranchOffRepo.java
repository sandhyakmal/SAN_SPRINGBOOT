package com.bcafinance.sanspringboot.repos;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/3/2022 3:53 PM
Last Modified on 12/3/2022 3:53 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.models.Provinces;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BranchOffRepo extends JpaRepository<BranchOffs, Long> {

    //Optional<BranchOffs> findByofficeCode(String officeCode);

    Optional<BranchOffs> findByofficeName(String officeName);

    List<BranchOffs> findByofficeNameContaining(String officeName);

}
