package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.models.ActiveReservation;
import com.CSMS.CSMS.models.Booking;
import com.CSMS.CSMS.services.ActiveReservationService;
import com.CSMS.CSMS.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
public class BookingController {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private BookingService bookingService;

    @Autowired
    private ActiveReservationService activeReservationService;

    @PostMapping("/createBooking")
    public Booking createBooking(@RequestBody Booking booking){
        // We will call central station api for reservation
        Booking booking1 = bookingService.createBooking(booking);

        ActiveReservation activeReservation = new ActiveReservation(booking1.getId());
        activeReservationService.addActiveReservation(activeReservation);

        return booking1;
    }

    @DeleteMapping("/deleteBooking/{id}")
    public String deleteBooking(@PathVariable long id){
        String response = bookingService.deleteBooking(id);
        return response;
    }
    @GetMapping("/getAllBookings")
    public List<Booking> getAllBookings(){
        return bookingService.getAllBookings();
    }
    @GetMapping("/getBookingById/{id}")
    public Optional<Booking> getBookingById(@PathVariable long id){
        return bookingService.getBookingById(id);
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

    @PutMapping("/updateBooking/{id}")
    public Booking updateBooking(@PathVariable long id,@RequestBody Booking booking)
    {
        return bookingService.updateBooking(id, booking);
    }
    @GetMapping("/getBookingByChargerIdDate")
    public ArrayList<List> bookingList(@RequestParam int chargerId, @RequestParam String date){
        return bookingService.getBookingByChargerIdDate(chargerId, date);
    }
    @GetMapping("/getBookingDetailByChargerIdDate")
    public List<Booking> bookingListt(@RequestParam int chargerId, @RequestParam String date){
        return bookingService.getBookingDetailByChargerIdDate(chargerId, date);
    }

}