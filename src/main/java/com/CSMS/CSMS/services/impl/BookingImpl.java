package com.CSMS.CSMS.services.impl;
import com.CSMS.CSMS.ConsumeAPI.ApiService;
import com.CSMS.CSMS.ConsumeAPI.dto.ReservationResponse;
import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.Repository.ChargerRepo;
import com.CSMS.CSMS.Repository.CustomerRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Booking;
import com.CSMS.CSMS.models.Charger;
import com.CSMS.CSMS.models.Customer;
import com.CSMS.CSMS.services.BookingService;
import com.CSMS.CSMS.services.CustomerService;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
@Service
public class BookingImpl implements BookingService {

    @Autowired
    private BookingRepo bookingRepo;
    @Autowired
    private ApiService apiService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private ChargerRepo chargerRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Override
    public Booking createBooking(Booking booking) {
        try {
            if(booking.getConnector_id()!=1 && booking.getConnector_id()!=2){
                throw new NotFoundException("Connector_Id isn't as expected");
            }
            String[] start = booking.getStart_time().split(":", 2);
            String[] end = booking.getEnd_time().split(":", 2);
            String[] date= booking.getDate().split("-",3);
            customerService.getCustomerById(booking.getCustomer_id());
            if (date[0].length()!=4 || date[1].length()!=2 || date[2].length()!=2 || booking.getDate().length()!=10){
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
                    booking.setBookingStatus("Accepted");
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
    public String cancelBooking(long id){
        try{
            Booking booking = bookingRepo.getById(id);
            booking.setBookingStatus("Cancelled");
            bookingRepo.save(booking);
            String startTime = booking.getStart_time();
            String date = booking.getDate();
            String currentTime = getCurrentTime();
            String finalDateAccessed=date+" "+startTime+":00";
            // System.out.println(getCurrentTime());
            DateTimeFormatter f = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss" );
            DateTimeFormatter g = DateTimeFormatter.ofPattern( "uuuu/MM/dd HH:mm:ss" );
            LocalDateTime currentDate = LocalDateTime.parse( currentTime , g ) ;
            LocalDateTime storedDate = LocalDateTime.parse( finalDateAccessed , f ) ;
        if(currentDate.compareTo(storedDate)>=0){
                cancelReservation(id);
            }
            return "Done";
        }
        catch(Exception e){
            return "No booking with the provided booking id found";
        }
    }

    private String getCurrentTime(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }
    
    public String cancelReservation(Long id){
        Booking booking = bookingRepo.getById(id);
        Charger charger= chargerRepo.getById((long)booking.getCharger_id());
        Customer customer =customerRepo.getById((long) booking.getCustomer_id());
        String ocppTagOfCustomer=customer.getOcpp_tag();
        List<ReservationResponse> getallreservationofocpptag=apiService.getAllReservationOfOcppTag(ocppTagOfCustomer);
            String EndTime= booking.getDate()+"T"+booking.getEnd_time()+":00.000Z";
            for(int i=0; i<getallreservationofocpptag.size(); i++){
                ReservationResponse reservationResponse= getallreservationofocpptag.get(i);
                if (reservationResponse.getConnectorId()==booking.getConnector_id() && reservationResponse.getExpiryDatetime().equals(EndTime)){
                    // We got booking which needs to get cancelled and got all the reservations in steve. Booking(customerId).to ocppTag.
                    HashMap<String,String> store = new HashMap<>();
                    store.put("chargerName",charger.getCharger_name());
                    store.put("reservationId",String.valueOf(reservationResponse.getReservationId()));
                    String getResult= apiService.cancelReservation(store);
                    break;
                }
            }
            return "Done";
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
        String[] dateNew= date.split("-",3);
        if (dateNew[0].length()!=4 || dateNew[1].length()!=2 || dateNew[2].length()!=2 || date.length()!=10){
            throw new NotFoundException("Start time and end time value isn't as expected");
        }
        return bookingRepo.findBookingByDate(date);
    }
    @Override
    public List<Booking> getBookingByChargerId(int chargerId){
        return bookingRepo.findBookingByChargerId(chargerId);
    }

    @Override
    public ArrayList<List> getBookingByChargerIdDate(int chargerId, int connector_id, String date) {
       List<Booking> list = bookingRepo.getBookingByChargerIdDate(chargerId,date);
        ArrayList<List> L = new ArrayList<>();
        for(int i = 0; i < list.size(); i ++){
            Booking b = list.get(i);
            if(connector_id==b.getConnector_id())
            {
                String startTime = b.getStart_time();
                String endTime = b.getEnd_time();
                List<String> list1 = new ArrayList<>();
                list1.add(startTime);
                list1.add(endTime);
                L.add(list1);
            }
        }
        return L;
    }
    @Override
    public List<Booking> getBookingDetailByChargerIdDate(int chargerId, int connector_id, String date) {
        List<Booking> list = bookingRepo.getBookingByChargerIdDate(chargerId,date);
        List<Booking> L = new ArrayList<>();
        for(int i = 0; i < list.size(); i ++){
            Booking b = list.get(i);
            if(connector_id==b.getConnector_id())
            {
                L.add(b);
            }
        }
        return L;
    }
    @Override
    public List<Booking> getBookingByStationId(int stationID){
        return bookingRepo.findBookingByStationId(stationID);
    }
    @Override
    public List<Booking> getBookingByCustomerId(int customerId){
        return bookingRepo.findBookingByCustomerId(customerId);
    }
}