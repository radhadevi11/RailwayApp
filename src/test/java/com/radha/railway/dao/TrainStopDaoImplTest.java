package com.radha.railway.dao;

import com.radha.railway.Station;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainStopDaoImplTest {
    @Test
    public void testSave(){
        TrainStopDaoImpl trainStopDao = new TrainStopDaoImpl();

        TrainStop trainStop = new TrainStop(
                "20:25:30",
                "20:30:30",
                new Train(4), 50,
                new Station(90),0);

        int id = trainStopDao.save(trainStop);
        System.out.println("The train stop id is "+id);
    }

    @Test
    public void testGet(){
        TrainStopDaoImpl trainStopDao = new TrainStopDaoImpl();

        TrainStop actual = trainStopDao.get(33);

        TrainStop expected = new TrainStop(33,"11:35:00","11:40:00",
                new Train(4,null,"12656",null,null),33,new Station("aaa","NDB"),0);

        assertEquals(expected,actual);




    }

    public void testGetAll(){
        TrainStopDaoImpl trainStopDao = new TrainStopDaoImpl();

        List<TrainStop> trainStops = trainStopDao.getAll();
        int actual = trainStops.size();
        assertEquals(1465,actual);

    }

}