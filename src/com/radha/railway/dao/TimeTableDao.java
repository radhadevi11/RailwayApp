package com.radha.railway.dao;

import com.radha.railway.Station;
import com.radha.railway.TimeTable;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
    public TimeTable loadFromFile(BufferedReader myReader) throws IOException {
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
                currentStation = new Station(splitText[4], splitText[3]);
                stations.put(currentStation.getCode(), currentStation);
            }
            else{
                currentStation = stations.get(splitText[3]);

            }
            if(currentTrain == null || !currentTrain.getNumber().equals(splitText[0])){
                Station sourceStation = new Station(splitText[9],splitText[8]);
                Station destinationStation = new Station(splitText[11],splitText[10]);
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


    public TimeTable loadFromFile(File myFile) throws IOException {
        return loadFromFile(new BufferedReader(new FileReader(myFile)));
    }

    public TimeTable getTimeTable() throws IOException {
        if (timeTable == null) {
            File csv = new File("C:\\Users\\radha\\Downloads\\ChennaiCentralTimetable.csv");
            timeTable = loadFromFile(csv);
            //store in database
        }
        return timeTable;
    }
}
