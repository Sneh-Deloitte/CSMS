package com.CSMS.CSMS.Controllers;
import com.CSMS.CSMS.services.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.CSMS.CSMS.Repository.ConnectorRepo;
import com.CSMS.CSMS.models.Connector;
import java.util.List;
@RestController
public class ConnectorController {
    @Autowired
    private ConnectorService connectorService;
    @Autowired
    private ConnectorRepo connectorRepo;
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
    // @PutMapping("/connector/{id}")
// public Connector udpateConnector(@PathVariable Integer id, @RequestBody Connector connector)
// {
// return connectorService.updateConnector(id, connector);
// }
    @DeleteMapping("/connector/{id}")
    public ResponseEntity<HttpStatus> deleteConnector(@PathVariable Integer id){
        connectorService.deleteConnector(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}