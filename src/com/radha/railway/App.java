package com.radha.railway;

import com.radha.railway.controller.StationModel;
import com.radha.railway.controller.TrainController;
import com.radha.railway.controller.TrainModel;
import com.radha.railway.service.NoSuchFromStationException;
import com.radha.railway.service.NoSuchToStationException;

import java.io.*;
import java.util.*;

public class App {

    public static void main(String[] args) throws IOException {
        TrainController trainController = new TrainController();
        Scanner scanner = new Scanner(System.in);

        boolean fromStationValid = true;
        boolean toStationValid = true;
        List<TrainModel> possibleTrains = null;
        do  {
            List<StationModel> destinationStations = null;
            String fromStationCode;
            do {
                System.out.println("Where are you traveling from?? :");
                System.out.println("Enter the Station code from the list below......");
                List<StationModel> fromStations = trainController.getFromStations();
                for(StationModel fromStation : fromStations) {
                    System.out.println(fromStation);
                }
                fromStationCode = scanner.next();
                System.out.println("Getting to stations for" + fromStationCode);
                try {
                    destinationStations = trainController.getToStations(fromStationCode);
                } catch (NoSuchFromStationException e) {
                    e.printStackTrace();
                    System.out.println("The from station you entered is invalid. Try again.");
                    fromStationValid = false;
                }
            }while (!fromStationValid);

           do {
               System.out.println("Where are you traveling to??:");
               System.out.println("Enter the Station code from the list below......");
               for (StationModel toStation : destinationStations) {
                   System.out.println(toStation);
               }
                String toStationCode = scanner.next();
                System.out.println("The possible trains to travel:");

                try {
                    possibleTrains = trainController.getTrains(fromStationCode, toStationCode);
                } catch (NoSuchFromStationException e) {
                    e.printStackTrace();
                    System.out.println("The from station you entered is invalid. Try again.");
                    fromStationValid = false;
                } catch(NoSuchToStationException e) {
                    e.printStackTrace();
                    System.out.println("The to station you entered is invalid. Try again.");
                    toStationValid = false;
                }
            }  while (!toStationValid);

        } while (!fromStationValid);
        for(TrainModel train : possibleTrains){
            System.out.println(train);
        }
        //TODO How to display only destination stations that are available for a fiven source station?
        //TODO How to make it easy to user to select the station??
        //TODO run the getDestinationName method from the main
        //TODO How to get destination station name?
        //TODO how to find intermideate stations between from to To  stations
    }




}


