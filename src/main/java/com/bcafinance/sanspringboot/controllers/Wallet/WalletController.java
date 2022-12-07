package com.bcafinance.sanspringboot.controllers.Wallet;
/*
@Author Andara a.k.a. Sandhy
Junior Programmer
Created with IntelliJ IDEA Version 2022.2.3 (Community Edition)
Created on 07/12/2022 11:09
Last Modified on 07/12/2022 11:09
Version 1.0
*/

import com.bcafinance.sanspringboot.dbo.BranchOffs.BranchOffDTO;
import com.bcafinance.sanspringboot.dbo.Wallets.WalletDTO;
import com.bcafinance.sanspringboot.handler.ResourceNotFoundException;
import com.bcafinance.sanspringboot.handler.ResponseHandler;
import com.bcafinance.sanspringboot.models.BranchOffs;
import com.bcafinance.sanspringboot.models.Provinces;
import com.bcafinance.sanspringboot.models.Wallets.Wallets;
import com.bcafinance.sanspringboot.services.BranchOffService;
import com.bcafinance.sanspringboot.services.WalletService.WalletService;
import com.bcafinance.sanspringboot.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class WalletController {

    @Getter
    private WalletService walletService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public WalletController(WalletService walletService){
        this.walletService = walletService;
    }

    @PostMapping("/v1/wallet")
    public ResponseEntity<Object>
    saveWallet( @RequestBody Wallets wallets) throws Exception {

        walletService.saveWallet(wallets);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @PostMapping("v1/wallet/bat")
    public ResponseEntity<Object>
    saveAllWallet(@Valid @RequestBody List<Wallets> listWallets) throws Exception {

        if(listWallets==null)throw new ResourceNotFoundException(ConstantMessage.ERROR_NO_CONTENT);
        walletService.saveAllWallet(listWallets);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE, HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/v1/wallet/datas/all")
    public ResponseEntity<List<Wallets>> getfindAll() {
        try {
            List<Wallets> lsWallets = walletService.findAllWallets();
            if (lsWallets.isEmpty()) {
                throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
            }
            return new ResponseEntity<>(lsWallets, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/v1/wallet/dto/datas/all")
    public ResponseEntity<Object> findAllWalletDTO()throws Exception{

        List<Wallets> lsWallets = walletService.findAllWallets();

        if(lsWallets.size()==0)
        {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<WalletDTO> lsWalletsDTO =  modelMapper.map(lsWallets, new TypeToken<List<WalletDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,lsWalletsDTO,null,null);
    }

    @PutMapping("/v1/wallet/update")
    public ResponseEntity<Object> updateWalletBynomorRekening(@RequestParam String nomorRekeningSumber,
                                                              @RequestParam String nomorRekeningTujuan,
                                                              @RequestParam Double value)throws Exception{
        walletService.updateWalletsBynomorRekening(nomorRekeningSumber, nomorRekeningTujuan, value);
        return new ResponseHandler().
                generateResponse(ConstantMessage.TRANSFER_SUCCESS,HttpStatus.OK,null,null,null);
    }

    @PostMapping("/v1/wallet/insert")
    public ResponseEntity<Object> saveWalletsQuery(@RequestBody Wallets wallets)throws Exception{
        walletService.saveWalletQuerys(wallets);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_INSERT_QUERY,HttpStatus.OK,null,null,null);
    }

}
