package com.radha.railway;

import java.util.ArrayList;

public class Train {
    private String name;
    private String number;
    private Station sourceStation;
    private Station destinationStation;
    private ArrayList<TrainStop> trainStops;


    public Train(String name, String number, Station sourceStation, Station destinationStation) {
        this.name = name;
        this.number = number;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public Station getSourceStation() {
        return sourceStation;
    }

    public Station getDestinationStation() {
        return destinationStation;
    }

    public ArrayList<TrainStop> getTrainStops() {
        return trainStops;
    }

    public void addTrainStop(TrainStop stop) {
        trainStops.add(stop);
    }
}