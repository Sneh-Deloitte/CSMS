package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.Repository.ActiveReservationRepo;
import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.models.ActiveReservation;
import com.CSMS.CSMS.models.Booking;
import com.CSMS.CSMS.services.ActiveReservationService;
import com.CSMS.CSMS.services.BookingService;

import aj.org.objectweb.asm.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ActiveReservationImpl implements ActiveReservationService {

    @Autowired
    private ActiveReservationRepo activeReservationRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Override
    public List<ActiveReservation> getAllActiveReservation() {
        System.out.println(getCurrentTime());
        return  activeReservationRepo.findAll();
    }

    @Override
    public ActiveReservation addActiveReservation(ActiveReservation activeReservation) {
        return activeReservationRepo.save(activeReservation);
    }

    @Override
    public String deleteActiveReservation(Long id) {

        try {
            activeReservationRepo.deleteById(id);
            return ("Deleted booking with id: "+id) ;
        }
        catch (Exception e){
            return ("No booking found with id: " +id);
        }
    }

    @Override
    public boolean getActiveStatus(ActiveReservation activeReservation) {
        Booking booking = bookingRepo.getById(activeReservation.getId());

        String startTime = booking.getEnd_time();
        String date = booking.getDate();
        String currentTime = getCurrentTime();
        String finalDateAccessed=date+" "+startTime+":00";
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/uuuu HH:mm:ss" );
        LocalDateTime currentDate = LocalDateTime.parse( currentTime , f ) ;
        LocalDateTime storedDate = LocalDateTime.parse( finalDateAccessed , f ) ;
        // if(currentDate.compareTo(storedDate)>0){
        //     String url="http://127.0.0.1:9000/steve/hello";
        //     RestTemplate restTemplate= new RestTemplate();
        //     Object countries= restTemplate.postForObject(url, restTemplate, Object.class);
        //     return true;
        // }
        if(currentDate.compareTo(storedDate)>=0){
            Long id=(long) 4;
            deleteActiveReservation(id);
            System.out.println(getAllActiveReservation());
        }
        return false;
    }


    private String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
}
