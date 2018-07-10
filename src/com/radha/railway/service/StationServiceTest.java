package com.radha.railway.service;
import com.radha.railway.Station;
import com.radha.railway.TimeTable;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class StationServiceTest {
    @Test
    public void testGetToStations(){
        StationService stationService = new StationService();
        ArrayList<Train> trains = new ArrayList<>();
        Station fromStation = new Station("Erode","ERD");
        Station toStation = new Station("Chennai","MAS");
        Station intermidiateStation = new Station("Coimbatore","CBE");


        Train firstTrain = new Train(
                "ErodeExp",
                "1234",
                fromStation,
                toStation);
        trains.add(firstTrain);
        TrainStop trainStop1 = new TrainStop(null,null,firstTrain,1,fromStation,0);
        TrainStop trainStop2 = new TrainStop(null,null,firstTrain,2,intermidiateStation,10);
        TrainStop trainStop3 = new TrainStop(null,null,firstTrain,3,toStation,30);
        firstTrain.addTrainStop(trainStop1);
        firstTrain.addTrainStop(trainStop2);
        firstTrain.addTrainStop(trainStop3);
        fromStation.addTrainStop(trainStop1);
        intermidiateStation.addTrainStop(trainStop2);
        toStation.addTrainStop(trainStop3);
        HashMap<String,Station> stations = new HashMap<>();
        stations.put("ERD",fromStation);
        stations.put("CBE",intermidiateStation);
        stations.put("MAS",toStation);
        TimeTable  timeTable = new TimeTable(trains,stations);

        Map<String,Station> toStations = stationService.getToStations("ERD",timeTable);

        Map<String,Station> expectedStations = new HashMap<String, Station>();
        expectedStations.put("CBE",intermidiateStation);
        expectedStations.put("MAS",toStation);

        assertEquals(expectedStations,toStations);





    }
    @Test
    public void testGetToStationsforMultipleTrains(){
        StationService stationService = new StationService();
        ArrayList<Train> trains = new ArrayList<>();
        Station fromStation = new Station("Erode","ERD");
        Station toStation = new Station("Chennai","MAS");
        Station intermidiateStation = new Station("Coimbatore","CBE");


        Train firstTrain = new Train(
                "ErodeExp",
                "1234",
                fromStation,
                toStation);
        Station  fromStationSecondTrain = new Station("Tirupur","TUP");
        Station interStation1 = new Station("Coimbatore","CBE");
        Station interStation2 = new Station("Erode","ERD");
        Station interStation3 = new Station("Chennai","MAS");
        Station interStation4 = new Station("Vijayavada","VJA");
        Station interStation5 = new Station("Mangalore","MNG");
        Station toStationSecondTrain = new Station("Bangalore","BNG");
        Train secondTrain = new Train("BangloreExp",
                "4567",
                fromStationSecondTrain,toStationSecondTrain);
        trains.add(firstTrain);
        trains.add(secondTrain);
        TrainStop trainStop1 = new TrainStop(null,null,firstTrain,1,fromStation,0);
        TrainStop trainStop2 = new TrainStop(null,null,firstTrain,2,intermidiateStation,10);
        TrainStop trainStop3 = new TrainStop(null,null,firstTrain,3,toStation,30);
        firstTrain.addTrainStop(trainStop1);
        firstTrain.addTrainStop(trainStop2);
        firstTrain.addTrainStop(trainStop3);
        fromStation.addTrainStop(trainStop1);
        intermidiateStation.addTrainStop(trainStop2);
        toStation.addTrainStop(trainStop3);
        TrainStop train2TrainStop1 = new TrainStop(null,null,secondTrain,1,fromStationSecondTrain,0);
        TrainStop train2TrainStop2 = new TrainStop(null,null,secondTrain,2,interStation1,10);
        TrainStop train2TrainStop3 = new TrainStop(null,null,secondTrain,3,interStation2,20);
        TrainStop train2TrainStop4 = new TrainStop(null,null,secondTrain,4,interStation3,30);
        TrainStop train2TrainStop5 = new TrainStop(null,null,secondTrain,5,interStation4,40);
        TrainStop train2TrainStop6 = new TrainStop(null,null,secondTrain,6,interStation5,50);
        TrainStop train2TrainStop7 = new TrainStop(null,null,secondTrain,7,toStationSecondTrain,60);
        secondTrain.addTrainStop(train2TrainStop1);
        secondTrain.addTrainStop(train2TrainStop2);
        secondTrain.addTrainStop(train2TrainStop3);
        secondTrain.addTrainStop(train2TrainStop4);
        secondTrain.addTrainStop(train2TrainStop5);
        secondTrain.addTrainStop(train2TrainStop6);
        secondTrain.addTrainStop(train2TrainStop7);
        HashMap<String,Station> stations = new HashMap<>();
        stations.put("ERD",fromStation);
        stations.put("CBE",intermidiateStation);
        stations.put("MAS",toStation);
        stations.put("TUP",fromStationSecondTrain);
        stations.put("VJA",interStation4);
        stations.put("MNG",interStation5);
        stations.put("BNG",toStationSecondTrain);
        TimeTable  timeTable = new TimeTable(trains,stations);

        Map<String,Station> toStations = stationService.getToStations("ERD",timeTable);

        Map<String,Station> expectedStations = new HashMap<String, Station>();
        expectedStations.put("CBE",intermidiateStation);
        expectedStations.put("MAS",toStation);
        expectedStations.put("TUP",fromStationSecondTrain);
        expectedStations.put("VJA",interStation4);
        expectedStations.put("MNG",interStation5);
        expectedStations.put("BNG",toStationSecondTrain);

        assertEquals(expectedStations,toStations);





    }


}