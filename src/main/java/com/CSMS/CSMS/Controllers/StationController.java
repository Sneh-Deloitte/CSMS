package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.ConsumeAPI.ApiService;
import com.CSMS.CSMS.ConsumeAPI.dto.OcppJsonStatus;
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

@CrossOrigin(origins = "*")
@RestController
public class StationController {

    @Autowired
    private StationService stationService;
    @Autowired
    private ChargerService chargerService;
    @Autowired
    private ApiService apiService;


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


//        to find all other are connected or not
        List<Charger> chargers= chargerService.getChargerByStationId(id);
        List<OcppJsonStatus> activeChargers= apiService.getActiveChargers();

        for (int j=0;j<chargers.size();j++)
        {
            Charger charger = chargers.get(j);
            //update the status id to 2 which means its's  not connected
            charger.setCharger_status_id(2);
            chargerService.updateChargerById(charger.getId(),charger);
        }

        for (int i=0;i<activeChargers.size();i++)
        {
            OcppJsonStatus ocppJsonStatus = activeChargers.get(i);
            for (int j=0;j<chargers.size();j++)
            {
                Charger charger = chargers.get(j);
                if(ocppJsonStatus.getChargeBoxId().equals(charger.getCharger_name())){
                    //update the status id to 1 which means its's  connected
                    charger.setCharger_status_id(1);
                    chargerService.updateChargerById(charger.getId(),charger);
                }
            }
        }

        return  chargerService.getChargerByStationId(id);
    }

    @PostMapping("/stationByMail/{mail}")
    public List<Station> getStationByMail(@PathVariable String mail){
        System.out.println(mail);
        return  stationService.getStationByMail(mail);
    }

}