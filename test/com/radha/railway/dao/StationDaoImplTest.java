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

}