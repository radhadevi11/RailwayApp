
package com.radha.railway;

import com.radha.railway.service.LatLng;

import java.util.ArrayList;

public class Station{
    private String name;
    private String code;
    private ArrayList<TrainStop> trainStops = new ArrayList<>();
    private LatLng latLng;

    public Station(String name, String code,LatLng latLng) {
        this.name = name;
        this.code = code;
        this.latLng = latLng;

    }
    public Station(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<TrainStop> getTrainStops() {
        return trainStops;
    }

    public void addTrainStop(TrainStop stop) {
        trainStops.add(stop);
    }

    public LatLng getLatLng() {
        return latLng;
    }

    public ArrayList<Train> getStoppingTrains() {
        ArrayList<Train> trains = new ArrayList<>();
        for(TrainStop trainStop: trainStops ) {
            trains.add(trainStop.getTrain());
        }
        return trains;
    }
    public boolean equals(Object other) {
        if (other instanceof Station) {
            Station otherStation = (Station) other;
            if (this.getCode().equals(otherStation.getCode())) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
}