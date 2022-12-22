package com.CSMS.CSMS.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StationInRange {

    private  double latitude;
    private  double longitude;
    private  int minDistInKm;

    public StationInRange(double latitude, double longitude, int minDistInKm) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.minDistInKm = minDistInKm;
    }
}
