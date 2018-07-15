package com.radha.railway.service;

import com.radha.railway.Station;
import com.radha.railway.TimeTable;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import com.radha.railway.dao.TimeTableDao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class StationService {
    private static TimeTableDao timeTableDao = new TimeTableDao();
    public Map<String, Station> getToStations(String fromStationCode) throws IOException {
        TimeTable timeTable = timeTableDao.getTimeTable();
        return getToStations(fromStationCode, timeTable);
    }
    public Map<String, Station> getToStations(String fromStationCode, TimeTable timeTable) {
          /* Algorithm:
        Step1:Declare a TimeTable object as timeTable and a map as destinationStations.
        Step2:Get the fromStation object from the stations map in the timeTable using the fromStationCode as key.
        Step3:for each trainStop in the fromStation object do
            i)Get the train from the trainStop
            ii)For each trainStop in the train do
            a)Get the Station and put it into the destinationStations

        return null;*/
        System.out.println("Getting to stations for fromStationdCode:" + fromStationCode);
        Map<String, Station> destinationStations = new HashMap();
        Map<String, Station> stations = timeTable.getStations();
        Station fromStation = stations.get(fromStationCode);
        for (TrainStop trainStop : fromStation.getTrainStops()) {
            int sequence = trainStop.getSequence();
            Train stoppingTrain = trainStop.getTrain();
            ArrayList<Station> stopingStations = stoppingTrain.getStoppingStations(sequence);
            for (Station stoppingStation : stopingStations) {
                destinationStations.put(stoppingStation.getCode(), stoppingStation);
            }
        }
        return destinationStations;
    }

    public Map<String, Station> getFromStations() throws IOException {
        return timeTableDao.getTimeTable().getStations();
    }
}
