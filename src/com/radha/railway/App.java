package com.radha.railway;

import java.io.*;
import java.util.*;

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

/*Algorithm
  Chennai - santragachi(1-12)(each station of the train is a train stop)
Step0.1:Initialize an empty Train object(currentTrain) and inizialize stations map and initialize a trais list.
Step0:For each Line in the File do
Step1:Create a Station.
Step1.1:Store the station code and Station into the map.
Step2:if currentTrain is empty OR this trainNo is NOT equal to the currentTrain trainNo
	Step2.1:Create a sourceStation
	Step2.2:Create a Destination Station
	Ste2.3:Create a Train and store it into the curentTrain

Step3:Create a TrainStop
Step4:Add TrainStop to currentTrain
Step5:Add the currentTrain to the trains list.
 */

    //TODO It will create a two lists(ie)List of trains and map of stations
    public static void loadFromFile(File myFile) throws IOException {
        loadFromFile(new BufferedReader(new FileReader(myFile)));
    }

    //TODO It will create a two lists(ie)List of trains and map of stations
    public static void loadFromFile(BufferedReader myReader) throws IOException {
        //similar to readFromFile
        //get the stations and trains
        Train currentTrain = null ;
        String readText;
        myReader.readLine();
        while ((readText= myReader.readLine()) != null) {
            String splitText[]= readText.split(",");
            int newSequence=Integer.parseInt(splitText[2]);
            long newDistance=Long.parseLong(splitText[7]);
            Station currentStaion = new Station(splitText[4],splitText[3]);
            stations.put(currentStaion.getCode(),currentStaion);
            if(currentTrain == null || !currentTrain.getNumber().equals(splitText[0])){
                Station sourceStation = new Station(splitText[9],splitText[8]);
                Station destinationStation = new Station(splitText[11],splitText[10]);
                currentTrain = new Train(splitText[1],splitText[0],
                        sourceStation,destinationStation);

            }
            TrainStop trainStop = new TrainStop(splitText[5],splitText[6],
                    currentTrain,newSequence,currentStaion,newDistance);
            currentTrain.addTrainStop(trainStop);
            trains.add(currentTrain);

        }


    }


    public static List<Train> getTrains() {
        return trains;
    }

    public static Map<String, Station> getStations() {
        return  stations;
    }

}


