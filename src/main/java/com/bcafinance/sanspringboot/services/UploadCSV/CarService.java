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
import org.springframework.data.domain.Page;
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
    public Iterable<Cars> pagingfindBycarNameContaining(String carName, Pageable pageable)
    {
        return carRepo.findBycarNameContaining(carName,pageable);
    }

    public Page<Cars> pagingFindPublic(String columnName, String value, String value1, Pageable pageable)
    {
        if(columnName.equalsIgnoreCase("carName")) 
        {
            return carRepo.findBycarNameContaining(value,pageable);
        } else if (columnName.equalsIgnoreCase("carModel"))
        {
            Integer parsCarModel = Integer.valueOf(value);
            return carRepo.findBycarModelIsGreaterThan(parsCarModel, pageable);
        } else if (columnName.equalsIgnoreCase("Brands"))
        {
            return carRepo.findByBrandsLike(value, pageable);
        } else if (columnName.equalsIgnoreCase("carCode"))
        {
            Integer parsCarCode = Integer.valueOf(value);
            return carRepo.findBycarCodeIsLessThan(parsCarCode, pageable);
        }else if (columnName.equalsIgnoreCase("price"))
        {
            Double parsPrice = Double.valueOf(value);
            Double parsPrice1 = Double.valueOf(value1);
            return carRepo.findBypriceBetween(parsPrice,parsPrice1, pageable);
        }
        return null;
    }

}
