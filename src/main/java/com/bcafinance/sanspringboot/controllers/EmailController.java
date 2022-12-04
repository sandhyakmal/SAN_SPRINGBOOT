package com.bcafinance.sanspringboot.controllers;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/4/2022 6:42 PM
Last Modified on 12/4/2022 6:42 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.configuration.ConfigProperties;
import com.bcafinance.sanspringboot.core.SMTPCore;
import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.Email.Emails;
import com.bcafinance.sanspringboot.models.Geographys;
import com.bcafinance.sanspringboot.services.Email.EmailService;
import com.bcafinance.sanspringboot.services.GeographyService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import com.bcafinance.sanspringboot.utils.ReadTextFileSB;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("api")
public class EmailController {

    @Getter
    private EmailService emailService;

    public EmailController(){
    }

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public EmailController(EmailService emailService){
        this.emailService = emailService;
    }

    @PostMapping("/v1/emails")
    public ResponseEntity<Object>
    saveEmails( @RequestBody Emails emails) throws Exception {
        if(emails==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        String[] strArr = new String[emails.size()];
        strArr[0] = emails.getEmails();

        emailService.saveEmail(emails);
//        Date tanggal = new Date();

        String token = emails.getEmails().substring(0,3)+emails.getDateRegister().toString().substring(17,19)+"321";
        System.out.println(System.getProperty("user.dir"));
        SMTPCore sc = new SMTPCore();
        ConfigProperties.getEmailPassword();
        String s = "coba";
        System.out.println(sc.sendMailWithAttachment(strArr,
                "EMAIL AUTHENTICATION","TOKEN REGISTRATION : "+"http://localhost:8080/api/v1/a/"+token,
                "SSL",
                new String[] {ResourceUtils.getFile("classpath:\\data\\sample.docx").getAbsolutePath()}));


        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SEND_EMAIL, HttpStatus.CREATED,null,null,null);
    }
}
