package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.models.Status;
import com.CSMS.CSMS.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;

    @GetMapping("/status/all")
    public List<Status> getAllStatus()
    {
        return statusService.getAllStatus();
    }

    // @GetMapping("/status/{id}")
    // public Status getStatusById(@PathVariable Integer id)
    // {
    //     return statusService.getStatus(id);
    // }

    @PostMapping("/status/add")
    public Status addStatus(@RequestBody Status status)
    {
        return statusService.addStatus(status);
    }


    // @PutMapping("/status/{id}")
    // public Status udpateStatus(@PathVariable Integer id, @RequestBody Status status)
    // {
    //     return statusService.updateStatus(id, status);
    // }

    // @DeleteMapping("/status/{id}")
    // public ResponseEntity<HttpStatus> deleteStatus(@PathVariable Integer id){
    //     statusService.deleteStatus(id);
    //     return new ResponseEntity<>(HttpStatus.OK);
    // }

    @PostMapping("/statusNotification")
    public String statusNotification(@RequestBody HashMap<String, String> store){
        return statusService.statusNotification(store);
    }
}
