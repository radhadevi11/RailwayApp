package com.radha.railway.dao;

import com.radha.railway.Station;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;


public class StationDaoImplTest {

    @Test
    public void testSave(){
        Station station = new Station("Chennai","ABC");

        StationDaoImpl stationDao = new StationDaoImpl();
        int id = stationDao.save(station);
        System.out.println("The id is "+id);


    }
    @Test
    public void testGetForCorrectStation(){
        Station expected = new Station("YP","ERRUPALEM");

        StationDaoImpl stationDao = new StationDaoImpl();

        Station actual = stationDao.get(465);

        assertEquals(expected,actual);

    }

    @Test
    public void testGetForWrongStation(){

        StationDaoImpl stationDao = new StationDaoImpl();

        Station actual = stationDao.get(999);

        assertNull(actual);

    }
    @Test

    public void testGetAll(){
        StationDaoImpl stationDao = new StationDaoImpl();

        List<Station> stations = stationDao.getAll();
//        assertTrue(isStationAvailable(stations));


        int actual = stations.size();


        assertEquals(467,actual);

    }

    private boolean isStationAvailable(List<Station> stations){
        for(Station station : stations){
            if(station.getCode().equals("AB")){
               return true;
            }

        }
        return false;
    }

    @Test

    public  void testGetStation(){

        StationDaoImpl stationDao = new StationDaoImpl();

        Station actual = stationDao.getStation("MAS");

        Station expected = new Station("CHENNAI CENT","MAS");

        assertEquals(expected,actual);
        assertEquals(78,actual.getTrainStops().size());

        TrainStop expectedTrainStop = new TrainStop(null,
                null,
                new Train(null,"22611",null,null),
                0,
                new Station(null,"MAS"),
                5);
        assertTrue(actual.getTrainStops().contains(expectedTrainStop));

    }

}