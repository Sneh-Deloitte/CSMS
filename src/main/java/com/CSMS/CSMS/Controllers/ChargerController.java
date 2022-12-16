package com.CSMS.CSMS.Controllers;

import com.CSMS.CSMS.ConsumeAPI.ApiService;
import com.CSMS.CSMS.ConsumeAPI.dto.OcppJsonStatus;
import com.CSMS.CSMS.Repository.ChargerRepo;
import com.CSMS.CSMS.models.Charger;
import com.CSMS.CSMS.services.ChargerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ChargerController {

    @Autowired
    private ChargerService chargerService;
    @Autowired
    private ChargerRepo chargerRepo;

    @Autowired
    private ApiService apiService;


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

    @GetMapping("/activeCharger")
    public void activeCharger()
    {
        List<OcppJsonStatus> list = apiService.getActiveChargers();
    }

}
