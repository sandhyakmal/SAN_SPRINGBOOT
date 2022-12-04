package com.bcafinance.sanspringboot.services.Email;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/4/2022 5:08 PM
Last Modified on 12/4/2022 5:08 PM
Version 1.0
*/


import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.models.Email.Emails;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.repos.BranchOffRepo;
import com.bcafinance.sanspringboot.repos.Email.EmailRepo;
import com.bcafinance.sanspringboot.repos.Email.TokenRepo;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmailService {

    @Autowired
    private EmailRepo emailRepo;

    @Autowired
    private TokenRepo tokenRepo;

//    @Autowired
//    EmailService(EmailRepo emailRepo){this.emailRepo = emailRepo;}

//    @Autowired
//    EmailService(TokenRepo tokenRepo) { this.tokenRepo = tokenRepo; }

    public void saveEmail(Emails emails) throws Exception{
        if(emails.getEmails()==null)throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        Optional<Emails> getEmail = emailRepo.findByEmails(emails.getEmails());
        if(getEmail.isPresent())
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_EXISTING);
        }

        emailRepo.save(emails);
    }
}
