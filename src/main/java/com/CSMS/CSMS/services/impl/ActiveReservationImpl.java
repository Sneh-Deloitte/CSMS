package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.ConsumeAPI.ApiService;
import com.CSMS.CSMS.ConsumeAPI.dto.ChargePointForm;
import com.CSMS.CSMS.Repository.ActiveReservationRepo;
import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.Repository.ChargerRepo;
import com.CSMS.CSMS.Repository.CustomerRepo;
import com.CSMS.CSMS.models.ActiveReservation;
import com.CSMS.CSMS.models.Booking;
import com.CSMS.CSMS.models.Charger;
import com.CSMS.CSMS.models.Customer;
import com.CSMS.CSMS.services.ActiveReservationService;
import com.CSMS.CSMS.services.BookingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;


@Service
public class ActiveReservationImpl implements ActiveReservationService {

    @Autowired
    private  ApiService apiService;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ActiveReservationRepo activeReservationRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private ChargerRepo chargerRepo;

    @Autowired
    private CustomerRepo customerRepo;


    @Override
    public List<ActiveReservation> getAllActiveReservation() {
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
        //find current time stamp and check if it is equal to or greater to current time.
        Booking booking = bookingRepo.getById(activeReservation.getId());

        String startTime = booking.getStart_time();
        String date = booking.getDate();
        String currentTime = getCurrentTime();

        String finalDateAccessed=date+" "+startTime+":00";
        System.out.println(getCurrentTime());
        System.out.println(date+" "+startTime+ "----------"+ currentTime+"--------"+booking.getBooking_id());
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" );
        DateTimeFormatter g = DateTimeFormatter.ofPattern( "uuuu/MM/dd HH:mm:ss" );
        LocalDateTime currentDate = LocalDateTime.parse( currentTime , g ) ;
        LocalDateTime storedDate = LocalDateTime.parse( finalDateAccessed , f ) ;

        if(currentDate.compareTo(storedDate)>=0){
            callSteveReservation(booking);
            Long id=activeReservation.getId();
            deleteActiveReservation(id);
            return  true;
        }
        return false;
    }

    private String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    private void callSteveReservation(Booking booking){
                // We will call central station api for reservation
        String expiryTime=booking.getDate()+"T"+booking.getEnd_time()+":00";

        //get chargerIdname from chargetable
        Charger charger = chargerRepo.getById((long) booking.getCharger_id());

        //get tag from custometable
        Customer customer =customerRepo.findByCustomerEmail(booking.getCustomerMailId());
        HashMap<String,String> store = new HashMap<>();
        store.put("chargerName",charger.getCharger_name());
        store.put("customerTag",customer.getOcpp_tag());
        store.put("expiryTime",expiryTime);
        store.put("connectorId",String.valueOf(booking.getConnector_id()));

        // Calling steve api
        String getResult= apiService.addReservation(store);

        // Cancel Reservation
        if (booking.getBookingStatus().equals("Cancelled")){
            bookingService.cancelReservation(booking.getId());
        }
    }

}
