package com.CSMS.CSMS.services.impl;

import com.CSMS.CSMS.Repository.StationRepo;
import com.CSMS.CSMS.exception.NotFoundException;
import com.CSMS.CSMS.models.Station;
import com.CSMS.CSMS.models.StationInRange;
import com.CSMS.CSMS.services.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationImpl implements StationService {

    @Autowired
    private StationRepo stationRepo;


    @Override
    public List<Station> getAllChargingStations(){
        return stationRepo.findAll();
    }

    @Override
    public List<Station> getAllChargingStationsInRange(StationInRange stationInRange) {

        List<Station> stations = stationRepo.findAll();
        List<Station> finalStation =new ArrayList<>();

        for(int i=0; i< stations.size() ; i++) {
            Station station = stations.get(i);
            double currLat = station.getLatitude();
            double currLon = station.getLongitude();
            if(meterDistanceBetweenPoints(stationInRange.getLatitude(),stationInRange.getLongitude(),currLat,currLon,stationInRange.getMinDistInKm())){
                finalStation.add(station);
            }
        }

        return  finalStation;
    }

    @Override
    public Station getChargingStationById(long id){
        return stationRepo.findById(id).orElseThrow(() -> new NotFoundException("Station not found with id" + id));
    }


    @Override
    public  Station updateChargingStationById(long id, Station station){
        Station station1 = stationRepo.findById(id).orElseThrow(() -> new NotFoundException("Station not found with id" + id));
        if(station.getAddress() != null) station1.setAddress(station.getAddress());
        if(station.getCity() != null) station1.setCity(station.getCity());
        if(station.getContact_no() != 0) station1.setContact_no(station.getContact_no());
        if(station.getName() != null) station1.setName(station.getName());
        if(station.getEmail_id() != null) station1.setEmail_id(station.getEmail_id());
        if(station.getLongitude() != 0) station1.setLongitude(station.getLongitude());
        if(station.getLatitude() != 0) station1.setLatitude(station.getLatitude());
        if(station.getImage_url() != null) station1.setImage_url(station.getImage_url());
        return stationRepo.save(station1);
    }


    @Override
    public void deleteChargingStation(long id) {
        try {
            stationRepo.findById(id);
            stationRepo.deleteById(id);
        } catch (Exception exception) {
            throw new NotFoundException("user not found with id" + getChargingStationById(id));
        }
    }

    @Override
    public Station addChargingStation(Station station) {
        return stationRepo.save(station);
    }


    private boolean meterDistanceBetweenPoints(double lat_a, double lng_a, double lat_b, double lng_b , int maxDisInKm) {
        float pk = (float) (180.f/Math.PI);

        double a1 = lat_a / pk;
        double a2 = lng_a / pk;
        double b1 = lat_b / pk;
        double b2 = lng_b / pk;

        double t1 = Math.cos(a1) * Math.cos(a2) * Math.cos(b1) * Math.cos(b2);
        double t2 = Math.cos(a1) * Math.sin(a2) * Math.cos(b1) * Math.sin(b2);
        double t3 = Math.sin(a1) * Math.sin(b1);
        double tt = Math.acos(t1 + t2 + t3);

        double distInMeter = 6366000 * tt;

        double distInKm = distInMeter / 1000;
        System.out.println(distInKm);
        if(distInKm<=maxDisInKm)return  true;

        return  false;
}
    public List<Station> getStationByMail(String mail){
        List<Station> list=stationRepo.getAllStationByMail(mail);
        System.out.println(mail);
        return list;
    }
}
