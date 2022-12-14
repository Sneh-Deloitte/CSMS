package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.Repository.StatusRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Status;
import com.CSMS.CSMS.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatusImpl implements StatusService {

    @Autowired
    private StatusRepo statusRepo;

    @Override
    public List<Status> getAllStatus() {
        return statusRepo.findAll();
    }

    @Override
    public Status addStatus(Status status) {
        return statusRepo.save(status);
    }

    @Override
    public void deleteStatus(Integer id) {
        try {
            statusRepo.deleteById(id);
        } catch (Exception exception) {
            throw new NotFoundException("Status not found with id " + id);
        }
    }

    @Override
    public Status updateStatus(Integer id, Status status) {
        Status status1 = statusRepo.findById(id).orElseThrow(() -> new NotFoundException("status not found with id" + id));
       if(status.getStatus_description() != null) status1.setStatus_description(status.getStatus_description());
        return statusRepo.save(status1);
    }

    @Override
    public Status getStatus(Integer id) {
        return statusRepo.findById(id).orElseThrow(() -> new NotFoundException("status not found with id "  + id));
    }
}
