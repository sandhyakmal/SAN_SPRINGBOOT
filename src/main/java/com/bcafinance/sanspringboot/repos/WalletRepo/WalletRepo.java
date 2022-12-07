package com.bcafinance.sanspringboot.repos.WalletRepo;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 07/12/2022 10:59
Last Modified on 07/12/2022 10:59
Version 1.0
*/

import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.models.Wallets.Wallets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface WalletRepo extends JpaRepository<Wallets, Long> {

    Optional<Wallets> findBynomorRekening(String nomorRekening);

    @Modifying
    @Query(
            value =
                    "insert into MstWallets (NomorRekening,NamaNasabah,Saldo,CreatedBy,CreatedDate, IsActive)" +
                            " values (:namaNasabah, :nomorRekening, :saldo, :createdBy, :createdDate, :isActive)",
            nativeQuery = true)
    void insertWallets(@Param("nomorRekening") String nomorRekening,
                        @Param("namaNasabah") String namaNasabah,
                        @Param("saldo") Double saldo,
                        @Param("createdBy") String createdBy,
                        @Param("createdDate") Date createdDate,
                        @Param("isActive") Boolean isActive);

}
