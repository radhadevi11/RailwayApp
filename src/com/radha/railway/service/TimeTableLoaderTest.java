package com.radha.railway.service;

import com.radha.railway.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableLoaderTest {
    @Test
    void testLoadFromFile() throws IOException {
        TimeTableLoader myLoader = new TimeTableLoader();
        String oneLine = "Train No,Train Name,SEQ,Station Code,Station Name,Arrival time,Departure Time,Distance,Source Station,Source Station Name,Destination Station,Destination Station Name\n" +
                "2842,MAS SRC SPL,1,MAS,CHENNAI CENT,18:20:00,18:20:00,0,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.";
        BufferedReader input = new BufferedReader(new StringReader(oneLine));
        TimeTable actual = myLoader.loadFromFile(input);

        List<Train> expectedTrains = new ArrayList<>();
        Station expectedStation = new Station("CHENNAI CENT", "MAS");
        Train expectedTrain = new Train("MAS SRC SPL", "2842",
                new Station("CHENNAI CENT", "MAS"),
                new Station("SANTRAGACHI JN.", "SRC"));
        TrainStop expectedTrainStop = new TrainStop("18:20:00","18:20:00",expectedTrain,1,expectedStation,0);
        expectedTrains.add(expectedTrain);
        assertEquals(expectedTrains, actual.getTrains());
        expectedStation.addTrainStop(expectedTrainStop);
        HashMap<String,Station> expectedStations = new HashMap<>();
        expectedStations.put("MAS",
               expectedStation);
        assertEquals(expectedStations,actual.getStations());
    }
    @Test
    void testLoadFromFileForMultipleStations() throws IOException {
        TimeTableLoader myLoader = new TimeTableLoader();
        String twoEntries = "Train No,Train Name,SEQ,Station Code,Station Name,Arrival time,Departure Time,Distance,Source Station,Source Station Name,Destination Station,Destination Station Name\n" +
                "2842,MAS SRC SPL,1,MAS,CHENNAI CENT,18:20:00,18:20:00,0,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.\n" +
                "2842,MAS SRC SPL,2,NLR,NELLORE,21:40:00,21:42:00,175,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.";
        BufferedReader input = new BufferedReader(new StringReader(twoEntries));
        List<Train> expectedTrains = new ArrayList<>();
        Station expectedStation1 = new Station("CHENNAI CENT","MAS");
        Station expectedStation2 = new Station("NELLORE","NLR");
        Train expectedTrain = new Train("MAS SRC SPL", "2842",
                new Station("CHENNAI CENT", "MAS"),
                new Station("SANTRAGACHI JN.", "SRC"));
        TrainStop expectedTrainStop1 = new TrainStop("18:20:00","18:20:00",expectedTrain,1,expectedStation1,0);
        TrainStop expectedTrainStop2 = new TrainStop("21:40:00","21:42:00",expectedTrain,2,expectedStation2,0);
        expectedTrains.add(expectedTrain);
        expectedTrain.addTrainStop(expectedTrainStop1);
        expectedTrain.addTrainStop(expectedTrainStop2);
        HashMap<String,Station> expectedStations = new HashMap<>();
        expectedStations.put("MAS",expectedStation1);
        expectedStations.put("NLR", expectedStation2);
        expectedStation1.addTrainStop(expectedTrainStop1);
        expectedStation2.addTrainStop(expectedTrainStop2);

        TimeTable actual =  myLoader.loadFromFile(input);

        assertEquals(expectedTrains, actual.getTrains());
        assertEquals(expectedStations,actual.getStations());
    }
    @Test
    void testLoadFromFileForMultipleStationsAndTrains() throws IOException {
        TimeTableLoader myLoader = new TimeTableLoader();
        String twoTrains = "Train No,Train Name,SEQ,Station Code,Station Name,Arrival time,Departure Time,Distance,Source Station,Source Station Name,Destination Station,Destination Station Name\n" +
                "2842,MAS SRC SPL,1,MAS,CHENNAI CENT,18:20:00,18:20:00,0,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.\n" +
                "2842,MAS SRC SPL,2,NLR,NELLORE,21:40:00,21:42:00,175,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.\n" +
                "6041,MAS-QLN SPEC,1,MAS,CHENNAI CENT,18:20:00,18:20:00,0,MAS,CHENNAI CENTRAL,QLN,QUILON";
        BufferedReader input = new BufferedReader(new StringReader(twoTrains));
        List<Train> expectedTrains = new ArrayList<>();
        Station expectedStation1 = new Station("CHENNAI CENT","MAS");
        Station expectedStation2 = new Station("NELLORE","NLR");
        Train expectedTrain = new Train("MAS SRC SPL", "2842",
                new Station("CHENNAI CENT", "MAS"),
                new Station("SANTRAGACHI JN.", "SRC"));
        Train expectedTrain2 = new Train("MAS-QLN SPEC","6041",
               new Station("CHENNAI CENT", "MAS"),
                new Station("QUILON","QLN"));
        TrainStop expectedTrainStop1 = new TrainStop("18:20:00","18:20:00",expectedTrain,1,expectedStation1,0);
        TrainStop expectedTrainStop2 = new TrainStop("21:40:00","21:42:00",expectedTrain,2,expectedStation2,175);
        TrainStop expectedTrainStop3 = new TrainStop("18:20:00","18:20:00",expectedTrain2,1,expectedStation1,0);
        expectedTrains.add(expectedTrain);
        expectedTrains.add(expectedTrain2);
        HashMap<String,Station> expectedStations = new HashMap<>();
        expectedStations.put("MAS",expectedStation1);
        expectedStations.put("NLR", expectedStation2);
        expectedStation1.addTrainStop(expectedTrainStop1);
        expectedStation2.addTrainStop(expectedTrainStop2);
        expectedStation1.addTrainStop(expectedTrainStop3);

        TimeTable actual =  myLoader.loadFromFile(input);

        assertEquals(expectedTrains, actual.getTrains());
        assertEquals(expectedStations,actual.getStations());

    }

}