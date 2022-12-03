package com.bcafinance.sanspringboot.repos;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/1/2022 2:59 PM
Last Modified on 12/1/2022 2:59 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.models.Provinces;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProvinceRepo extends CrudRepository<Provinces, Long> {

    Optional<Provinces> findByProvinceCode(String provinceCode);
    Optional<Provinces> findByProvince(String province);
}
