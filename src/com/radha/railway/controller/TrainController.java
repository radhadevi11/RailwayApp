package com.radha.railway.controller;

import com.radha.railway.Station;
import com.radha.railway.service.StationService;

import java.io.IOException;
import java.util.Map;

public class TrainController {
    private static StationService stationService = new StationService();

    public Map<String, Station> getFromStations() throws IOException {
        return stationService.getFromStations();

    }

    public Map<String, Station> getToStations(String fromStationCode) throws IOException {
        return stationService.getToStations(fromStationCode);
    }
}
