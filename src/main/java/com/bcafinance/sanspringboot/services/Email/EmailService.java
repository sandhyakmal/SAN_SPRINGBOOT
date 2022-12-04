package com.bcafinance.sanspringboot.services.Email;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/4/2022 5:08 PM
Last Modified on 12/4/2022 5:08 PM
Version 1.0
*/


import com.bcafinance.sanspringboot.repos.BranchOffRepo;
import com.bcafinance.sanspringboot.repos.Email.EmailRepo;
import com.bcafinance.sanspringboot.repos.Email.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EmailService {

    private EmailRepo emailRepo;

    private TokenRepo tokenRepo;

//    @Autowired
//    EmailService(EmailRepo emailRepo){
//        this.emailRepo = emailRepo;
//    }
//
//    @Autowired
//    EmailService(TokenRepo tokenRepo) { this.tokenRepo = tokenRepo; }
}
