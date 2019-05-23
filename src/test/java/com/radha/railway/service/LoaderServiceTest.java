package com.radha.railway.service;

import com.radha.railway.Station;
import com.radha.railway.TimeTableEntry;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import com.radha.railway.dao.StationDaoImpl;
import com.radha.railway.dao.TrainDaoImpl;
import com.radha.railway.dao.TrainStopDaoImpl;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class LoaderServiceTest {

    public static final String TRAIN_NO = "1234";
    public static final String TRAIN_NAME = "Chennai Central";
    public static final String STATION_CODE = "MAS";
    public static final String STATION_NAME = "Chennai Central";
    public static final String SOURCE_STATION_CODE = "ED";
    public static final String SOURCE_STATION_NAME = "ERODE";
    public static final String DEST_STATION_CODE = "CBE";
    public static final String DEST_STATION_NAME = "COIMBATORE";


    @InjectMocks
    @Spy
    private LoaderService loaderService;
    @Mock
    private StationDaoImpl stationDao;
    @Mock
    private TrainDaoImpl trainDao;
    @Mock
    private TrainStopDaoImpl trainStopDao;
    @Mock
    private TimeTableEntry timeTableEntry;

    @Before
    public void setUp() {

        doReturn(TRAIN_NO).when(timeTableEntry).getTrainNo();
        doReturn(TRAIN_NAME).when(timeTableEntry).getTrainName();
        doReturn(SOURCE_STATION_CODE).when(timeTableEntry).getSourceStationCode();
        doReturn(SOURCE_STATION_NAME).when(timeTableEntry).getSourceStationName();
        doReturn(DEST_STATION_NAME).when(timeTableEntry).getDestinationStationName();
        doReturn(DEST_STATION_CODE).when(timeTableEntry).getDestinationStationCode();
        doReturn(STATION_CODE).when(timeTableEntry).getStationCode();
        doReturn(STATION_NAME).when(timeTableEntry).getStationName();

    }

    @Test
    public void testSaveTrainNotInTheMap() {

        Map<String, Train> trains = new HashMap<>();

        Map<String, Station> stations = mock(Map.class);
        Station sourceStation = mock(Station.class);
        doReturn(sourceStation).when(loaderService).saveStation(SOURCE_STATION_NAME, SOURCE_STATION_CODE, stations);
        Station destStation = Mockito.mock(Station.class);
        doReturn(destStation).when(loaderService).saveStation(DEST_STATION_NAME, DEST_STATION_CODE, stations);

        Train actual = loaderService.saveTrain(trains, timeTableEntry, stations);


        assertEquals(TRAIN_NO, actual.getNumber());
        assertEquals(TRAIN_NAME, actual.getName());
        assertThat(actual.getSourceStation()).isSameAs(sourceStation);
        assertThat(actual.getDestinationStation()).isSameAs(destStation);
        Mockito.verify(trainDao).save(actual);
        assertThat(trains).containsExactly(entry(TRAIN_NO, actual));
    }

    @Test
    public void testSaveTrainPresentInTheMap() {
        Map<String, Train> trains = new HashMap<>();
        Train expected = new Train(TRAIN_NAME, TRAIN_NO, null, null);
        trains.put(TRAIN_NO, expected);
        Map<String, Station> stations = mock(Map.class);
        TimeTableEntry timeTableEntry = mock(TimeTableEntry.class);
        doReturn(TRAIN_NO).when(timeTableEntry).getTrainNo();

        Train actual = loaderService.saveTrain(trains, timeTableEntry, stations);

        assertThat(actual).isSameAs(expected);
    }

    @Test
    public void testSaveStationNotInTheMap() {


        Map<String, Station> stationMap = new HashMap<>();


        Station actual = loaderService.saveStation(STATION_NAME, STATION_CODE, stationMap);

        assertTrue(stationMap.containsKey(STATION_CODE));
        assertEquals(stationMap.get(STATION_CODE), actual);
        Mockito.verify((stationDao)).save(actual);
        assertEquals(STATION_CODE, actual.getCode());
        assertEquals(STATION_NAME, actual.getName());

    }

    @Test
    public void testSaveStationPresentInTheMap() {

        Map<String, Station> stationMap = new HashMap<>();
        stationMap.put("MAS", new Station("Chennai Central", "MAS"));


        Station actual = loaderService.saveStation(STATION_NAME, STATION_CODE, stationMap);

        assertTrue(stationMap.containsKey(STATION_CODE));
        assertEquals(stationMap.get(STATION_CODE), actual);
        Mockito.verify(stationDao, Mockito.never()).save(actual);
        assertEquals(STATION_CODE, actual.getCode());
        assertEquals(STATION_NAME, actual.getName());

    }

    /* public void testSave(){
         TrainDaoImpl trainDao = Mockito.mock(TrainDaoImpl.class);
         StationDaoImpl stationDao = Mockito.mock(StationDaoImpl.class);
         TrainStopDaoImpl trainStopDao = Mockito.mock(TrainStopDaoImpl.class);

         LoaderService loaderService = new LoaderService(stationDao,trainDao,trainStopDao);

         BufferedReader reader = new BufferedReader(new InputStreamReader();
     }*/
    @Test
    public void testLoad() throws IOException {
        BufferedReader reader = Mockito.mock(BufferedReader.class);
        Mockito.when(reader.readLine()).thenReturn("header", "line1", "line2", null);

        Mockito.doNothing().when(loaderService).saveOneRow(Mockito.anyMap(), Mockito.anyMap(), Mockito.eq("line1"));
        Mockito.doNothing().when(loaderService).saveOneRow(Mockito.anyMap(), Mockito.anyMap(), Mockito.eq("line2"));

        loaderService.load(reader, null);

        Mockito.verify(loaderService).saveOneRow(new HashMap<>(), new HashMap<>(), "line1");
        Mockito.verify(loaderService).saveOneRow(new HashMap<>(), new HashMap<>(), "line2");


    }

    @Test
    public void testOneRow() {

        Map<String, Station> stations = mock(Map.class);
        Map<String, Train> trains = mock(Map.class);
        String readText = "text";
        doReturn(timeTableEntry).when(loaderService).getTimeTableEntry("text");
        Station station = mock(Station.class);
        Train train = mock(Train.class);
        doReturn(station).when(loaderService).saveStation(STATION_NAME, STATION_CODE, stations);
        doReturn(train).when(loaderService).saveTrain(trains, timeTableEntry, stations);
        doNothing().when(loaderService).saveTrainStop(timeTableEntry, station, train);

        loaderService.saveOneRow(stations, trains, readText);

        verify(loaderService).saveTrainStop(timeTableEntry, station, train);


    }

    @Test
    public void testSaveTrainStop() {


        Train currentTrain = mock(Train.class);
        Station currentStation = mock(Station.class);
        TrainStop trainStop = new TrainStop(null, null, currentTrain, 5, currentStation, 123);

        loaderService.saveTrainStop(timeTableEntry, currentStation, currentTrain);

        verify(trainStopDao).save(trainStop);

    }

   
}