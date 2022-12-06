package com.bcafinance.sanspringboot.dbo.UploadCSV;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 06/12/2022 11:52
Last Modified on 06/12/2022 11:52
Version 1.0
*/

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

public class CarDTO {

    private Long id;

    private String carName;

    private String brands;

    private Integer carModel;

    private Double price;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
