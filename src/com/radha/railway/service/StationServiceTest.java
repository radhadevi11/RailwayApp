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

}