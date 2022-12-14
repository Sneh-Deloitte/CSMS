package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.ConsumeAPI.ApiService;
import com.CSMS.CSMS.ConsumeAPI.dto.Address;
import com.CSMS.CSMS.ConsumeAPI.dto.ChargePointForm;
import com.CSMS.CSMS.Repository.BookingRepo;
import com.CSMS.CSMS.Repository.ChargerRepo;
import com.CSMS.CSMS.Repository.StationRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Booking;
import com.CSMS.CSMS.models.Charger;
import com.CSMS.CSMS.services.BookingService;
import com.CSMS.CSMS.services.ChargerService;
import com.CSMS.CSMS.services.StationService;
import com.neovisionaries.i18n.CountryCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ChargerImpl implements ChargerService {

    @Autowired
    private ChargerRepo chargerRepo;

    @Autowired
    private BookingRepo bookingRepo;

    @Autowired
    private StationService stationservice;

    @Autowired
    private ApiService apiService;

    @Autowired
    private BookingService bookingService;

    @Override
    public List<Charger> getChargerByStationId(long id) {
        return  chargerRepo.getChargerByStationId(id);
    }

    @Override
    public Charger getChargerById(long id) {
        return chargerRepo.findById(id).orElseThrow(() -> new NotFoundException("Charger not found with id" + id));
    }

    @Override
    public Charger updateChargerById(long id, Charger charger) {
        Charger charger1 = chargerRepo.findById(id).orElseThrow(() -> new NotFoundException("Charger not found with id" + id));
        if(charger.getCharger_name() != null) charger1.setCharger_name(charger.getCharger_name());
        if(charger.getStation_id() != 0) charger1.setStation_id(charger.getStation_id());
        if(charger.getCharger_op() != null) charger1.setCharger_op(charger.getCharger_op());
        if(charger.getCharger_machine_id() != 0) charger1.setCharger_machine_id(charger.getCharger_machine_id());
        if(charger.getBooking_rate() != 0) charger1.setBooking_rate(charger.getBooking_rate());
        if(charger.getCharger_type() != null) charger1.setCharger_type(charger.getCharger_type());
        if(charger.getCharger_status_id() != 0) charger1.setCharger_status_id(charger.getCharger_status_id());
        if(charger.getCharging_rate() != 0) charger1.setCharging_rate(charger.getCharging_rate());
        return chargerRepo.save(charger1);
    }

    @Override
    public void deleteChargerById(long id) {
        try {
            chargerRepo.deleteById(id);
        } catch (Exception exception) {
            throw new NotFoundException("user not found with id" + id);
        }
    }

    @Override
    public Charger addCharger(Charger charger) {
        if (chargerRepo.findAll().size()<=5){
            Integer count=chargerRepo.countChargerByStationId(charger.getStation_id());
            charger.setCharger_name(String.valueOf(charger.getStation_id())+","+String.valueOf(count+1)+","+charger.getCharger_name());
            stationservice.getChargingStationById(charger.getStation_id());
            ChargePointForm chargePointForm = new ChargePointForm();
            BigDecimal lonlat = new BigDecimal(0.0);
            Address address = new Address();
            address.setCity(stationservice.getChargingStationById(charger.getStation_id()).getCity());
            address.setStreet(stationservice.getChargingStationById(charger.getStation_id()).getStreet());
            address.setHouseNumber(stationservice.getChargingStationById(charger.getStation_id()).getHouseNumber());
            String countryCode=stationservice.getChargingStationById(charger.getStation_id()).getCountry().toUpperCase().substring(0,2);
            address.setCountry(CountryCode.getByAlpha2Code(countryCode));
            address.setZipCode(stationservice.getChargingStationById(charger.getStation_id()).getZipCode());

            chargePointForm.setChargeBoxId(charger.getCharger_name());
            chargePointForm.setRegistrationStatus("Accepted");
            chargePointForm.setInsertConnectorStatusAfterTransactionMsg(true);
            chargePointForm.setAddress(address);
            chargePointForm.setLocationLatitude(lonlat);
            chargePointForm.setLocationLongitude(lonlat);
            chargePointForm.setDescription("Description");
            chargePointForm.setNote("Registred by CSMS");
            chargePointForm.setAdminAddress("https://hashedin.com/");
            apiService.addCharger(chargePointForm);

            return chargerRepo.save(charger);
        }
        else{
            throw new NotFoundException("Already 5 chargers in the station");
        }
    }

    @Override
    public String outOfService(String chargerName){
        String[] chargerDetails=chargerName.split(",",3);
        Integer chargerId= Integer.parseInt(chargerDetails[1]);
        List<Booking> list=bookingRepo.findBookingByChargerId(chargerId);
        for(Booking b:list){
            bookingService.cancelBooking(b.getId());
        }
        return chargerName;
    }
}
