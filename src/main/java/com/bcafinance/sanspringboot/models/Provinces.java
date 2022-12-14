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
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Data
@Entity
@Table(name = "MstProvince")
public class Provinces implements Serializable {

    private static final long serialversionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ProvinceID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "GeographyID")
    private Geographys geographys;

    @NotEmpty(message = ConstantMessage.WARNING_PROVINCE_MENDATORY)
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

    @ManyToMany
//    @JsonManagedReference
    @JoinTable(
            name = "ProvinceBranchOff",
            joinColumns = @JoinColumn(name="ProvinceID",referencedColumnName = "ProvinceID"),
            inverseJoinColumns = @JoinColumn(name = "BranchOfficeID",referencedColumnName = "BranchOfficeID")
    )
    private Set<BranchOffs> branchOffs = new HashSet<BranchOffs>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Geographys getGeographys() {
        return geographys;
    }

    public void setGeographys(Geographys geographys) {
        this.geographys = geographys;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Set<BranchOffs> getBranchoffs() {
        return branchOffs;
    }

    public void setBranchOffs(Set<BranchOffs> branchOffs) {
        this.branchOffs = branchOffs;
    }
}
