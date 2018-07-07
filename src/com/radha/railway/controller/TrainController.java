package com.radha.railway.controller;

import com.radha.railway.Station;
import com.radha.railway.TimeTable;
import com.radha.railway.service.StationService;
import com.radha.railway.service.TimeTableLoader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TrainController {
    private TimeTable timeTable;

    public TimeTable loadFromFile(File myFile) throws IOException {
        TimeTableLoader timeTableLoader = new TimeTableLoader();
        timeTable = timeTableLoader.loadFromFile(new BufferedReader(new FileReader(myFile)));
        return timeTable;
    }

    public Map<String, Station> getFromStations() {
        return timeTable.getStations();

    }

    public Map<String, Station> getToStations(String fromStationCode, TimeTable timeTable) {

return null;
    }
}
