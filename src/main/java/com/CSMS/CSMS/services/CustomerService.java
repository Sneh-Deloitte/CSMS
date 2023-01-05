package com.CSMS.CSMS.services;

import com.CSMS.CSMS.models.Customer;

import java.util.List;

public interface CustomerService {

    // public Customer createCustomer(Customer customer);
    
    public Customer updateCustomerById(long id, Customer customer);
    
    public Customer deleteCustomer(long id);
    
    public List<Customer> getAllCustomers();
    
    public Customer getCustomerByMailId(String id);

}
