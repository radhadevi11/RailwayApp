package com.radha.railway;

import com.radha.railway.controller.StationModel;
import com.radha.railway.controller.TrainController;
import com.radha.railway.controller.TrainModel;
import com.radha.railway.dao.TimeTableDao;
import com.radha.railway.service.NoSuchFromStationException;
import com.radha.railway.service.NoSuchToStationException;

import java.io.*;
import java.util.*;

public class App {
    public static void main(String[] args) throws IOException {
        TrainController trainController = new TrainController();
        Scanner scanner = new Scanner(System.in);

        boolean fromStationValid;
        boolean toStationValid;
        List<TrainModel> possibleTrains;
        List<StationModel> destinationStations = null;
        String fromStationCode;
        do {
            fromStationValid = true;
            fromStationCode = getFromStationFromUser(trainController, scanner);
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
            toStationValid = true;
            String toStationCode = getToStationFromUser(scanner, destinationStations);
            System.out.println("The possible trains to travel:");

            try {
                possibleTrains = trainController.getTrains(fromStationCode, toStationCode);
                System.out.println("Possible trains are:");
                for(TrainModel trainModel: possibleTrains) {
                    System.out.println(trainModel);
                }
            } catch (NoSuchFromStationException e) {
                e.printStackTrace();
                System.out.println("The from station you entered is invalid. Try again.");
                fromStationValid = false;
            } catch(NoSuchToStationException e) {
                e.printStackTrace();
                System.out.println("The to station you entered is invalid. Try again.");
                toStationValid = false;
            }
            //
        }  while (!toStationValid);

    }

    private static String getToStationFromUser(Scanner scanner, List<StationModel> destinationStations) {
        System.out.println("Where are you traveling to??:");
        System.out.println("Enter the Station code from the list below......");
        for (StationModel toStation : destinationStations) {
            System.out.println(toStation);
        }
        return scanner.next();
    }

    private static String getFromStationFromUser(TrainController trainController, Scanner scanner) throws IOException {
        String fromStationCode;
        System.out.println("Where are you traveling from?? :");
        System.out.println("Enter the Station code from the list below......");
        List<StationModel> fromStations = trainController.getFromStations();
        for(StationModel fromStation : fromStations) {
            System.out.println(fromStation);
        }
        fromStationCode = scanner.next();
        return fromStationCode;
    }
}







