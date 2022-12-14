package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.Repository.ChargerRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Charger;
import com.CSMS.CSMS.models.Station;
import com.CSMS.CSMS.services.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChargerImpl implements ChargerService {

    @Autowired
    private ChargerRepo chargerRepo;


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
        return chargerRepo.save(charger);
    }


}
