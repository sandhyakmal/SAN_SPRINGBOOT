package com.bcafinance.sanspringboot.services.UjianAkhir;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 09/12/2022 13:37
Last Modified on 09/12/2022 13:37
Version 1.0
*/

import com.bcafinance.sanspringboot.models.UjianAkhir.UjianAkhir;
import com.bcafinance.sanspringboot.models.UploadCSV.Cars;
import com.bcafinance.sanspringboot.models.Wallets.Wallets;
import com.bcafinance.sanspringboot.repos.UjianAkhir.UjianAkhirRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UjianAkhirService {

    private UjianAkhirRepo ujianAkhirRepo;

    @Autowired
    UjianAkhirService(UjianAkhirRepo ujianAkhirRepo){
        this.ujianAkhirRepo = ujianAkhirRepo;
    }

    public void saveUjianAkhirQuerys(UjianAkhir ujianAkhir) {
        ujianAkhirRepo.insertUjianAkhir(ujianAkhir.getVar1(), ujianAkhir.getVar2(), ujianAkhir.getVar3());
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllUjianAkhir(List<UjianAkhir> ls){
        ujianAkhirRepo.saveAll(ls);
    }

    public Iterable<UjianAkhir> pagingfindAll(Pageable pageable)
    {
        return ujianAkhirRepo.findAll(pageable);
    }
}
