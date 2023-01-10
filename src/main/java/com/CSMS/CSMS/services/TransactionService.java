package com.CSMS.CSMS.services;

import java.util.HashMap;

import com.CSMS.CSMS.models.Transaction;

public interface TransactionService {

    public void addTransaction(Transaction transaction);

    public void updateTransaction(HashMap<String, String> store);

    public void saveStartTransaction(HashMap<String, String> store);
    
}
