package com.bcafinance.sanspringboot.controllers.UploadSCV;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/5/2022 11:17 AM
Last Modified on 12/5/2022 11:17 AM
Version 1.0
*/


import com.bcafinance.sanspringboot.dbo.Employee.EmployeeDTO;
import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.UploadCSV.Employee;
import com.bcafinance.sanspringboot.services.UploadCSV.EmployeeService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import com.bcafinance.sanspringboot.utils.CsvReader;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/")
public class EmployeeControlller {

    @Getter
    private EmployeeService employeeService;

    @Autowired
    private ModelMapper modelMapper;

    private List<Employee> lsEmployee = new ArrayList<Employee>();

    @Autowired
    public EmployeeControlller(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/v1/employee/upl/bat/11")
    public ResponseEntity<Object>
    uploadEmployee(@Valid @RequestParam("demoFile") MultipartFile multipartFile) throws Exception {
        try{
            if(CsvReader.isCsv(multipartFile))
            {
                employeeService.saveBulkEmployee(multipartFile);
            }
            else
            {
                throw new ResourceNotFoundException(ConstantMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename());
            }
        }catch (Exception e)
        {
            throw new Exception(ConstantMessage.ERROR_UPLOAD_CSV+multipartFile.getOriginalFilename());
        }
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,
                HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/employee/datas/all/dto")
    public ResponseEntity<Object> findAllEmployeeDTO()throws Exception {

        List<Employee> lsEmployee = employeeService.findAllEmployee();

        if(lsEmployee.size()!=0)
        {
            List<EmployeeDTO> lsEmployeeDTO = modelMapper.map(lsEmployee, new TypeToken<List<EmployeeDTO>>() {}.getType());

            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsEmployeeDTO,null,null);
        }
        throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
    }

    @GetMapping("/v1/employee/search/dto/{size}/{page}")
    public ResponseEntity<Object> pageFindEmployeeByNameDTO(@RequestParam String employeeName,
                                                           @PathVariable("size") int size,
                                                           @PathVariable("page") int page )throws Exception {

        Pageable pageable = PageRequest.of(page,size);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,employeeService.pagingFindEmployeeByName(employeeName,pageable),null,null);
    }

    @GetMapping("/v1/employee/search/dto/{size}/{page}/{sort}")
    public ResponseEntity<Object> pageSortByNameDTO(@RequestParam String employeeName,
                                                    @PathVariable("size") int size,
                                                    @PathVariable("page") int page,
                                                    @PathVariable("sort") String sortz)throws Exception {

        Pageable pageable;
        if(sortz.equalsIgnoreCase("desc"))
        {
            pageable = PageRequest.of(page,size, Sort.by("id").descending());
        }
        else
        {
            pageable = PageRequest.of(page,size, Sort.by("id"));//default asc
        }

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,employeeService.pagingFindEmployeeByName(employeeName,pageable),null,null);
    }

}
