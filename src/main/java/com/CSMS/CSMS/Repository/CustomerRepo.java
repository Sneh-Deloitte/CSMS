package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Customer;
import com.CSMS.CSMS.services.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
    @Query(value = "select count(*) from customer where customer_email = ?1 or customer_phone = ?2", nativeQuery = true)
    int countOfUsersByEmailOrMobile(String email, String mobile);
    @Query(value = "select * from customer where customer_email = ?1", nativeQuery = true)
    Customer findByCustomerEmail(String email);
}
