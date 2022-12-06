package com.bcafinance.sanspringboot.services.UploadCSV;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/5/2022 11:05 AM
Last Modified on 12/5/2022 11:05 AM
Version 1.0
*/


import com.bcafinance.sanspringboot.models.UploadCSV.Employee;
import com.bcafinance.sanspringboot.repos.UploadCSV.EmployeeRepo;
import com.bcafinance.sanspringboot.utils.CsvReader;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Transactional
public class EmployeeService {

    @Getter
    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeService(EmployeeRepo employeeRepo){
        this.employeeRepo = employeeRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Employee> saveBulkEmployee(MultipartFile multipartFile) throws Exception
    {
        try{
            List<Employee> lsEmployee = CsvReader.csvToEmployeeData(multipartFile.getInputStream());
            return employeeRepo.saveAll(lsEmployee);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Employee> findAllEmployee()
    {
        return (List<Employee>)employeeRepo.findAll();
    }


    public Iterable<Employee> pagingFindEmployeeByName(String employeeName, Pageable pageable)
    {
        return employeeRepo.findByEmployeeNameIsContaining(employeeName,pageable);
    }

}
