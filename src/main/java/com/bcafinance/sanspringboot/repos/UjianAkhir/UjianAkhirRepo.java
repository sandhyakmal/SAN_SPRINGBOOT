package com.bcafinance.sanspringboot.repos.UjianAkhir;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 09/12/2022 13:35
Last Modified on 09/12/2022 13:35
Version 1.0
*/

import com.bcafinance.sanspringboot.models.UjianAkhir.UjianAkhir;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface UjianAkhirRepo extends JpaRepository<UjianAkhir, Long> {

    @Modifying
    @Query(
            value =
                    "insert into UjianAkhir (Var1,Var2,Var3)" +
                            " values (:var1, :var2, :var3)",
            nativeQuery = true)
    void insertUjianAkhir(@Param("var1") Integer var1,
                       @Param("var2") Double var2,
                       @Param("var3") String var3);

    Page<UjianAkhir> findAll(Pageable pageable);
}
