package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.Repository.CustomerRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Customer;
import com.CSMS.CSMS.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerImpl implements CustomerService {
    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    @Override
    public Customer updateCustomerById(long id, Customer customer) {
        Customer customer1 = customerRepo.findById(id).orElseThrow(()-> new NotFoundException("Customer not found with id" +id));
        if(customer1.getCustomer_email() != null)customer1.setCustomer_email(customer.getCustomer_email());
        if(customer1.getCustomer_gender() != null)customer1.setCustomer_gender(customer.getCustomer_gender());
        if(customer1.getCustomer_phone() != 0)customer1.setCustomer_phone(customer.getCustomer_phone());
        if(customer1.getDob_customer() != 0)customer1.setDob_customer(customer.getDob_customer());
        if(customer1.getCustomer_nationality() != null)customer1.setCustomer_nationality(customer.getCustomer_nationality());
        if(customer1.getCustomer_firstName() != null)customer1.setCustomer_firstName(customer.getCustomer_firstName());
        if(customer1.getCustomer_lastName() != null)customer1.setCustomer_lastName(customer.getCustomer_lastName());
        return customerRepo.save(customer1);
    }

    @Override
    public Customer deleteCustomer(long id) {
        try {
            customerRepo.deleteById(id);
        } catch (Exception exception) {
            throw new NotFoundException("user not found with id" + id);
        }

        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getCustomerById(long id) {
        Customer customer = customerRepo.findById(id).orElseThrow(() -> new NotFoundException("charger not found" + id));
        return customer;
    }
}
