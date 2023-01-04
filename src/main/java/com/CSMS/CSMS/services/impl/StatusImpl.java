package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.Repository.StatusRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Booking;
import com.CSMS.CSMS.models.Status;
import com.CSMS.CSMS.services.BookingService;
import com.CSMS.CSMS.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusImpl implements StatusService {

    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private BookingService bookingService;

    @Override
    public List<Status> getAllStatusByStationId(int stationId) {
        List<Status> allStatus=statusRepo.findAll();
        List<Status> allStatusByStationId=allStatus.stream().filter(c->c.getChargeBoxIdName().split(",",3)[0].equals(String.valueOf(stationId))).collect(Collectors.toList());
        return allStatusByStationId;
    }

    @Override
    public Status addStatus(Status status) {
        return statusRepo.save(status);
    }

    // @Override
    // public void deleteStatus(Integer id) {
    //     try {
    //         statusRepo.deleteById(id);
    //     } catch (Exception exception) {
    //         throw new NotFoundException("Status not found with id " + id);
    //     }
    // }

    @Override
    public Status updateStatus(Status status) {
            Status status1 = statusRepo.getStatusByChargeBoxId(status.getChargeBoxIdName()).get();
            status1.setErrorCode(status.getErrorCode());
            return statusRepo.save(status1);
    }

    // @Override
    // public Status getStatus(Integer id) {
    //     return statusRepo.findById(id).orElseThrow(() -> new NotFoundException("status not found with id "  + id));
    // }

    @Override
    public String statusNotification(HashMap<String, String> store){
        if(store.get("errorCode").equals("HighTemperature")){
            String[] dateTime=store.get("timestamp").split("T",2);
            String startDate=dateTime[0];
            String startTime=dateTime[1].substring(0, 5);
            String[] chargeBox=store.get("chargeBoxId").split(",",3);
            List<Booking> booking = bookingRepo.findBookingByStationIdChargerId(Integer.valueOf(chargeBox[0]),Integer.valueOf(chargeBox[1]));
            List<Booking> bookingWithDateTime=booking.stream().filter(c->
                c.getDate().compareTo(startDate)>0 || (c.getDate().compareTo(startDate)==0 && c.getStart_time().compareTo(startTime)>=0)).collect(Collectors.toList());
            bookingWithDateTime.forEach(c->bookingService.cancelBooking(c.getId()));
        }
        if(statusRepo.getStatusByChargeBoxId(store.get("chargeBoxId")).isPresent()){
            updateStatus(new Status(store.get("errorCode"),store.get("chargeBoxId")));
        }
        else{
            addStatus(new Status(store.get("errorCode"),store.get("chargeBoxId")));
        }
        return store.get("errorCode");
    }
}
