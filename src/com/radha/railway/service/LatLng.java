package com.radha.railway.service;

public class LatLng {
    private double latitude;
    private double longitude;


    public LatLng(double latitude,double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
