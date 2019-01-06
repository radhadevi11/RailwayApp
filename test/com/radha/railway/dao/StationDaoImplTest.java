package com.radha.railway.dao;

import com.radha.railway.Station;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationDaoImplTest {

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
    public void testGetForWronglStation(){

        StationDaoImpl stationDao = new StationDaoImpl();

        Station actual = stationDao.get(999);

        assertNull(actual);

    }

}