package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.Repository.StatusRepo;
import com.CSMS.CSMS.models.Status;
import com.CSMS.CSMS.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;
    @Autowired
    private StatusRepo statusRepo;

    @GetMapping("/status/all")
    public List<Status> getAllStatus()
    {
        return statusService.getAllStatus();
    }

    @GetMapping("/status/{id}")
    public Status getStatusById(@PathVariable Integer id)
    {
        return statusService.getStatus(id);
    }

    @PostMapping("/status/add")
    public Status addStatus(@RequestBody Status status)
    {
        System.out.println("********************************************");
        return statusService.addStatus(status);
    }


    @PutMapping("/status/{id}")
    public Status udpateStatus(@PathVariable Integer id, @RequestBody Status status)
    {
        return statusService.updateStatus(id, status);
    }

    @DeleteMapping("/status/{id}")
    public ResponseEntity<HttpStatus> deleteStatus(@PathVariable Integer id){
        statusService.deleteStatus(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }



}
