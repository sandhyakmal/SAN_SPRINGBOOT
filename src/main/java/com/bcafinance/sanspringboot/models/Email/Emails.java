package com.bcafinance.sanspringboot.models.Email;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/4/2022 4:24 PM
Last Modified on 12/4/2022 4:24 PM
Version 1.0
*/


import com.bcafinance.sanspringboot.utils.ConstantMessage;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "MstEmails")
public class Emails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EmailsID")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_EMAIL_MENDATORY)
    @Column(name = "Email",nullable = false)
    private String emails;

    @Column(name = "DateRegister",nullable = false)
    private  Date dateRegister = new Date();

    @NotEmpty(message = ConstantMessage.WARNING_USERNAME_MENDATORY)
    @Column(name = "Username",nullable = false)
    private String username;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();//JANGAN GUNAKAN columnDefinition untuk set default kolom, langsung set di variabel nya saja.

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int size() {
        return 1;
    }
}

