package com.CSMS.CSMS.models;

public class StationInRange {

    private  double latitude;
    private  double longitude;
    private  int minDistInKm;

    public StationInRange(double latitude, double longitude, int minDistInKm) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.minDistInKm = minDistInKm;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getMinDistInKm() {
        return minDistInKm;
    }

    public void setMinDistInKm(int minDistInKm) {
        this.minDistInKm = minDistInKm;
    }


}
