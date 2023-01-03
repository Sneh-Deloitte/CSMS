package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.Repository.ConnectorRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Connector;
import com.CSMS.CSMS.services.ConnectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ConnectorImpl implements ConnectorService {
    @Autowired
    private ConnectorRepo connectorRepo;
    @Override
    public List<Connector> getAllConnector() {
        return connectorRepo.findAll();
    }
    @Override
    public Connector addConnector(Connector status) {
        return connectorRepo.save(status);
    }
    // @Override
    // public void deleteConnector(Integer id) {
    //     try {
    //         connectorRepo.deleteById(id);
    //     } catch (Exception exception) {
    //         throw new NotFoundException("Status not found with id " + id);
    //     }
    // }
    // @Override
    // public Connector updateConnector(Integer id, Connector connector) {
    //     Connector connector1 = connectorRepo.findById(id).orElseThrow(() -> new NotFoundException("status not found with id" + id));
    //    if(connector.getConnector_description() != null) connector1.setConnector_description(connector.getConnector_description());
    //     return connectorRepo.save(connector1);
    // }
    @Override
    public Connector getConnector(Integer id) {
        return connectorRepo.findById(id).orElseThrow(() -> new NotFoundException("status not found with id "  + id));
    }
}