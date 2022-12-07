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

import java.util.List;
import java.util.Optional;

public interface WalletRepo extends JpaRepository<Wallets, Long> {

//    Optional<Wallets> findBynomorRekeningSumber(String nomorRekeningSumber);
//    Optional<Wallets> findBynomorRekeningTujuan(String nomorRekeningTujuan);
}
