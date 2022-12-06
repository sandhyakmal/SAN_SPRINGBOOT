package com.bcafinance.sanspringboot.services.UploadCSV;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 12/5/2022 11:05 AM
Last Modified on 12/5/2022 11:05 AM
Version 1.0
*/


import com.bcafinance.sanspringboot.models.UploadCSV.Cars;
import com.bcafinance.sanspringboot.repos.UploadCSV.CarRepo;
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
public class CarService {

    @Getter
    private CarRepo carRepo;

    @Autowired
    public CarService(CarRepo carRepo){
        this.carRepo = carRepo;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<Cars> saveBulkCars(MultipartFile multipartFile) throws Exception
    {
        try{
            List<Cars> lsCars = CsvReader.csvToCarData(multipartFile.getInputStream());
            return carRepo.saveAll(lsCars);
        }catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    public List<Cars> findAllCars()
    {
        return (List<Cars>)carRepo.findAll();
    }

    public Iterable<Cars> pagingFindCarByName(String carName, Pageable pageable)
    {
        return carRepo.findBycarNameIsContaining(carName,pageable);
    }

}
