package com.bcafinance.sanspringboot.models.UploadCSV;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 06/12/2022 10:27
Last Modified on 06/12/2022 10:27
Version 1.0
*/

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "MstCars")
public class Cars implements Serializable {

    private static final long serialversionUID = 1L;

    @Id
    @Column(name = "CardId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "CarName" ,length = 50, nullable = false)
    private String carName;

    @Column(name = "Brands" ,length = 20, nullable = false)
    private String brands;

    @Column(name = "CarModel",length = 20,nullable = false)
    private Integer carModel;

    @Column(name = "CarCode",length = 10, nullable = false)
    private Integer carCode;

    @Column(name = "CreatedDates",nullable = false)
    private LocalDate createdDates;

    @Column(name = "ExpiredDates" , nullable = false)
    private LocalDate expiredDates;

    @Column(name = "Price",length = 50,nullable = false)
    private Double price;

    @Column(name = "Tax",length = 50, nullable = false)
    private Double tax;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public Integer getCarModel() {
        return carModel;
    }

    public void setCarModel(Integer carModel) {
        this.carModel = carModel;
    }

    public Integer getCarCode() {
        return carCode;
    }

    public void setCarCode(Integer carCode) {
        this.carCode = carCode;
    }

    public LocalDate getCreatedDates() {
        return createdDates;
    }

    public void setCreatedDates(LocalDate createdDates) {
        this.createdDates = createdDates;
    }

    public LocalDate getExpiredDates() {
        return expiredDates;
    }

    public void setExpiredDates(LocalDate expiredDates) {
        this.expiredDates = expiredDates;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getTax() {
        return tax;
    }

    public void setTax(Double tax) {
        this.tax = tax;
    }
}
