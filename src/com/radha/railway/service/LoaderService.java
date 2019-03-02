package com.radha.railway.service;

import com.radha.railway.LatLng;
import com.radha.railway.Station;
import com.radha.railway.dao.StationDaoImpl;

import java.io.BufferedReader;
import java.util.Map;

public class LoaderService {

    private StationDaoImpl stationDao;

    /*Algorithm
Chennai - santragachi(1-12)(each station of the train is a train stop)
Step0.1:declare a Train object(currentTrain) and
declare stations map with the key of stationCode and value of Station object and
initialize a trains hashMap with the key of trainNo and value of Train object.
    For each Line in the File do
            Store the stationCode in a stationCode variable
            if the stationCode is not present in the stations map then
                   Create a Station.
                   Store the station code and Station into the map.
                   save station to database
            Store train number in trainNo variable
            if trainNo is not present in trains map then
                    Create a sourceStation
                    Create a Destination Station
                    Create a Train and store it into the currentTrain
                    save currentTrain to database
                    add this trainNo and train object to trains map

        Create a TrainStop
        add TrainStop to database

*/

    public void load(BufferedReader myReader, Map<String,LatLng> latLngMap) {



    }
}
