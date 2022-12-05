package com.bcafinance.sanspringboot.dbo.Employee;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/5/2022 11:26 AM
Last Modified on 12/5/2022 11:26 AM
Version 1.0
*/

import com.bcafinance.sanspringboot.utils.ConstantMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeDTO {


    @NotEmpty
    private Long id;

    @Length(max = 50 , message = ConstantMessage.WARNING_EMPLOYEE_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_EMPLOYEE_NAME_MENDATORY)
    private String employeeName;

    @Length(max = 20 , message = ConstantMessage.WARNING_FIRST_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_FISRT_NAME_MENDATORY)
    private String firstName;

    @Length(max = 20 , message = ConstantMessage.WARNING_LAST_NAME_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_LAST_NAME_MENDATORY)
    private String lastName;

    @NotEmpty(message = ConstantMessage.WARNING_BIRTH_DATE_MENDATORY)
    private LocalDate birthDate;

    @Length(max = 50 , message = ConstantMessage.WARNING_ADDRESS_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_ADDRESS_MENDATORY)
    private String address;

    @Length(max = 20 , message = ConstantMessage.WARNING_GENDER_LENGTH)
    @NotEmpty(message = ConstantMessage.WARNING_GENDER_MENDATORY)
    private String gender;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
