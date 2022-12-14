package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.Repository.ChargerRepo;
import com.CSMS.CSMS.Repository.StationRepo;
import com.CSMS.CSMS.models.Charger;
import com.CSMS.CSMS.models.Station;
import com.CSMS.CSMS.services.ChargerService;
import com.CSMS.CSMS.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChargerController {

    @Autowired
    private ChargerService chargerService;
    @Autowired
    private ChargerRepo chargerRepo;


    @GetMapping("/charger/{id}")
    public Charger getChargerById(@PathVariable long id) {
        return chargerService.getChargerById(id);
    }

    @PostMapping("/charger/add")
    public Charger addChargerById(@RequestBody Charger charger) {
        return chargerService.addCharger(charger);
    }

    @PutMapping("/charger/{id}")
    public Charger updateChargerById(@PathVariable long id, @RequestBody Charger charger) {
        return chargerService.updateChargerById(id, charger);
    }

    @DeleteMapping("/charger/{id}")
    public ResponseEntity<HttpStatus> deleteChargerById(@PathVariable long id){
        chargerService.deleteChargerById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
