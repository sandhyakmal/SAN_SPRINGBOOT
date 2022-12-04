package com.bcafinance.sanspringboot.dbo.BranchOff;
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

import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BranchOffIdDTO {

    private Long id;

    @Length(max = 50 , message = ConstantMessage.WARNING_BRANCH_OFFICE_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_BRANCH_OFFICE_NAME_MANDATORY)
    private String officeName;

    @Length(max = 50 , message = ConstantMessage.WARNING_BRANCH_OFFICE_CODE_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_BRANCH_OFFICE_CODE_MENDATORY)
    private String officeCode;

    private String officeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOfficeName() {
        return officeName;
    }

    public void setOfficeName(String officeName) {
        this.officeName = officeName;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getOfficeType() {
        return officeType;
    }

    public void setOfficeType(String officeType) {
        this.officeType = officeType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}
