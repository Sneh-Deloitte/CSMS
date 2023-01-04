package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.Repository.StatusRepo;
import com.CSMS.CSMS.models.Booking;
import com.CSMS.CSMS.models.Status;
import com.CSMS.CSMS.services.BookingService;
import com.CSMS.CSMS.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
public class StatusController {

    @Autowired
    private StatusService statusService;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private BookingRepo bookingRepo;

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

    @PostMapping("/statusNotification")
    public String statusNotification(@RequestBody HashMap<String, String> store){
        String[] dateTime=store.get("timestamp").split("T",2);
        String startDate=dateTime[0];
        String startTime=dateTime[1].substring(0, 5);
        System.out.println(startDate);
        System.out.println(startTime);
        String[] chargeBox=store.get("chargeBoxId").split(",",3);
        List<Booking> booking = bookingRepo.findBookingByStationIdChargerId(Integer.valueOf(chargeBox[0]),Integer.valueOf(chargeBox[1]));
        List<Booking> bookingWithDateTime=booking.stream().filter(c->c.getDate().compareTo(startDate)>=0 && c.getStart_time().compareTo(startTime)>=0).collect(Collectors.toList());
        System.out.println(bookingWithDateTime);
        return "errorCode";
    }
}
