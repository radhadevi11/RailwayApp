package com.radha.railway;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class TimeTableEntry {
    private String trainNo;
    private String trainName;
    private int seqence;
    private String stationCode;
    private String stationName;
    private String arrivalTime;
    private String departureTime;
    private long distance;
    private String sourceStaion;
    private String sourceStaionName;
    private String destinationStation;
    private String destinationStationName;

    public TimeTableEntry() {
    }

    public TimeTableEntry(String trainNo, String trainName, int seqence, String stationCode, String stationName, String arrivalTime, String departureTime, long distance, String sourceStaion, String sourceStaionName, String destinationStation, String destinationStationName) {
        this.trainNo = trainNo;
        this.trainName = trainName;
        this.seqence = seqence;
        this.stationCode = stationCode;
        this.stationName = stationName;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
        this.distance = distance;
        this.sourceStaion = sourceStaion;
        this.sourceStaionName = sourceStaionName;
        this.destinationStation = destinationStation;
        this.destinationStationName = destinationStationName;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public String getTrainName() {
        return trainName;
    }
    public int getSeqence(){
        return seqence;
    }
    public String getStationCode(){
        return stationCode;
    }

    public String getStationName() {
        return stationName;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public long getDistance() {
        return distance;
    }

    public String getSourceStaion() {
        return sourceStaion;
    }

    public String getSourceStaionName() {
        return sourceStaionName;
    }

    public String getDestinationStation() {
        return destinationStation;
    }

    public String getDestinationStationName() {
        return destinationStationName;
    }

    public String toString(){
        return "Train No:"+getTrainNo()+ "\n" +
                "Train Name:"+getTrainName()+"\n" +
                "SEQ:"+getSeqence()+"\n" +
                "Station Code:"+getStationCode()+"\n" +
                "Station Name:"+getStationName()+"\n" +
                "Arrival time:"+getArrivalTime()+ "\n" +
                "Departure Time:"+getDepartureTime()+ "\n" +
                "Distance:"+getDistance()+"\n" +
                "Source Station:"+getSourceStaion()+"\n" +
                "Source Station Name:"+getSourceStaionName()+"\n" +
                "Destination Station:"+getDestinationStation()+ "\n" +
                "Destination Station Name:"+getDestinationStationName()+"\n";

    }


    public static ArrayList<TimeTableEntry> readFromFile(File myFile)throws FileNotFoundException, IOException {
       /*Algorithm:
       step 1:create a TimeTableEntry object
       Step2:create a arrayList of TimeTableEntry as entries
       Step3:create a bufferedReader object
       Step4:read the whole file line by line and store it in the TimeTableEntry Object
                      store the object in he ArrayList
       Step5:return the arrayList
        */

        ArrayList<TimeTableEntry> entries =new ArrayList<>();
        BufferedReader myReader = new BufferedReader(new FileReader(myFile));
        String readText;
        myReader.readLine();
        while ((readText= myReader.readLine()) != null) {
            String splitText[]= readText.split(",");
            int newSequence=Integer.parseInt(splitText[2]);
            long newDistance=Long.parseLong(splitText[7]);
            TimeTableEntry myEntry = new TimeTableEntry();
            myEntry.trainNo=splitText[0];
            myEntry.trainName=splitText[1];
            myEntry.seqence=newSequence;
            myEntry.stationCode=splitText[3];
            myEntry.stationName=splitText[4];
            myEntry.arrivalTime=splitText[5];
            myEntry.departureTime=splitText[6];
            myEntry.distance=newDistance;
            myEntry.sourceStaion=splitText[8];
            myEntry.sourceStaionName=splitText[9];
            myEntry.destinationStation=splitText[10];
            myEntry.destinationStationName=splitText[11];
            entries.add(myEntry);

        }
        return entries;

    }
    public  ArrayList<String> findAvailableTrains (ArrayList<TimeTableEntry> timeTableEntries,String destinationStationCode,String sourceStaionCode){
        ArrayList<String> trainResult = new ArrayList<>();
        boolean foundFromStation = false;
        String trainNo ="";
        for(TimeTableEntry currentEntry : timeTableEntries){
            if(foundFromStation == true){
                if(currentEntry.getTrainNo() == trainNo){
                    if(currentEntry.getStationCode() == destinationStationCode){
                        trainResult.add(currentEntry.getTrainNo());
                        foundFromStation = false;
                    }
                    else {
                        continue;
                    }
                }
                else {
                    foundFromStation = false;
                    continue;
                }
            }
            else {
                if(currentEntry.getStationCode() == sourceStaionCode){
                    trainNo = currentEntry.getTrainNo();
                    foundFromStation = true;
                    continue;

                }
                else {
                    continue;
                }
            }

        }
        return trainResult;
    }



}
