package com.radha.railway.controller;

import com.radha.railway.Station;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import com.radha.railway.service.NoSuchFromStationException;
import com.radha.railway.service.NoSuchToStationException;
import com.radha.railway.service.StationService;
import com.radha.railway.service.TrainService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TrainController {
    private static StationService stationService = new StationService();
    private static TrainService trainService = new TrainService();

    public List<StationModel> getFromStations() throws IOException {
        Map<String, Station> fromStations = stationService.getFromStations();
        return convertToStationModels(fromStations.values());

    }

    public List<StationModel> getToStations(String fromStationCode) throws IOException, NoSuchFromStationException {
        Map<String, Station> toStations =stationService.getToStations(fromStationCode);
        return convertToStationModels(toStations.values());
    }


    private ArrayList<StationModel> convertToStationModels(Collection<Station> stations) {
        ArrayList<StationModel> stationModels = new ArrayList<>();
        for(Station station : stations){
            stationModels.add(convertToStationModel(station));

        }
        return stationModels;
    }
    private StationModel convertToStationModel(Station station) {
        return new StationModel(station.getName(),station.getCode(),station.getLatLng());
    }

    public List<TrainModel> getTrains(String fromStationCode,String toStationCode) throws IOException, NoSuchFromStationException, NoSuchToStationException {
       List<Train> trains =  trainService.getTrains(fromStationCode,toStationCode);
       ArrayList<TrainModel> trainModels = new ArrayList<>();
       for(Train train : trains){
          trainModels.add(convertToTrainModel(train));

       }
       return trainModels;
    }

    private TrainModel convertToTrainModel(Train train) {
        Station sourceStation = train.getSourceStation();
        StationModel sourceStationModel = convertToStationModel(sourceStation);
        Station destinationStation = train.getDestinationStation();
        StationModel destinationStationModel = convertToStationModel(destinationStation);
        ArrayList<TrainStop> trainStops = train.getTrainStops();
        List<TrainStopModel> trainStopModels = convertToTrainStopModels(trainStops);
        return new TrainModel(train.getName(),train.getNumber(),sourceStationModel,destinationStationModel,trainStopModels);


    }

    private TrainStopModel convertToTrainStopModel(TrainStop trainStop) {
        Station station = trainStop.getStation();
        StationModel stationModel = convertToStationModel(station);
       return new TrainStopModel(trainStop.getArrivalTime(),trainStop.getDepartureTime(),trainStop.getSequence(),stationModel,trainStop.getDistance());


    }

    private List<TrainStopModel> convertToTrainStopModels(List<TrainStop> trainStops) {
        ArrayList<TrainStopModel> trainStopModels = new ArrayList<>();
        for(TrainStop trainStop :trainStops) {
            TrainStopModel trainStopModel = convertToTrainStopModel(trainStop);
            trainStopModels.add(trainStopModel);
        }
        return trainStopModels;


    }
}
