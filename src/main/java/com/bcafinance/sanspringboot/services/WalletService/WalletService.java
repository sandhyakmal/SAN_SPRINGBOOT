package com.bcafinance.sanspringboot.services.WalletService;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 07/12/2022 11:01
Last Modified on 07/12/2022 11:01
Version 1.0
*/


import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.models.Wallets.Wallets;
import com.bcafinance.sanspringboot.repos.WalletRepo.WalletRepo;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WalletService {

    private WalletRepo walletRepo;

    @Autowired
    WalletService(WalletRepo walletRepo){
        this.walletRepo = walletRepo;
    }

    public void saveWallet(Wallets wallets) throws Exception{
        walletRepo.save(wallets);
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllWallet(List<Wallets> ls){
        walletRepo.saveAll(ls);
    }

    public List<Wallets> findAllWallets()
    {
        return walletRepo.findAll();
    }

//    @Transactional(rollbackFor = {Exception.class, SQLException.class})
//    public void updateWalletsBynomorRekening(Wallets s) throws Exception{
//        Wallets rekeningSumber = walletRepo.findBynomorRekeningSumber(s.getNomorRekening()).orElseThrow(()->
//                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
//
//        Wallets rekeningTujuan = walletRepo.findBynomorRekeningTujuan(s.getNomorRekening()).orElseThrow(()->
//                new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
//
//        rekeningSumber.setModifiedBy("1");
//        rekeningTujuan.setModifiedBy("1");
//
//        rekeningSumber.setModifiedDate(new Date());
//        rekeningTujuan.setModifiedDate(new Date());
//
//        rekeningSumber.setSaldo(s.getSaldo());
//        rekeningTujuan.setSaldo(s.getSaldo());
//    }
}
