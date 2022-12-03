package com.bcafinance.sanspringboot.repos;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 11/30/2022 2:19 PM
Last Modified on 11/30/2022 2:19 PM
Version 1.0
*/


import com.bcafinance.sanspringboot.models.Geographys;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GeographyRepo extends JpaRepository<Geographys, Long> {
   //Optional<Geographys> findByProvince(String province);
   Optional<Geographys> findByPostalCode(String postalCode);
   //Optional<Geographys> findByProvinceCode(String provinceCode);
   List<Geographys> findByregionnameContaining(String regionname);
   List<Geographys> findByCityNotLike(String name);
   List<Geographys> searchByCityStartsWith(String name);
   List<Geographys> searchByCityEndsWith(String name);

}
