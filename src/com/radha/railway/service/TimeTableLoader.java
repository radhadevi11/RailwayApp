package com.radha.railway.service;

import com.radha.railway.Station;
import com.radha.railway.TimeTable;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TimeTableLoader {

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


}
