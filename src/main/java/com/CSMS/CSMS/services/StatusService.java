package com.CSMS.CSMS.services;

import com.CSMS.CSMS.models.Status;

import java.util.HashMap;
import java.util.List;

public interface StatusService {

    public List<Status> getAllStatusByStationId(int station_id);

    public Status addStatus(Status status);

    // public void deleteStatus(Integer id);

    public Status updateStatus(Status status);

    // public Status getStatus(Integer id);

    public String statusNotification(HashMap<String, String> store);

}
