package com.radha.railway.service;

import com.radha.railway.Station;
import com.radha.railway.Train;
import com.radha.railway.dao.StationDaoImpl;
import com.radha.railway.dao.TrainDaoImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LoaderServiceTest {

    public static final String TRAIN_NO = "1234";
    public static final String TRAIN_NAME = "Chennai Central";
    public static final String STATION_CODE = "MAS";
    public static final String STATION_NAME = "Chennai Central";



    @Test
    public void testSaveTrain(){
        TrainDaoImpl trainDao = Mockito.mock(TrainDaoImpl.class);
        LoaderService loaderService = new LoaderService(null, trainDao, null);

        Station sourceStation = Mockito.mock(Station.class);
        Station destStation = Mockito.mock(Station.class);
        Train actual = loaderService.saveTrain(TRAIN_NO, TRAIN_NAME, sourceStation, destStation);

        assertEquals(TRAIN_NO, actual.getNumber());
        assertEquals(TRAIN_NAME, actual.getName());
        assertSame(sourceStation, actual.getSourceStation());
        assertSame(destStation, actual.getDestinationStation());
        Mockito.verify(trainDao).save(actual);
    }

    @Test
    public void testSaveStationNotInTheMap(){
        StationDaoImpl stationDao = Mockito.mock(StationDaoImpl.class);
        LoaderService loaderService = new LoaderService(stationDao, null, null);

        Map<String,Station> stationMap = new HashMap<>();


        Station actual = loaderService.saveStation(STATION_NAME,STATION_CODE,stationMap);

        assertTrue(stationMap.containsKey(STATION_CODE));
        assertEquals(stationMap.get(STATION_CODE), actual);
        Mockito.verify((stationDao)).save(actual);
        assertEquals(STATION_CODE, actual.getCode());
        assertEquals(STATION_NAME, actual.getName());

    }

}