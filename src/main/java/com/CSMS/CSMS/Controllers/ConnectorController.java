package com.CSMS.CSMS.Controllers;
import com.CSMS.CSMS.services.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.CSMS.CSMS.ConsumeAPI.ApiService;
import com.CSMS.CSMS.Repository.ChargerRepo;
import com.CSMS.CSMS.Repository.ConnectorRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Charger;
import com.CSMS.CSMS.models.Connector;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class ConnectorController {
    @Autowired
    private ConnectorService connectorService;
    @Autowired
    private ConnectorRepo connectorRepo;
    @Autowired
    private ChargerRepo chargerRepo;
    @Autowired
    private ApiService apiService;
    @GetMapping("/connector/all")
    public List<Connector> getAllConnectors()
    {
        return connectorService.getAllConnector();
    }
    @GetMapping("/connector/{id}")
    public Connector getConnectorById(@PathVariable Integer id)
    {
        return connectorService.getConnector(id);
    }
    @PostMapping("/connector/add")
    public Connector addconnector(@RequestBody Connector connector)
    {
        return connectorService.addConnector(connector);
    }
    @PutMapping("/connector/{id}")
public Connector udpateConnector(@PathVariable Integer id, @RequestBody Connector connector)
{
return connectorService.updateConnector(id, connector);
}
    @DeleteMapping("/connector/{id}")
    public ResponseEntity<HttpStatus> deleteConnector(@PathVariable Integer id){
        connectorService.deleteConnector(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAllConnectors/{chargerId}")
    public List<Integer> getAllConnector(@PathVariable String chargerId){
        try{
        List<Integer> connectors= apiService.getAllConnectors(chargerId);
        Charger charger= chargerRepo.getChargerByName(chargerId).get(0);
        // List<Charger> charger= chargerRepo.getChargerByName(chargerId);
        System.out.println(charger);
        // for(int i=0; i< connectors.size(); i++){
        //     Connector connector=new Connector();
        //     connector.setConnector_id(connectors.get(i));
        //     connector.setCharger_id(charger.getId().intValue());
        //     connectorRepo.save(connector);
        // }
        return apiService.getAllConnectors(chargerId);
    }
    catch(Exception e){
        throw new NotFoundException(e); 
    }
    }
}