package com.CSMS.CSMS.services.impl;
import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Booking;
import com.CSMS.CSMS.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
@Service
public class BookingImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;
    @Override
    public Booking createBooking(Booking booking) {

        // Rest api call for central system to follow ocpp protocol-> Reserve Now

        try {
            String[] start = booking.getStart_time().split(":", 2);
            String[] end = booking.getEnd_time().split(":", 2);
            String[] date= booking.getDate().split("/",3);
            if (date[0].length()!=2 || date[1].length()!=2 || date[2].length()!=4 || booking.getDate().length()!=10){
                throw new NotFoundException("Date isn't as expected");
            }
            String booking_id =booking.setBooking_id(RandomString.getAlphaNumericString(8));
            if (start[0].length()!=2 || start[1].length()!=2 || end[0].length()!=2 || end[1].length()!=2){
                throw new NotFoundException("Start time and end time value isn't as expected");
            }
            int start_hour = Integer.parseInt(start[0]);
            int end_hour = Integer.parseInt(end[0]);
            int day = Integer.parseInt(date[0]);
            int month = Integer.parseInt(date[1]);
            int year = Integer.parseInt(date[2]);
            if (start_hour<24 && end_hour<24) {
                if (start_hour < end_hour) {
                    return bookingRepo.save(booking);
                }
            }
            throw new NotFoundException("Data as expected");
        }
        catch (NotFoundException e){
            throw new NotFoundException(e.getMessage());
        }
        catch (Exception e){
            throw new NotFoundException(e.getMessage());
        }

    }
    @Override
    public Booking updateBooking(long id, Booking booking) {
        Booking booking1 = bookingRepo.findById(id).orElseThrow(()-> new NotFoundException("Charger is not found with id" +id));
        if(booking.getCost() != 0) booking1.setCost(booking.getCost());
        if(booking.getEnd_time() != "") booking1.setEnd_time(booking.getEnd_time());
        if(booking.getPayment_mode() != null) booking1.setPayment_mode(booking.getPayment_mode());
        if(booking.getPayment_status() != 0) booking1.setPayment_status(booking.getPayment_status());
        if(booking.getStart_time() != null) booking1.setStart_time(booking.getStart_time());
        if(booking.getDate() != null) booking1.setDate(booking.getDate());
        return bookingRepo.save(booking1);
    }

    @Override
    public String deleteBooking(long id) {

        // Rest api call for central system to follow ocpp protocol-> Cancel Reservation

        try {
            bookingRepo.deleteById(id);
            return ("Deleted booking with id: "+id) ;
        }
        catch (Exception e){
            return ("No booking found with id: " +id);
        }

    }

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepo.findAll();
    }

    @Override
    public Optional<Booking> getBookingById(long id) {
        return bookingRepo.findById(id);
    }

    @Override
    public List<Booking> getBookingByDate(String date){
        String[] dateNew= date.split("/",3);
        if (dateNew[0].length()!=2 || dateNew[1].length()!=2 || dateNew[2].length()!=4 || date.length()!=10){
            throw new NotFoundException("Start time and end time value isn't as expected");
        }
        return bookingRepo.findBookingByDate(date);
    }
    @Override
    public List<Booking> getBookingByChargerId(int chargerId){
        return bookingRepo.findBookingByChargerId(chargerId);
    }

    @Override
    public ArrayList<List> getBookingByChargerIdDate(int chargerId, String date) {
        List<Booking> list = bookingRepo.getBookingByChargerIdDate(chargerId,date);
        ArrayList<List> L = new ArrayList<>();
        for(int i = 0; i < list.size(); i ++){
            Booking b = list.get(i);
            String startTime = b.getStart_time();
            String endTime = b.getEnd_time();
            List<String> list1 = new ArrayList<>();
            list1.add(startTime);
            list1.add(endTime);
            L.add(list1);
        }
        return L;
    }

    @Override
    public List<Booking> getBookingDetailByChargerIdDate(int chargerId, String date) {
        return bookingRepo.getBookingByChargerIdDate(chargerId, date);
    }


    @Override
    public List<Booking> getBookingByStationId(int stationID){
        return bookingRepo.findBookingByStationId(stationID);
    }




}