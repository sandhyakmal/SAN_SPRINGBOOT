package com.bcafinance.sanspringboot.models;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/1/2022 2:45 PM
Last Modified on 12/1/2022 2:45 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Data
@Entity
@Table(name = "MstGeographyProvince")
public class GeoProvince {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GeographyProvinceID")
    private Long id;

    @ManyToOne
    private Geographys geographys;

    @Column(name = "Province",length = 50, nullable = false)
    private String province;

    @NotEmpty(message = ConstantMessage.WARNING_PROVINCE_CODE_MENDATORY)
    @Column(name = "ProvinceCode",length = 50, nullable = false)
    private String provinceCode;

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

    public GeoProvince() {
    }
}
