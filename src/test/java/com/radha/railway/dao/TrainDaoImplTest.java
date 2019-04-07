package com.radha.railway.dao;

import com.radha.railway.Station;
import com.radha.railway.Train;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;


public class TrainDaoImplTest {

    @Test
    public void testSave(){
        TrainDaoImpl trainDao = new TrainDaoImpl();

        Train train = new Train("abc","1234",new Station(8),new Station(9));

        int actual = trainDao.save(train);

        System.out.println("The id is "+actual);
    }

    @Test
    public void testGet(){
        TrainDaoImpl trainDao = new TrainDaoImpl();

        Train actual = trainDao.get(77);

        Train expected = new Train(	"MAS - TVC SU","12695",new Station(241),new Station(433));

        assertEquals(expected,actual);


    }

    @Test
    public void testGetAll(){
        TrainDaoImpl trainDao = new TrainDaoImpl();

        List<Train> trains = trainDao.getAll();

        int actual = trains.size();

        assertEquals(78,actual);

    }

    @Test
    public void testGetForWrongStation(){
        TrainDaoImpl trainDao = new TrainDaoImpl();

        Train actual = trainDao.get(90);

        assertNull(actual);
    }

}