package com.CSMS.CSMS.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.CSMS.CSMS.Repository.MaintenanceRepo;
import com.CSMS.CSMS.Repository.StatusRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Maintenance;
import com.CSMS.CSMS.services.MaintenanceService;

@Service
public class MaintenanceImpl implements MaintenanceService{

    @Autowired
    private MaintenanceRepo maintenanceRepo;

    @Autowired
    private StatusRepo statusRepo;

    @Override
    public String getTicketNo(String chargeBoxName){
        return chargeBoxName+"-"+RandomString.getAlphaNumericString(6);
    }

    @Override
    public void addMaintenanceTicket(Maintenance maintenance){
        maintenanceRepo.save(maintenance);
    }

    @Override
    public String closeMaintenanceTicket(String ticketNo){
        Maintenance maintenance=maintenanceRepo.findByTicketNo(ticketNo);
        maintenance.setStatus("Closed");
        maintenanceRepo.save(maintenance);
        try{
        statusRepo.deleteByChargerBoxName(ticketNo.split("-",2)[0]);
    }    
        catch(Exception e){
            throw new NotFoundException("Data already deleted");
        }
        return "Done";
    }

    @Override
    public List<Maintenance> getAllMaintenanceTicket(){
        return maintenanceRepo.findAll();
    }
}
