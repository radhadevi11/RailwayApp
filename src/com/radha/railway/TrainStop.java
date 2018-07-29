package com.radha.railway;

public class TrainStop {
    private String arrivalTime;
    private String departureTime;
    private Train train;
    private int sequence;
    private Station station;
    private long distance;

    public TrainStop(String arrivalTime, String departureTime, Train train, int sequence, Station station, long distance) {
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.train = train;
        this.sequence = sequence;
        this.station = station;
        this.distance = distance;

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

    public long getDistance() {
        return distance;
    }

    public boolean isBeforeStop(TrainStop otherStop){
        if(this.getTrain().equals(otherStop.getTrain()) && this.getSequence() < otherStop.getSequence()){
            return true;
        }
        else {
            return false;
        }
    }
}