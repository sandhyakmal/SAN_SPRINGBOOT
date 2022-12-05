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
import com.bcafinance.sanspringboot.models.Emails;
import com.bcafinance.sanspringboot.services.EmailService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


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
    String tokens = emails.getEmails().substring(0,3)+emails.getCreatedDate().toString().substring(17,19)+"321";

    emails.setTokens(tokens);

    emailService.saveEmail(emails);

    System.out.println(System.getProperty("user.dir"));
    SMTPCore sc = new SMTPCore();
    ConfigProperties.getEmailPassword();
    String s = "coba";
    System.out.println(sc.sendMailWithAttachment(strArr,
            "EMAIL AUTHENTICATION","TOKEN REGISTRATION : "+"http://localhost:8080/api/v1/a/"+tokens,
            "SSL"));


    return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SEND_EMAIL, HttpStatus.CREATED,null,null,null);
    }
    @GetMapping("/v1/a/{tokens}")
    public ResponseEntity<Object> getTokens(@PathVariable("tokens") String tokens)throws Exception {
        Emails emailTokens = emailService.findByTokens(tokens);

        if (emailTokens != null) {
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_EMAIL, HttpStatus.OK, null, null, null);
        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_EMAIL_NOT_FOUND);
        }
    }
}
