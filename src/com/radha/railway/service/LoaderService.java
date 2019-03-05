package com.radha.railway.service;

import com.radha.railway.LatLng;
import com.radha.railway.Station;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import com.radha.railway.dao.StationDaoImpl;
import com.radha.railway.dao.TrainDaoImpl;
import com.radha.railway.dao.TrainStopDaoImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LoaderService {

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

    public void load(BufferedReader myReader, Map<String,LatLng> latLngMap) throws IOException {
        Map<String,Station> stations = new HashMap<>();
        Map<String,Train> trains = new HashMap<>();
        Station currentStation = null;
        Train currentTrain = null ;
        StationDaoImpl stationDao = new StationDaoImpl();
        TrainDaoImpl trainDao = new TrainDaoImpl();
        TrainStopDaoImpl trainStopDao = new TrainStopDaoImpl();
        String readText;
        myReader.readLine();
        while ((readText= myReader.readLine()) != null) {
            String splitText[] = readText.split(",");
            String stationCode = splitText[3];
            String trainNo = splitText[0];
            int newSequence = Integer.parseInt(splitText[2]);
            long newDistance=Long.parseLong(splitText[7]);

            if(! stations.containsKey(stationCode)){
              saveStation(splitText,stationDao,currentStation);
              stations.put(stationCode,currentStation);
            }
            if(!trains.containsKey(trainNo)){

                saveTrain(splitText,trainDao,currentTrain);
                trains.put(trainNo,currentTrain);

            }
            TrainStop trainStop = new TrainStop(splitText[5],splitText[6],
                    currentTrain,newSequence,currentStation,newDistance);
            trainStopDao.save(trainStop);


        }
    }

    private Train createTrain(String trainNo, String trainName, Station sourceSation, Station destinationStation) {
       return new Train(trainNo,trainName,sourceSation,destinationStation);
    }

    private Station createStation(String stationName, String stationCode) {
        return new Station(stationName,stationCode);
    }

    private void saveStation(String[] splitText,StationDaoImpl stationDao,Station currentStation){
        currentStation =  createStation(splitText[4], splitText[3]);
        stationDao.save(currentStation);
    }

    private void saveTrain(String[] splitText,TrainDaoImpl trainDao,Train currentTrain){
        Station sourceStation = createStation(splitText[9],splitText[8]);
        Station destinationStation = createStation(splitText[11],splitText[10]);
        currentTrain = createTrain(splitText[0],splitText[1],sourceStation,destinationStation);
        trainDao.save(currentTrain);
    }
}
