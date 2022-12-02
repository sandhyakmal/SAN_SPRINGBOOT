package com.bcafinance.sanspringboot.models;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/2/2022 2:28 PM
Last Modified on 12/2/2022 2:28 PM
Version 1.0
*/

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MstBranchOffice")
public class BranchOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BranchOfficeID")
    private Long id;

    @ManyToMany
    private Province province;

    @Column(name = "OfficeName",length = 50, nullable = false)
    private String officeName;

    @Column(name = "OfficeCode",length = 50, nullable = false)
    private String officeCode;

    @Column(name = "OfficeType",length = 50, nullable = false)
    private String officeType;

    @Column(name = "Description",length = 50, nullable = false)
    private String description;

    @Column(name = "CreatedBy",nullable = false)
    private String createdBy = "1";

    @Column(name = "CreatedDate",nullable = false)
    private Date createdDate = new Date();

    @Column(name = "ModifiedBy",nullable = true)
    private String modifiedBy ;

    @Column(name = "ModifiedDate",nullable = true)
    private Date modifiedDate;

    @Column(name = "IsActive",nullable = false)
    private boolean isActive = true;

    public BranchOff(){

    }
}
