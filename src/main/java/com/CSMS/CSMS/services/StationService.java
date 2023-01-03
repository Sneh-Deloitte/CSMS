package com.CSMS.CSMS.services;

import com.CSMS.CSMS.models.Station;
import com.CSMS.CSMS.models.StationInRange;

import java.util.List;

public interface StationService {

    public List<Station> getAllChargingStations();

    public List<Station> getAllChargingStationsInRange(StationInRange stationInRange);

    public Station getChargingStationById(long id);

    // public Station updateChargingStationById(long id, Station station);

    // public void deleteChargingStation(long id);

    public Station addChargingStation(Station station);

    public List<Station> getStationByMail(String mail);
}
