
package com.radha.railway.controller;

import com.radha.railway.LatLng;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;

import java.util.ArrayList;

public class StationModel {
    private String name;
    private String code;
    private LatLng latLng;

    public StationModel(String name, String code, LatLng latLng) {
        this.name = name;
        this.code = code;
        this.latLng = latLng;

    }
    public StationModel(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }


    public LatLng getLatLng() {
        return latLng;
    }


    public boolean equals(Object other) {
        if (other instanceof StationModel) {
            StationModel otherStation = (StationModel) other;
            if (this.getCode().equals(otherStation.getCode())) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }
    public String toString(){
        return "StationName:"+this.name+" StationCode:"+this.code+this.latLng;
    }
}