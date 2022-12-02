package com.bcafinance.sanspringboot.dbo;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 11/30/2022 1:54 PM
Last Modified on 11/30/2022 1:54 PM
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
public class GeographysDTO {

    private Long id;

    @Length(message = ConstantMessage.WARNING_REGION_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_REGION_MENDATORY)
    private String regionname;

    @NotEmpty(message = ConstantMessage.WARNING_REGION_DESC_MENDATORY)
    private String description;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getRegionname(){
        return getRegionname();
    }

    public void setRegionname(String regionname){
        this.regionname = regionname;
    }

    public String getDescription(){
        return getDescription();
    }

    public void setDescription(String description){
        this.description = description;
    }

}
