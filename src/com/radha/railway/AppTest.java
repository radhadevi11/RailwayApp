package com.radha.railway;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    public void testGetStationNames(){
        //setup

        ArrayList<TimeTableEntry> timeTableEntries = new ArrayList<>();
        TimeTableEntry entry1 = new TimeTableEntry(null, null, 0, null,
                "stationA", null, null, 0, null, null, null, null);
        TimeTableEntry entry2 = new TimeTableEntry(null, null, 0, null,
                "stationB", null, null, 0, null, null, null, null);
        timeTableEntries.add(entry1);
        timeTableEntries.add(entry2);
        //invoke the test method

        HashSet<String> stationNames = App.getStationNames(timeTableEntries);

        //assert
        HashSet<String> expected = new HashSet<>();
        expected.add("stationA");
        expected.add("stationB");
        assertEquals(expected, stationNames);
    }

    @Test
    public void testGetStationNamesWIthSameNamesInEntries(){
        //setup

        ArrayList<TimeTableEntry> timeTableEntries = new ArrayList<>();
        TimeTableEntry entry1 = new TimeTableEntry(null, null, 0, null,
                "stationA", null, null, 0, null, null, null, null);
        TimeTableEntry entry2 = new TimeTableEntry(null, null, 0, null,
                "stationA", null, null, 0, null, null, null, null);
        timeTableEntries.add(entry1);
        timeTableEntries.add(entry2);
        //invoke the test method

        HashSet<String> stationNames = App.getStationNames(timeTableEntries);

        //assert
        HashSet<String> expected = new HashSet<>();
        expected.add("stationA");
        assertEquals(expected, stationNames);
    }
    @Test
    public void testGetStationNamesAsMapWIthSameNamesInEntries(){
        //setup

        ArrayList<TimeTableEntry> timeTableEntries = new ArrayList<>();
        TimeTableEntry entry1 = new TimeTableEntry(null, null, 0, "STNA",
                "stationA", null, null, 0, null, null, null, null);
        TimeTableEntry entry2 = new TimeTableEntry(null, null, 0, "STNB",
                "stationB", null, null, 0, null, null, null, null);
        TimeTableEntry entry3 = new TimeTableEntry(null, null, 0, "STNB",
                "stationB", null, null, 0, null, null, null, null);
        timeTableEntries.add(entry1);
        timeTableEntries.add(entry2);
        timeTableEntries.add(entry3);
        //invoke the test method

        HashMap<String,String> stationNames = App.getStationNamesAsMap(timeTableEntries);

        //assert
        HashMap<String,String> expected = new HashMap<>();
        expected.put("STNA","stationA");
        expected.put("STNB","stationB");
        assertEquals(expected, stationNames);
    }
    @Test
    public void testGetDestinationStationNames(){
        //setup
        String fromStationCode = "NZM";
        HashMap<String,String> uniqueStations = new HashMap<>();
        uniqueStations.put("IPL","IDAPPALLI");
        uniqueStations.put("IPM","ICHCHA PURAM");
        uniqueStations.put("NZM","HAZRAT NIZAM");
        //invoke the test method

        HashMap<String, String> actual = App.getDestinationStationNames(
                fromStationCode,
                uniqueStations);
        HashMap<String,String> expected = new HashMap<>();
        expected.put("IPL","IDAPPALLI");
        expected.put("IPM","ICHCHA PURAM");
        assertEquals(expected,actual);



    }
    @Test
    public void testGetDestinationStationNamesWithFromStationCodeNotPresent(){
        //setup
        String fromStationCode = "NZM";
        HashMap<String,String> uniqueStations = new HashMap<>();
        uniqueStations.put("IPL","IDAPPALLI");
        uniqueStations.put("IPM","ICHCHA PURAM");
        uniqueStations.put("ABC","HAZRAT NIZAM");

        //invoke the test method

        HashMap<String, String> actual = App.getDestinationStationNames(
                fromStationCode,
                uniqueStations);
        HashMap<String,String> expected = new HashMap<>();
        expected.put("IPL","IDAPPALLI");
        expected.put("IPM","ICHCHA PURAM");
        expected.put("ABC","HAZRAT NIZAM");
        assertEquals(expected,actual);



    }
}