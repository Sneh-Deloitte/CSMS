package com.CSMS.CSMS.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import com.CSMS.CSMS.Repository.TransactionRepo;
import com.CSMS.CSMS.models.Transaction;
import com.CSMS.CSMS.services.TransactionService;

@Service
public class TransactionImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public void addTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
    }

    @Override
    public void updateTransaction(HashMap<String, String> store) {
        List<Transaction> newTransaction=transactionRepo.getTransactionByIdTag(store.get("idTag"));
        List<Transaction> finalListOfTransaction=newTransaction.stream().filter(c-> c.getStatusOfTransaction().equals("1")).collect(Collectors.toList());
        Transaction transaction=finalListOfTransaction.get(0);
        transaction.setStopTimestamp(DateTime.parse(store.get("timestamp")));
        transaction.setStopReason(store.get("stopReason"));
        transaction.setMeterStop(Integer.parseInt(store.get("MeterStop")));
        transaction.setStatusOfTransaction("0");
        transactionRepo.save(transaction);
    }
    
    @Override
    public void saveStartTransaction(HashMap<String, String> store){
        if(store.get("statusOfTransaction").equals("1")){
            addTransaction(new Transaction(store.get("chargeBoxIdentity"),Integer.parseInt(store.get("connectorId")),store.get("idTag"), DateTime.parse(store.get("timestamp")), Integer.parseInt(store.get("MeterStart")),Integer.parseInt(store.get("reservationId")),store.get("statusOfTransaction"),null,null,null));
        }
        else{
            updateTransaction(store);
        }
    }
}
