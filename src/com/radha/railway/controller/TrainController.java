package com.radha.railway.controller;

import com.radha.railway.Station;
import com.radha.railway.Train;
import com.radha.railway.service.StationService;
import com.radha.railway.service.TrainService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainController {
    private static StationService stationService = new StationService();
    private static TrainService trainService = new TrainService();

    public List<StationModel> getFromStations() throws IOException {
        Map<String, Station> fromStations = stationService.getFromStations();
        //TODO convrt to List<StationModel>

    }

    public List<StationModel> getToStations(String fromStationCode) throws IOException {
        return stationService.getToStations(fromStationCode);
    }
    public ArrayList<TrainModel> getTrains(String fromStationCode,String toStationCode)throws IOException{
        return  trainService.getTrains(fromStationCode,toStationCode);
    }
}
