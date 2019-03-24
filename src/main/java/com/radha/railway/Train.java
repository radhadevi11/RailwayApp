package com.radha.railway;

import java.util.ArrayList;

public class Train {
    private Integer id;
    private String name;
    private String number;
    private Station sourceStation;
    private Station destinationStation;
    private ArrayList<TrainStop> trainStops = new ArrayList<>();


    public Train(String name, String number, Station sourceStation, Station destinationStation) {
        this(null, name, number, sourceStation, destinationStation);
    }

    public Train(Integer id, String name, String number, Station sourceStation, Station destinationStation) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
    }

    public Train(Integer id){
        this.id = id;
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
    public ArrayList<Station> getStoppingStations(int sequence){
         ArrayList<Station> stations = new ArrayList<>();
         for(TrainStop currentStop : trainStops ) {
             if(currentStop.getSequence() > sequence) {
                 Station currentStation = currentStop.getStation();
                 stations.add(currentStation);
             }
         }
         return stations;
    }

    public boolean equals(Object other) {
        if (other instanceof Train) {
            Train otherTrain = (Train) other;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}