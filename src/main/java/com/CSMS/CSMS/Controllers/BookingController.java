package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.ConsumeAPI.ApiService;
import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.Repository.ChargerRepo;
import com.CSMS.CSMS.Repository.ConnectorRepo;
import com.CSMS.CSMS.Repository.CustomerRepo;
import com.CSMS.CSMS.models.*;
import com.CSMS.CSMS.services.ActiveReservationService;
import com.CSMS.CSMS.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@CrossOrigin(origins = "*")
@RestController
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ActiveReservationService activeReservationService;

    @PostMapping("/createBooking")
    public Booking createBooking(@RequestBody Booking booking){
                //store in csms database
                Booking booking1 = bookingService.createBooking(booking);

                //to store in active reservation
                ActiveReservation activeReservation = new ActiveReservation(booking1.getId());
                activeReservationService.addActiveReservation(activeReservation);
                return  booking1;
    }

    @DeleteMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable long id){
        String response = bookingService.deleteBooking(id);
        return response;
    }

    @PostMapping("/cancelBooking/{id}")
    public String cancelBooking(@PathVariable long id){
        String response = bookingService.cancelBooking(id);
        return response;
    }
    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }
    @GetMapping("/getBookingById/{mailId}")
    public List<Booking> getBookingByMailId(@PathVariable String mailId){
        return bookingService.getBookingByMailId(mailId);
    }

    @GetMapping("/getBookingByDate")
    public List<Booking> dateBooking(@RequestParam String date){
        return bookingService.getBookingByDate(date);
    }
    @GetMapping("/getBookingByStationId")
    public List<Booking> stationIdBooking(@RequestParam int stationId){
        return bookingService.getBookingByStationId(stationId);
    }
    @GetMapping("/getBookingByChargerId")
    public List<Booking> chargerIdBooking(@RequestParam int chargerId){
        return bookingService.getBookingByChargerId(chargerId);
    }
    // @GetMapping("/getBookingByCustomerId")
    // public List<Booking> customerIdBooking(@RequestParam int customer_id){
    //     return bookingService.getBookingByCustomerId(customer_id);
    // }

    @PutMapping("/updateBooking/{id}")
    public Booking updateBooking(@PathVariable long id,@RequestBody Booking booking)
    {
        return bookingService.updateBooking(id, booking);
    }
    @GetMapping("/getBookingByChargerIdDate")
    public ArrayList<List> bookingList(@RequestParam int chargerId,@RequestParam int connector_id, @RequestParam String date){
        return bookingService.getBookingByChargerIdDate(chargerId,connector_id, date);
    }
    @GetMapping("/getBookingDetailByChargerIdDate")
    public List<Booking> bookingListt(@RequestParam int chargerId,@RequestParam int connectorId, @RequestParam String date){
        return bookingService.getBookingDetailByChargerIdDate(chargerId, connectorId,date);
    }

}