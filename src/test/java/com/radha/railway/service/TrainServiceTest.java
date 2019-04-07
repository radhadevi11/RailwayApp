package com.radha.railway.service;

import com.radha.railway.*;
import org.junit.Test;


import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;


public class TrainServiceTest {
    @Test
    public void testGetTrains() throws IOException, NoSuchToStationException, NoSuchFromStationException {
        String fromStationCode = "ERD";
        String toStationCode = "MAS";
        TrainService trainService = new TrainService();
        ArrayList<Train> trains = new ArrayList<>();
        Station erodeStation = new Station("Erode", "ERD", new LatLng(12.3336, 31.334));
        Station chennaiStation = new Station("Chennai", "MAS", new LatLng(12.3336, 31.334));
        Station coimbatoreStation = new Station("Coimbatore", "CBE", new LatLng(12.3336, 31.334));


        Train firstTrain = new Train(
                "ErodeExp",
                "1234",
                erodeStation,
                chennaiStation);
        Station tirupurStation = new Station("Tirupur", "TUP", new LatLng(12.3336, 31.334));
        Station vijayavadaStation = new Station("Vijayavada", "VJA", new LatLng(12.3336, 31.334));
        Station mangaloreStation = new Station("Mangalore", "MNG", new LatLng(12.3336, 31.334));
        Station bangaloreStation = new Station("Bangalore", "BNG",new LatLng(12.3336,31.334));
        Train secondTrain = new Train("BangloreExp",
                "4567",
                tirupurStation, bangaloreStation);
        trains.add(firstTrain);
        TrainStop trainStop1 = new TrainStop(null, null, firstTrain, 1, erodeStation, 0);
        TrainStop trainStop2 = new TrainStop(null, null, firstTrain, 2, coimbatoreStation, 10);
        TrainStop trainStop3 = new TrainStop(null, null, firstTrain, 3, chennaiStation, 30);
        firstTrain.addTrainStop(trainStop1);
        firstTrain.addTrainStop(trainStop2);
        firstTrain.addTrainStop(trainStop3);
        erodeStation.addTrainStop(trainStop1);
        coimbatoreStation.addTrainStop(trainStop2);
        chennaiStation.addTrainStop(trainStop3);
        trains.add(secondTrain);
        TrainStop train2TrainStop1 = new TrainStop(null, null, secondTrain, 1, tirupurStation, 0);
        TrainStop train2TrainStop2 = new TrainStop(null, null, secondTrain, 2, coimbatoreStation, 10);
        TrainStop train2TrainStop3 = new TrainStop(null, null, secondTrain, 3, erodeStation, 20);
        TrainStop train2TrainStop4 = new TrainStop(null, null, secondTrain, 4, chennaiStation, 30);
        TrainStop train2TrainStop5 = new TrainStop(null, null, secondTrain, 5, vijayavadaStation, 40);
        TrainStop train2TrainStop6 = new TrainStop(null, null, secondTrain, 6, mangaloreStation, 50);
        TrainStop train2TrainStop7 = new TrainStop(null, null, secondTrain, 7, bangaloreStation, 60);
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
        HashMap<String, Station> stations = new HashMap<>();
        stations.put("ERD", erodeStation);
        stations.put("CBE", coimbatoreStation);
        stations.put("MAS", chennaiStation);
        stations.put("TUP", tirupurStation);
        stations.put("VJA", vijayavadaStation);
        stations.put("MNG", mangaloreStation);
        stations.put("BNG", bangaloreStation);
        TimeTable  timeTable = new TimeTable(trains,stations);

        ArrayList<Train> actualTrains =  trainService.getTrains(fromStationCode,toStationCode,timeTable);

        ArrayList<Train> expectedTrains = new ArrayList<>();
        expectedTrains.add(firstTrain);
        expectedTrains.add(secondTrain);


        assertEquals(expectedTrains,actualTrains);



    }
    @Test
    public void getTrainsForDifferentSequence() throws IOException, NoSuchToStationException, NoSuchFromStationException {
        String fromStationCode = "ERD";
        String toStationCode = "MAS";
        TrainService trainService = new TrainService();
        ArrayList<Train> trains = new ArrayList<>();
        Station erodeStation = new Station("Erode", "ERD", new LatLng(12.3336, 31.334));
        Station chennaiStation = new Station("Chennai", "MAS", new LatLng(12.3336, 31.334));
        Station coimbatoreStation = new Station("Coimbatore", "CBE", new LatLng(12.3336, 31.334));


        Train firstTrain = new Train(
                "ErodeExp",
                "1234",
                erodeStation,
                chennaiStation);
        Station tirupurStation = new Station("Tirupur", "TUP", new LatLng(12.3336, 31.334));
        Station vijayavadaStation = new Station("Vijayavada", "VJA", new LatLng(12.3336, 31.334));
        Station mangaloreStation = new Station("Mangalore", "MNG", new LatLng(12.3336, 31.334));
        Station bangaloreStation = new Station("Bangalore", "BNG",new LatLng(12.3336,31.334));
        Train secondTrain = new Train("BangloreExp",
                "4567",
                tirupurStation, bangaloreStation);
        trains.add(firstTrain);
        TrainStop trainStop1 = new TrainStop(null, null, firstTrain, 1, erodeStation, 0);
        TrainStop trainStop2 = new TrainStop(null, null, firstTrain, 2, coimbatoreStation, 10);
        TrainStop trainStop3 = new TrainStop(null, null, firstTrain, 3, chennaiStation, 30);
        firstTrain.addTrainStop(trainStop1);
        firstTrain.addTrainStop(trainStop2);
        firstTrain.addTrainStop(trainStop3);
        erodeStation.addTrainStop(trainStop1);
        coimbatoreStation.addTrainStop(trainStop2);
        chennaiStation.addTrainStop(trainStop3);
        trains.add(secondTrain);
        TrainStop train2TrainStop1 = new TrainStop(null, null, secondTrain, 1, tirupurStation, 0);
        TrainStop train2TrainStop2 = new TrainStop(null, null, secondTrain, 2, coimbatoreStation, 10);
        TrainStop train2TrainStop3 = new TrainStop(null, null, secondTrain, 4, erodeStation, 20);
        TrainStop train2TrainStop4 = new TrainStop(null, null, secondTrain, 3, chennaiStation, 30);
        TrainStop train2TrainStop5 = new TrainStop(null, null, secondTrain, 5, vijayavadaStation, 40);
        TrainStop train2TrainStop6 = new TrainStop(null, null, secondTrain, 6, mangaloreStation, 50);
        TrainStop train2TrainStop7 = new TrainStop(null, null, secondTrain, 7, bangaloreStation, 60);
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
        HashMap<String, Station> stations = new HashMap<>();
        stations.put("ERD", erodeStation);
        stations.put("CBE", coimbatoreStation);
        stations.put("MAS", chennaiStation);
        stations.put("TUP", tirupurStation);
        stations.put("VJA", vijayavadaStation);
        stations.put("MNG", mangaloreStation);
        stations.put("BNG", bangaloreStation);
        TimeTable  timeTable = new TimeTable(trains,stations);

        ArrayList<Train> actualTrains =  trainService.getTrains(fromStationCode,toStationCode,timeTable);

        ArrayList<Train> expectedTrains = new ArrayList<>();
        expectedTrains.add(firstTrain);



        assertEquals(expectedTrains,actualTrains);


    }
}