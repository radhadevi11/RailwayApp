package com.radha.railway.controller;

import com.radha.railway.Station;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import com.radha.railway.service.StationService;
import com.radha.railway.service.TrainService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainController {
    private static StationService stationService = new StationService();
    private static TrainService trainService = new TrainService();

    public List<StationModel> getFromStations() throws IOException {
       ArrayList<StationModel> fromStationModels = new ArrayList<>();
        Map<String, Station> fromStations = stationService.getFromStations();
        for(Station station : fromStations.values()){
            fromStationModels.add(new StationModel(station.getName(),station.getCode(),station.getLatLng()));

        }
        return fromStationModels;
        //TODO convrt to List<StationModel>

    }

    public List<StationModel> getToStations(String fromStationCode) throws IOException {
        Map<String, Station> toStation =stationService.getToStations(fromStationCode);
        ArrayList<StationModel> toStationModels = new ArrayList<>();
        for(Station station : toStation.values()){
           toStationModels.add(new StationModel(station.getName(),station.getCode(),station.getLatLng()));

        }
        return toStationModels;
    }
    //TODO Convert to List
    public List<TrainModel> getTrains(String fromStationCode,String toStationCode)throws IOException{
       ArrayList<Train> trains =  trainService.getTrains(fromStationCode,toStationCode);
       ArrayList<TrainModel> trainModels = new ArrayList<>();
       for(Train train : trains){
          Station sourceStation = train.getSourceStation();
          StationModel sourceStationModel = new StationModel(sourceStation.getName(),sourceStation.getCode(),sourceStation.getLatLng());
          Station destinationStation = train.getDestinationStation();
          StationModel destinationStationModel = new StationModel(destinationStation.getName(),destinationStation.getCode(),destinationStation.getLatLng());
          ArrayList<TrainStop> trainStops = train.getTrainStops();
          ArrayList<TrainStopModel> trainStopModels = new ArrayList<>();
          for(TrainStop trainStop :trainStops) {
              Station station = trainStop.getStation();
              StationModel stationModel = new StationModel(station.getName(),station.getCode(),station.getLatLng());
              TrainStopModel trainStopModel = new TrainStopModel(trainStop.getArrivalTime(),trainStop.getDepartureTime(),trainStop.getSequence(),stationModel,trainStop.getDistance());
              trainStopModels.add(trainStopModel);
          }
          trainModels.add(new TrainModel(train.getName(),train.getNumber(),sourceStationModel,destinationStationModel,trainStopModels));

       }
       return trainModels;
    }
}
