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
   private StationDaoImpl stationDao = new StationDaoImpl();
   private TrainDaoImpl trainDao = new TrainDaoImpl();
   private TrainStopDaoImpl trainStopDao = new TrainStopDaoImpl();


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
               String readText;
        myReader.readLine();
        while ((readText= myReader.readLine()) != null) {
            String splitText[] = readText.split(",");
            String stationCode = splitText[3];
            String trainNo = splitText[0];
            int newSequence = Integer.parseInt(splitText[2]);
            long newDistance=Long.parseLong(splitText[7]);


                currentStation = saveStation(splitText[4], splitText[3],stations);

            if(! trains.containsKey(trainNo)){
               //to check whether this station is available in the station table
                Station sourceStation = saveStation(splitText[9],splitText[8],stations);
                Station destinationStation = saveStation(splitText[11],splitText[10],stations);

                currentTrain = saveTrain(splitText[1],trainNo,sourceStation,destinationStation);
                trains.put(trainNo,currentTrain);

            }
            TrainStop trainStop = new TrainStop(splitText[5],splitText[6],
                    currentTrain,newSequence,currentStation,newDistance);
            trainStopDao.save(trainStop);


        }
    }

    private Station saveStation(String stationName, String stationCode, Map<String, Station> stations){
        Station currentStation = stations.get(stationCode);
        if (! stations.containsKey(stationCode) ) {
            currentStation = new Station(stationName, stationCode);
            stationDao.save(currentStation);
            stations.put(stationCode,currentStation);
        }
        return currentStation;
    }

    private Train saveTrain(String trainNo,String trainName,Station sourceStation,Station destinationStation){
        Train currentTrain = new Train(trainName,trainNo,sourceStation,destinationStation);
        trainDao.save(currentTrain);
        return currentTrain;
    }


}
