package com.radha.railway;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class App {
    private static ArrayList<Train> trains = new ArrayList<>();
    private static HashMap<String,Station> stations = new HashMap<>();
    public static void main(String[] args) throws IOException {
        File csv = new File("C:\\Users\\radha\\Downloads\\ChennaiCentralTimetable.csv");
        ArrayList<TimeTableEntry> myEntries = TimeTableEntry.readFromFile(csv);
//        for (TimeTableEntry newEntry : myEntries) {
//            System.out.println(newEntry);
//        }
        /*HashSet<String> stationNames = getStationNames(myEntries);
        System.out.println("The Station names are");
        for (String eachName : stationNames) {
            System.out.println(eachName);
        }*/
        HashMap<String,String> uniqueStationNames =  getStationNamesAsMap(myEntries);
        System.out.println("Where are you traveling from?? :");
        System.out.println("Enter the Station code from the list below......");
        for(String code: uniqueStationNames.keySet())
        {
            System.out.println("Code:"+code+ " stationName: "+uniqueStationNames.get(code));
        }
        Scanner scanner = new Scanner(System.in);
        String fromStationCode = scanner.next();
        HashMap<String,String> destinationStations = getDestinationStationNames(fromStationCode,uniqueStationNames);
        System.out.println("Where are you traveling to??:");
        System.out.println("Enter the Station code from the list below......");
        for (String code : destinationStations.keySet()){
            System.out.println("Code:"+code+" stationName:"+destinationStations.get(code));
        }
        String toStationCode = scanner.next();
        //TODO How to display only destination stations that are available for a fiven source station?
        //TODO How to make it easy to user to select the station??
        //TODO run the getDestinationName method from the main
        //TODO How to get destination station name?
        //TODO how to find intermideate stations between from to To  stations
    }
    public static HashSet<String> getStationNames(ArrayList<TimeTableEntry> timeTableEntries){
        /*Algorithm:
        Step 1:create a new HashSet object as stationNames
        Step 2:For each entry in the TimeTableEntries do
                   a)Declare a String variable getName,get the Station name from the Entry and assaign it to getName
                   b)add the StationName into the HashSet
        Step 3:Return the HashSet.
         */
        HashSet<String> stationNames = new HashSet<>();
        for(TimeTableEntry eachEntry : timeTableEntries){
            String stationName = eachEntry.getStationName();
            String stationCode = eachEntry.getStationCode();
            stationNames.add(stationName);

        }
        return stationNames;
    }
    public static HashMap<String,String> getStationNamesAsMap(ArrayList<TimeTableEntry> timeTableEntries){
        /*Algorithm:
        Step 0: Initialize a map of station names keyed by staion code
        Step 1:For each entry in the TimeTableEntries do
                   a)Put the station code and station name of the entry into the map
        Step 3:Return the map.
         */
        HashMap<String,String> stationNamesAndCodes = new HashMap<>();
        for(TimeTableEntry eachEntry : timeTableEntries){
            String stationName = eachEntry.getStationName();
            String stationCode = eachEntry.getStationCode();
            stationNamesAndCodes.put(stationCode,stationName);
        }
        return stationNamesAndCodes;
    }
    public  static HashMap<String,String> getDestinationStationNames(String fromStationCode,
                                                                     HashMap<String,String> uniqueStations){

        //hashing fn = get first letter
        //a = create a bucket and put the key in that bucket
        //d = crea def, dff
        //

        // abc abc def ghi
        //Set

        /*Algorithm
        Step0:Declare a map as destinationStationNamesAndCode
        Step1:For each code in the uniqueStation map do
                    i)Compare with the fromStationCode
                    ii)If the code is match with the fromStationCode then continue.
                    iii)otherwise add the Station code and the name into the destinationStationNamesAndCode map.
        Step2:return the destinationStationNamesAndCode map.
        */
        HashMap<String,String> destinationStationNamesAndCode = new HashMap<>();

        for(String stationCode : uniqueStations.keySet()){
            if(!fromStationCode.equals(stationCode)) {
                destinationStationNamesAndCode.put(stationCode,uniqueStations.get(stationCode));
            }
        }
        return destinationStationNamesAndCode;

    }

/*Steps:
      1)get the from station code eg:11
      2)get the to station code   eg:33
      3)dispaly the intermidiate station code names ie,12-32
 */
/*Algorithm:
     Step1:for each train(Different trains for source to Destination) in the given entries do
              i)get the source station sequence number.
              ii)get the destination station sequence number.
              iii)for each sequence from source to destination do
                        a)get the corresponding station name
                        b)store it in the map
     Step2:return the map.*/
//TODO It will create a two lists(ie)List of trains and map of stations
    public static void loadFromFile(File myFile){
        //similar to readFromFile
        //get the stations and trains
    }





}


