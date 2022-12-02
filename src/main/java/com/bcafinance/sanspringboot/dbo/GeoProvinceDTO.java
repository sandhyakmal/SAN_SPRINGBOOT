package com.bcafinance.sanspringboot.dbo;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/1/2022 2:45 PM
Last Modified on 12/1/2022 2:45 PM
Version 1.0
*/

import com.bcafinance.sanspringboot.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoProvinceDTO {

    private Long id;

    @Length(max = 50 , message = ConstantMessage.WARNING_PROVINCE_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_PROVINCE_MENDATORY)
    private String province;

    @Length(max = 50 , message = ConstantMessage.WARNING_PROVINCE_CODE_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_PROVINCE_CODE_MENDATORY)
    private String provinceCode;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getProvince(){
        return province;
    }

    public void setProvince(String province){
        this.province = province;
    }

    public String getProvinceCode(){
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode){
        this.provinceCode = provinceCode;;
    }



}
