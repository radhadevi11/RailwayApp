package com.radha.railway.dao;

import com.radha.railway.Station;
import com.radha.railway.TimeTable;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import com.radha.railway.LatLng;
import org.omg.CORBA.portable.InputStream;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TimeTableDao {
    private TimeTable timeTable;
    /*Algorithm
Chennai - santragachi(1-12)(each station of the train is a train stop)
Step0.1:Initialize an empty Train object(currentTrain) and inizialize stations map and initialize a trais list.
Step0:For each Line in the File do
Step1:Create a Station.
Step1.1:Store the station code and Station into the map.
Step2:if currentTrain is empty OR this trainNo is NOT equal to the currentTrain trainNo
  Step2.1:Create a sourceStation
  Step2.2:Create a Destination Station
  Ste2.3:Create a Train and store it into the curentTrain

Step3:Create a TrainStop
Step4:Add TrainStop to currentTrain
Step5:Add the currentTrain to the trains list.
*/
    public TimeTable loadFromFile(BufferedReader myReader,Map<String,LatLng> latLngMap) throws IOException {
        //similar to readFromFile
        //get the stations and trains
        ArrayList<Train> trains = new ArrayList<>();
        HashMap<String,Station> stations = new HashMap<>();
        Station currentStation;
        Train currentTrain = null ;
        String readText;
        myReader.readLine();
        while ((readText= myReader.readLine()) != null) {
            String splitText[]= readText.split(",");
            int newSequence=Integer.parseInt(splitText[2]);
            long newDistance=Long.parseLong(splitText[7]);
            if(stations.get(splitText[3])==null) {
                currentStation = createStation(splitText[3], splitText[4],latLngMap);
                stations.put(currentStation.getCode(), currentStation);
            }
            else{
                currentStation = stations.get(splitText[3]);

            }
            if(currentTrain == null || !currentTrain.getNumber().equals(splitText[0])){
                Station sourceStation =createStation(splitText[8],splitText[9],latLngMap);
                Station destinationStation = createStation(splitText[10],splitText[11],latLngMap);
                currentTrain = new Train(splitText[1],splitText[0],
                        sourceStation,destinationStation);
                trains.add(currentTrain);


            }
            TrainStop trainStop = new TrainStop(splitText[5],splitText[6],
                    currentTrain,newSequence,currentStation,newDistance);
            currentTrain.addTrainStop(trainStop);
            currentStation.addTrainStop(trainStop);

        }
        return new TimeTable(trains,stations);

    }

    private Station createStation(String code, String name, Map<String, LatLng> latLngMap) {

            return new Station(name, code, latLngMap.get(code));
        }

    public TimeTable loadFromFile(File myFile,Map<String,LatLng> latLngMap) throws IOException {
        return loadFromFile(new BufferedReader(new FileReader(myFile)),latLngMap);
    }

    public TimeTable getTimeTable() throws IOException {
        if (timeTable == null) {
            java.io.InputStream latLanStream = this.getClass().getResourceAsStream("/StationLatitudeLongitude.csv");
            BufferedReader latLngReader = new BufferedReader((new InputStreamReader(latLanStream)));
            Map<String,LatLng> latLngMap = loadLatLngFile(latLngReader);
            java.io.InputStream timeTableStream = this.getClass().getResourceAsStream("/ChennaiCentralTimetable.csv");
            BufferedReader timeTableReader = new BufferedReader(new InputStreamReader(timeTableStream));
            timeTable = loadFromFile(timeTableReader, latLngMap);
            //store in database
        }
        return timeTable;
    }
    Map<String,LatLng> loadLatLngFile(BufferedReader myReader)throws IOException{
        HashMap<String,LatLng> results = new HashMap<>();
        myReader.readLine();
        String readText;
        while ((readText= myReader.readLine()) != null) {
            String splitText[]= readText.split(",");
            double latitude = Double.parseDouble(splitText[1]);
            double longitude = Double.parseDouble(splitText[2]);
           results.put(splitText[0],new LatLng(latitude,longitude));


        }
        return results;


    }
}
