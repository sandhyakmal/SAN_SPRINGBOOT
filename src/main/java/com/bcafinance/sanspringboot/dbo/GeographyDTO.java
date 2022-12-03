package com.bcafinance.sanspringboot.dbo;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/3/2022 10:18 PM
Last Modified on 12/3/2022 10:18 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeographyDTO {

    private Long id;

    @Length(max = 50 , message = ConstantMessage.WARNING_REGION_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_REGION_MENDATORY)
    private String regionname;

    @Length(max = 50 , message = ConstantMessage.WARNING_CITY_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_CITY_MENDATORY)
    private String city;

    private String postalCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegionname() {
        return regionname;
    }

    public void setRegionname(String regionname) {
        this.regionname = regionname;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
