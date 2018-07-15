package com.radha.railway;

import com.radha.railway.controller.TrainController;

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException {
        TrainController trainController = new TrainController();

        System.out.println("Where are you traveling from?? :");
        System.out.println("Enter the Station code from the list below......");
        Map<String, Station> fromStations = trainController.getFromStations();
        for(String code: fromStations.keySet())
        {
            System.out.println("Code:"+code+ " stationName: "+fromStations.get(code));
        }
        Scanner scanner = new Scanner(System.in);
        String fromStationCode = scanner.next();
        System.out.println("Getting to stations for"  + fromStationCode);
        Map<String, Station> destinationStations = trainController.getToStations(fromStationCode);
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




}


