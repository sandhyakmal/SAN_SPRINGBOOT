package com.bcafinance.sanspringboot.models;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 11/30/2022 1:54 PM
Last Modified on 11/30/2022 1:54 PM
Version 1.0
*/

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "MstGeography")

public class Geographys {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GeographyID")
    private Long id;

    @Column(name = "RegionName",nullable = false)
    private String regionname;

    @Column(name = "Province",nullable = false)
    private String province;

    @Column(name = "ProvinceCode",nullable = false)
    private String provinceCode;

    @Column(name = "City",nullable = false)
    private String city;

    @Column(name = "PostalCode",nullable = true)
    private Long postalCode;

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

    public Geographys() {
    }
}
