package com.CSMS.CSMS.services;

import com.CSMS.CSMS.models.Station;
import com.CSMS.CSMS.models.StationInRange;

import java.util.List;

public interface StationService {

    public Station addChargingStation(Station station);
    
    public Station updateChargingStationById(long id, Station station);

    public void deleteChargingStation(long id);

    public List<Station> getAllChargingStations();

    public List<Station> getAllChargingStationsInRange(StationInRange stationInRange);

    public Station getChargingStationById(long id);

    public List<Station> getStationByMail(String mail);
}
