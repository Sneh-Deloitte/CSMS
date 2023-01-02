package com.CSMS.CSMS.services;

import com.CSMS.CSMS.models.Charger;

import java.util.List;


public interface ChargerService {

    public List<Charger> getChargerByStationId(long id);

    public Charger getChargerById(long id);

    public Charger updateChargerById(long id, Charger charger);

    public void deleteChargerById(long id);

    public Charger addCharger(Charger charger);

    public String outOfService(String chargerName);

}

