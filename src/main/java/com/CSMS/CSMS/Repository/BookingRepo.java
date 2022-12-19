package com.CSMS.CSMS.Repository;


import com.CSMS.CSMS.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Long> {

    @Query(value = "SELECT * FROM booking WHERE date=?1",nativeQuery = true)
    List<Booking> findBookingByDate(String date);

    @Query(value = "SELECT * FROM booking WHERE charger_id=?1",nativeQuery = true)
    List<Booking> findBookingByChargerId(int chargerId);

    @Query(value = "SELECT * FROM booking WHERE station_id=?1",nativeQuery = true)
    List<Booking> findBookingByStationId(int stationId);

    @Query(value = "SELECT * FROM booking WHERE charger_id=?1 and connector_id?=2 and date=?3",nativeQuery = true)
    List<Booking> getBookingByChargerIdDate(int chargerId, int connectorId, String date);

    @Query(value = "SELECT * FROM booking WHERE customer_id=?1 2",nativeQuery = true)
    List<Booking> findBookingByCustomerId(int customerId);



}
