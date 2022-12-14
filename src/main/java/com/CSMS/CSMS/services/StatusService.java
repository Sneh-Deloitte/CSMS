package com.CSMS.CSMS.services;

import com.CSMS.CSMS.models.Status;

import java.util.List;

public interface StatusService {

    public List<Status> getAllStatus();

    public Status addStatus(Status status);

    public void deleteStatus(Integer id);

    public Status updateStatus(Integer id,Status status);

    public Status getStatus(Integer id);


}
