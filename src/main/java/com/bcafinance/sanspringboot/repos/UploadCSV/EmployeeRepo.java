package com.bcafinance.sanspringboot.repos.UploadCSV;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/5/2022 10:48 AM
Last Modified on 12/5/2022 10:48 AM
Version 1.0
*/

import com.bcafinance.sanspringboot.models.UploadCSV.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Long> {

    Page<Employee> findByEmployeeNameIsContaining(String employeeName, Pageable pageable);
//    Page<Employee> findAllEmployees(Pageable pageable);


}
