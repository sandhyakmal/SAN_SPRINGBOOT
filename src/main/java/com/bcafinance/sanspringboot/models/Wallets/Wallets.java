package com.bcafinance.sanspringboot.models.Wallets;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 07/12/2022 10:50
Last Modified on 07/12/2022 10:50
Version 1.0
*/

import com.bcafinance.sanspringboot.utils.ConstantMessage;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "MstWallets")
public class Wallets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "WalletId")
    private Long id;

    @NotEmpty(message = ConstantMessage.WARNING_NAME_MENDATORY)
    @Column(name = "NomorRekening",nullable = false, unique = true)
    private String nomorRekening;

    @NotEmpty(message = ConstantMessage.WARNING_NAME_MENDATORY)
    @Column(name = "NamaNasabah",nullable = false)
    private String namaNasabah;

    @NotNull(message = ConstantMessage.WARNING_SALDO_MENDATORY)
    @Column(name = "Saldo",nullable = false)
    private Double saldo;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomorRekening() {
        return nomorRekening;
    }

    public void setNomorRekening(String nomorRekening) {
        this.nomorRekening = nomorRekening;
    }

    public String getNamaNasabah() {
        return namaNasabah;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
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
}
