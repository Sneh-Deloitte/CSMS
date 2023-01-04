package com.CSMS.CSMS.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.CSMS.CSMS.models.Maintenance;
import com.CSMS.CSMS.services.MaintenanceService;

@CrossOrigin(origins = "*")
@RestController
public class MaintenanceController {

    @Autowired
    private MaintenanceService maintenanceService;

    @GetMapping("/getTicketNo/{chargeBoxName}")
    private String getTicketNo(@PathVariable String chargeBoxName){
        return maintenanceService.getTicketNo(chargeBoxName);
    }

    @PostMapping("/addMaintenanceTicket")
    private void addMaintenanceTicket(@RequestBody Maintenance maintenance){
        maintenanceService.addMaintenanceTicket(maintenance);
    }
    
    @PostMapping("/closeMaintenanceTicket/{ticketNo}")
    private void closeMaintenanceTicket(@PathVariable String ticketNo){
        maintenanceService.closeMaintenanceTicket(ticketNo);
    }

    @PostMapping("/getAllMaintenanceTicket")
    private List<Maintenance> getAllMaintenanceTicket(){
        return maintenanceService.getAllMaintenanceTicket();
    }
}
