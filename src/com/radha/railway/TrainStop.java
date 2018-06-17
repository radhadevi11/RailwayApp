package com.radha.railway;

public class TrainStop {
    private String arrivalTime;
    private String departureTime;
    private Train train;
    private int sequence;
    private Station station;

    public TrainStop(String arrivalTime, String departureTime, Train train, int sequence, Station station) {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.train = train;
        this.sequence = sequence;
        this.station = station;

    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public Train getTrain() {
        return train;
    }

    public int getSequence() {
        return sequence;
    }

    public Station getStation() {
        return station;
    }


}