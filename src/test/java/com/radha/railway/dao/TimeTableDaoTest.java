package com.radha.railway.dao;

import com.radha.railway.Station;
import com.radha.railway.TimeTable;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import com.radha.railway.LatLng;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TimeTableDaoTest {
    @Test
    void testLoadFromFilewithLatLng() throws IOException {
        TimeTableDao timeTableDao = new TimeTableDao();
        String oneLine = "Train No,Train Name,SEQ,Station Code,Station Name,Arrival time,Departure Time,Distance,Source Station,Source Station Name,Destination Station,Destination Station Name\n" +
                "2842,MAS SRC SPL,1,MAS,CHENNAI CENT,18:20:00,18:20:00,0,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.";
        BufferedReader input = new BufferedReader(new StringReader(oneLine));
        HashMap<String,LatLng> actualLatLng = new HashMap<>();
        LatLng masLatLng = new LatLng(10, 20);
        actualLatLng.put("MAS", masLatLng);
        actualLatLng.put("SRC",new LatLng(40,30));

        List<Train> expectedTrains = new ArrayList<>();
        Station expectedStation = new Station("CHENNAI CENT", "MAS", masLatLng);
        Train expectedTrain = new Train("MAS SRC SPL", "2842",
                new Station("CHENNAI CENT", "MAS"),
                new Station("SANTRAGACHI JN.", "SRC"));
        TrainStop expectedTrainStop = new TrainStop("18:20:00","18:20:00",expectedTrain,1,expectedStation,0);
        expectedTrains.add(expectedTrain);

        TimeTable actual = timeTableDao.loadFromFile(input,actualLatLng);


        assertEquals(expectedTrains, actual.getTrains());
        expectedStation.addTrainStop(expectedTrainStop);
        HashMap<String,Station> expectedStations = new HashMap<>();
        expectedStations.put("MAS",
                expectedStation);
        assertEquals(expectedStations,actual.getStations());
    }
    @Test
    void testLoadFromFileForMultipleStationsWithLatLng() throws IOException {
        TimeTableDao timeTableDao = new TimeTableDao();
        String twoEntries = "Train No,Train Name,SEQ,Station Code,Station Name,Arrival time,Departure Time,Distance,Source Station,Source Station Name,Destination Station,Destination Station Name\n" +
                "2842,MAS SRC SPL,1,MAS,CHENNAI CENT,18:20:00,18:20:00,0,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.\n" +
                "2842,MAS SRC SPL,2,NLR,NELLORE,21:40:00,21:42:00,175,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.";
        BufferedReader input = new BufferedReader(new StringReader(twoEntries));
        HashMap<String,LatLng> actualLatLng = new HashMap<>();
        actualLatLng.put("MAS",new LatLng(10,20));
        actualLatLng.put("NLR",new LatLng(20,30));
        actualLatLng.put("SRC",new LatLng(40,30));


        List<Train> expectedTrains = new ArrayList<>();
        Station expectedStation1 = new Station("CHENNAI CENT","MAS",new LatLng(10,20));
        Station expectedStation2 = new Station("NELLORE","NLR",new LatLng(20,30));
        Train expectedTrain = new Train("MAS SRC SPL", "2842",
                new Station("CHENNAI CENT", "MAS",new LatLng(10,20)),
                new Station("SANTRAGACHI JN.", "SRC",new LatLng(40,30)));
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

        TimeTable actual =  timeTableDao.loadFromFile(input,actualLatLng);

        assertEquals(expectedTrains, actual.getTrains());
        assertEquals(expectedStations,actual.getStations());
    }
    @Test
    void testLoadFromFileForMultipleStationsAndTrainsWithLatLng() throws IOException {
        TimeTableDao timeTableDao = new TimeTableDao();
        String twoTrains = "Train No,Train Name,SEQ,Station Code,Station Name,Arrival time,Departure Time,Distance,Source Station,Source Station Name,Destination Station,Destination Station Name\n" +
                "2842,MAS SRC SPL,1,MAS,CHENNAI CENT,18:20:00,18:20:00,0,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.\n" +
                "2842,MAS SRC SPL,2,NLR,NELLORE,21:40:00,21:42:00,175,MAS,CHENNAI CENTRAL,SRC,SANTRAGACHI JN.\n" +
                "6041,MAS-QLN SPEC,1,MAS,CHENNAI CENT,18:20:00,18:20:00,0,MAS,CHENNAI CENTRAL,QLN,QUILON";
        BufferedReader input = new BufferedReader(new StringReader(twoTrains));
        HashMap<String,LatLng> actualLatLng = new HashMap<>();
        actualLatLng.put("MAS",new LatLng(10,20));
        actualLatLng.put("NLR",new LatLng(20,30));
        actualLatLng.put("SRC",new LatLng(40,30));

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

        TimeTable actual =  timeTableDao.loadFromFile(input,actualLatLng);

        assertEquals(expectedTrains, actual.getTrains());
        assertEquals(expectedStations,actual.getStations());

    }
    @Test
    void testLoadLatLng() throws IOException{
        TimeTableDao timeTableDao = new TimeTableDao();
        String oneLine = "Station Code,Latitude,Longitude\n"+
                         "NZM,28.5913871,77.2418643 \n"+
                         "IPL,10.0236761,76.3116235";
        BufferedReader input = new BufferedReader(new StringReader(oneLine));
        HashMap<String,LatLng> expected = new HashMap<>();
        expected.put("NZM",new LatLng(28.5913871,77.2418643));
        expected.put("IPL",new LatLng(10.0236761,76.3116235));


        Map<String,LatLng> actual = timeTableDao.loadLatLngFile(input);


        assertEquals(expected,actual);
    }

}