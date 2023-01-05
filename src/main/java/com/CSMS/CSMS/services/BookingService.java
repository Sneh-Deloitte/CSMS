package com.CSMS.CSMS.services;
import com.CSMS.CSMS.models.Booking;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Date;
public interface BookingService {

    public Booking createBooking(Booking booking);
    public Booking updateBooking(long id, Booking booking);
    public String deleteBooking(long id);
    public String cancelBooking(long id);
    public List<Booking> getAllBookings();
    public List<Booking> getBookingByMailId(String mailId);
    public List<Booking> getBookingByDate(String date);
    public List<Booking> getBookingByStationId(int stationId);
    public List<Booking> getBookingByChargerId(int chargerId);
    public List<Booking> getBookingByCustomerId(int customerId);

    public ArrayList<List> getBookingByChargerIdDate(int chargerId, int connector_id, String date);
    public String cancelReservation(Long id);

    // GetBookingDetailByChargerId and Date Returns Booking Detail

    public List<Booking> getBookingDetailByChargerIdDate(int chargerId,int connector_id, String date);

}