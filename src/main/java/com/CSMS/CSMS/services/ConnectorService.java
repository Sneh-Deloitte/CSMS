package com.CSMS.CSMS.services;
import com.CSMS.CSMS.models.Connector;

import java.util.List;
public interface ConnectorService {
    public List<Connector> getAllConnector();
    public Connector addConnector(Connector connector);
    public void deleteConnector(Integer id);
    // public Connector updateConnector(Integer id,Connector connector);
    public Connector getConnector(Integer id);
}