package com.CSMS.CSMS.Controllers;


import com.CSMS.CSMS.Repository.StationRepo;
import com.CSMS.CSMS.models.Charger;
import com.CSMS.CSMS.models.Station;
import com.CSMS.CSMS.models.StationInRange;
import com.CSMS.CSMS.services.ChargerService;
import com.CSMS.CSMS.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StationController {

    @Autowired
    private StationService stationService;
    @Autowired
    private ChargerService chargerService;
    @Autowired
    private StationRepo stationRepo;

    @GetMapping("/station/all")
    public List<Station> getAllChargingStations()
    {
        return stationService.getAllChargingStations();
    }

    @GetMapping("/station/{id}")
    public Station getChargingStationById(@PathVariable long id)
    {
        return stationService.getChargingStationById(id);
    }

    @PostMapping("/station/add")
    public Station addChargingStationById(@RequestBody Station station) {
        return stationService.addChargingStation(station);
    }

    @PutMapping("/station/{id}")
    public Station updateChargingStationById(@PathVariable long id, @RequestBody Station station)
    {
        return stationService.updateChargingStationById(id, station);
    }

    @DeleteMapping("/station/{id}")
    public ResponseEntity<HttpStatus> deleteChargingStation(@PathVariable long id){
        stationService.deleteChargingStation(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/stationInRange")
    public List<Station> getStationInRange(@RequestBody StationInRange stationInRange){
        System.out.println(stationInRange);
        return  stationService.getAllChargingStationsInRange(stationInRange);
    }

    @GetMapping("/station/chargers/{id}")
    public List<Charger> getAllChargerInStation(@PathVariable long id){
        return  chargerService.getChargerByStationId(id);
    }
}
