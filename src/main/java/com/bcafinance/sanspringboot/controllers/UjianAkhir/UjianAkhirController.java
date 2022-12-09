package com.bcafinance.sanspringboot.controllers.UjianAkhir;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 09/12/2022 13:45
Last Modified on 09/12/2022 13:45
Version 1.0
*/

import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.UjianAkhir.UjianAkhir;
import com.bcafinance.sanspringboot.models.Wallets.Wallets;
import com.bcafinance.sanspringboot.services.UjianAkhir.UjianAkhirService;
import com.bcafinance.sanspringboot.services.WalletService.WalletService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api")
public class UjianAkhirController {

    @Getter
    private UjianAkhirService ujianAkhirService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UjianAkhirController(UjianAkhirService ujianAkhirService){
        this.ujianAkhirService = ujianAkhirService;
    }

    @PostMapping("/v1/ujianakhir/insert")
    public ResponseEntity<Object> saveUjianAkhirQuery(@RequestBody UjianAkhir ujianAkhir)throws Exception{
        ujianAkhirService.saveUjianAkhirQuerys(ujianAkhir);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_INSERT_QUERY, HttpStatus.OK,null,null,null);
    }

    @PostMapping("v1/ujianakhir/bat")
    public ResponseEntity<Object>
    saveAllUjianAkhir(@Valid @RequestBody List<UjianAkhir> listUjianAkhir) throws Exception {

        if(listUjianAkhir==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        ujianAkhirService.saveAllUjianAkhir(listUjianAkhir);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/ujianakhir/search/{size}/{page}")
    public ResponseEntity<Object> pageFindAllUjianAkhir(@PathVariable("size") int size,
                                                        @PathVariable("page") int page)throws Exception {
        Pageable pageable = PageRequest.of(page,size);

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,ujianAkhirService.pagingfindAll(pageable),null,null);
    }

}
