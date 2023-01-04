package com.CSMS.CSMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.CSMS.CSMS.models.Maintenance;

@Repository
public interface MaintenanceRepo extends JpaRepository<Maintenance, Long> {
    
    @Query(value = "select * from maintenance where ticket_no=?1", nativeQuery = true)
    Maintenance findByTicketNo(String ticketNo);

}
