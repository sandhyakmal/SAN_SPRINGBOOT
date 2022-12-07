package com.bcafinance.sanspringboot.repos.UploadCSV;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/5/2022 10:48 AM
Last Modified on 12/5/2022 10:48 AM
Version 1.0
*/

import com.bcafinance.sanspringboot.models.UploadCSV.Cars;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepo extends JpaRepository<Cars, Long> {

    Page<Cars> findBycarNameContaining(String carName, Pageable pageable);
    Page<Cars> findByBrandsLike(String brands, Pageable pageable);
    Page<Cars> findBycarModelIsGreaterThan(Integer carModel, Pageable pageable);
    Page<Cars> findBycarCodeIsLessThan(Integer carCode,Pageable pageable);
    Page<Cars> findBypriceBetween(Double price, Double price1, Pageable pageable);

}
