package com.CSMS.CSMS.Controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CSMS.CSMS.services.TransactionService;

@CrossOrigin(origins = "*")
@RestController
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/getStartTransaction")
    public void startTransaction(@RequestBody HashMap<String, String> store){
        transactionService.saveStartTransaction(store);
    }
    @GetMapping("/getStopTransaction")
    public void stopTransaction(@RequestBody HashMap<String, String> store){
        transactionService.saveStartTransaction(store);
    }
    
}
