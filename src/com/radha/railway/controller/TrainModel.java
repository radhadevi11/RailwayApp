package com.radha.railway.controller;

import com.radha.railway.Station;
import com.radha.railway.TrainStop;

import java.util.ArrayList;

public class TrainModel {
    private String name;
    private String number;
    private StationModel sourceStation;
    private StationModel destinationStation;
    private ArrayList<TrainStopModel> trainStops = new ArrayList<>();


    public TrainModel(String name, String number, StationModel sourceStation, StationModel destinationStation,ArrayList<TrainStopModel> trainStops) {
        this.name = name;
        this.number = number;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.trainStops = trainStops;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public StationModel getSourceStation() {
        return sourceStation;
    }

    public StationModel getDestinationStation() {
        return destinationStation;
    }

    public ArrayList<TrainStopModel> getTrainStops() {
        return trainStops;
    }

    public boolean equals(Object other) {
        if (other instanceof TrainModel) {
            TrainModel otherTrain = (TrainModel) other;
            if (this.getNumber().equals(otherTrain.getNumber())) {
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Train{" +
                "name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", sourceStation=" + sourceStation +
                ", destinationStation=" + destinationStation +
                ", trainStops=" + trainStops +
                '}';
    }
}