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
    public void testGetToStations() throws NoSuchFromStationException {
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
    public void testGetToStationsforMultipleTrains() throws NoSuchFromStationException {
        StationService stationService = new StationService();
        ArrayList<Train> trains = new ArrayList<>();
        Station erodeStation = new Station("Erode","ERD");
        Station chennaiStation = new Station("Chennai","MAS");
        Station coimbatoreStation = new Station("Coimbatore","CBE");


        Train firstTrain = new Train(
                "ErodeExp",
                "1234",
                erodeStation,
                chennaiStation);
        Station  tirupurStation = new Station("Tirupur","TUP");
        Station vijayavadaStation = new Station("Vijayavada","VJA");
        Station mangaloreStation = new Station("Mangalore","MNG");
        Station bangaloreStation = new Station("Bangalore","BNG");
        Train secondTrain = new Train("BangloreExp",
                "4567",
                tirupurStation,bangaloreStation);
        trains.add(firstTrain);
        TrainStop trainStop1 = new TrainStop(null,null,firstTrain,1,erodeStation,0);
        TrainStop trainStop2 = new TrainStop(null,null,firstTrain,2,coimbatoreStation,10);
        TrainStop trainStop3 = new TrainStop(null,null,firstTrain,3,chennaiStation,30);
        firstTrain.addTrainStop(trainStop1);
        firstTrain.addTrainStop(trainStop2);
        firstTrain.addTrainStop(trainStop3);
        erodeStation.addTrainStop(trainStop1);
        coimbatoreStation.addTrainStop(trainStop2);
        chennaiStation.addTrainStop(trainStop3);
        trains.add(secondTrain);
        TrainStop train2TrainStop1 = new TrainStop(null,null,secondTrain,1,tirupurStation,0);
        TrainStop train2TrainStop2 = new TrainStop(null,null,secondTrain,2,coimbatoreStation,10);
        TrainStop train2TrainStop3 = new TrainStop(null,null,secondTrain,3,erodeStation,20);
        TrainStop train2TrainStop4 = new TrainStop(null,null,secondTrain,4,chennaiStation,30);
        TrainStop train2TrainStop5 = new TrainStop(null,null,secondTrain,5,vijayavadaStation,40);
        TrainStop train2TrainStop6 = new TrainStop(null,null,secondTrain,6,mangaloreStation,50);
        TrainStop train2TrainStop7 = new TrainStop(null,null,secondTrain,7,bangaloreStation,60);
        secondTrain.addTrainStop(train2TrainStop1);
        secondTrain.addTrainStop(train2TrainStop2);
        secondTrain.addTrainStop(train2TrainStop3);
        secondTrain.addTrainStop(train2TrainStop4);
        secondTrain.addTrainStop(train2TrainStop5);
        secondTrain.addTrainStop(train2TrainStop6);
        secondTrain.addTrainStop(train2TrainStop7);
        tirupurStation.addTrainStop(train2TrainStop1);
        coimbatoreStation.addTrainStop(train2TrainStop2);
        erodeStation.addTrainStop(train2TrainStop3);
        chennaiStation.addTrainStop(train2TrainStop4);
        vijayavadaStation.addTrainStop(train2TrainStop5);
        mangaloreStation.addTrainStop(train2TrainStop6);
        bangaloreStation.addTrainStop(train2TrainStop7);
        HashMap<String,Station> stations = new HashMap<>();
        stations.put("ERD",erodeStation);
        stations.put("CBE",coimbatoreStation);
        stations.put("MAS",chennaiStation);
        stations.put("TUP",tirupurStation);
        stations.put("VJA",vijayavadaStation);
        stations.put("MNG",mangaloreStation);
        stations.put("BNG",bangaloreStation);
        TimeTable  timeTable = new TimeTable(trains,stations);

        Map<String,Station> toStations = stationService.getToStations("ERD",timeTable);

        Map<String,Station> expectedStations = new HashMap<String, Station>();
        expectedStations.put("CBE",coimbatoreStation);
        expectedStations.put("MAS",chennaiStation);
        expectedStations.put("VJA",vijayavadaStation);
        expectedStations.put("MNG",mangaloreStation);
        expectedStations.put("BNG",bangaloreStation);

        assertEquals(expectedStations,toStations);





    }


}