package com.CSMS.CSMS.Controllers;


import com.CSMS.CSMS.Repository.CustomerRepo;
import com.CSMS.CSMS.models.ActiveReservation;
import com.CSMS.CSMS.models.Customer;
import com.CSMS.CSMS.services.ActiveReservationService;
import com.CSMS.CSMS.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private ActiveReservationService activeReservationService;

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable long id){
        return customerService.getCustomerById(id);
    }

    @GetMapping("/GetAllCustomers")
    public List<Customer> getAllCustomers(){
        return customerService.getAllCustomers();
    }

    @DeleteMapping("/deleteCustomers/{id}")
    public Customer deleteCustomer(@PathVariable long id){
        return customerService.deleteCustomer(id);
    }

    @PutMapping("/updateCustomer/{id}")
    public Customer updateCustomerById(@PathVariable long id,@RequestBody Customer customer){
        return customerService.updateCustomerById(id, customer);
    }

    @PostMapping("/createCustomer")
    public Customer createCustomer(@RequestBody Customer customer){
        return customerService.createCustomer(customer);
    }
}
