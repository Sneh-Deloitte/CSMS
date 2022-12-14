package com.CSMS.CSMS.Controllers;


import com.CSMS.CSMS.Repository.CustomerRepo;
import com.CSMS.CSMS.models.ActiveReservation;
import com.CSMS.CSMS.models.Customer;
import com.CSMS.CSMS.services.ActiveReservationService;
import com.CSMS.CSMS.services.CustomerService;

import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

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

    @PostMapping("/infinte")
    public void infinity(){

        Timer timer = new Timer ();
        timer.schedule (hourlyTask, 0l, 20000);
    }


    TimerTask hourlyTask = new TimerTask () {
        @Override
        public void run () {
           System.out.println("Running in every 20 seceonds");

           // fetch all the data in active reservation table
            // if the timestamp match to current
                //-> hit the steve api
                //
            List<ActiveReservation> listActiveReservation= activeReservationService.getAllActiveReservation();
            for(int i=0;i<listActiveReservation.size(); i++){

                System.out.println(activeReservationService.getActiveStatus(listActiveReservation.get(i)));
            }

        }
    };
}
