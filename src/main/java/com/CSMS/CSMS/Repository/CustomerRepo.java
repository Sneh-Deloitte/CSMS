package com.CSMS.CSMS.Repository;

import com.CSMS.CSMS.models.Customer;
import com.CSMS.CSMS.services.CustomerService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Long> {
}
