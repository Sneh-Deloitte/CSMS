package com.CSMS.CSMS.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.CSMS.CSMS.models.Maintenance;


public interface MaintenanceService {

    public String getTicketNo(String chargeBoxName);

    public void addMaintenanceTicket(Maintenance maintenance);

    public String closeMaintenanceTicket(String ticketNo);

    public List<Maintenance> getAllMaintenanceTicket();

}
