package com.radha.railway.controller;

import com.radha.railway.Station;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
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
        //TODO convrt to List<StationModel>

    }

    public List<StationModel> getToStations(String fromStationCode) throws IOException {
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

    //TODO Convert to List
    public List<TrainModel> getTrains(String fromStationCode,String toStationCode)throws IOException{
       ArrayList<Train> trains =  trainService.getTrains(fromStationCode,toStationCode);
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
        ArrayList<TrainStopModel> trainStopModels = new ArrayList<>();
        for(TrainStop trainStop :trainStops) {
            Station station = trainStop.getStation();
            StationModel stationModel = convertToStationModel(station);
            TrainStopModel trainStopModel = new TrainStopModel(trainStop.getArrivalTime(),trainStop.getDepartureTime(),trainStop.getSequence(),stationModel,trainStop.getDistance());
            trainStopModels.add(trainStopModel);
        }
        return new TrainModel(train.getName(),train.getNumber(),sourceStationModel,destinationStationModel,trainStopModels));

    }

    private TrainStopModel convertToTrainStopModel(TrainStop trainStop) {

    }

    private List<TrainStopModel> convertToTrainStopModel(List<TrainStop> trainStop) {

    }
}
