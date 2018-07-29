package com.radha.railway.service;

import com.radha.railway.Station;
import com.radha.railway.TimeTable;
import com.radha.railway.Train;
import com.radha.railway.TrainStop;
import com.radha.railway.dao.DaoFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class TrainService {
    public ArrayList<Train> getTrains(String fromStationCode,String toStationCode) throws IOException{
        TimeTable timeTable = DaoFactory.getTimeTableDao().getTimeTable();
        return getTrains(fromStationCode,toStationCode, timeTable);

    }

    ArrayList<Train> getTrains(String fromStationCode,String toStationCode, TimeTable timeTable) {
       /* step 0:Declare a List as resultsList.
                Step 1: Get the fromStation object from the from station code using the stations map.
                Step 2:Get the list of trainstops(fromStationStops) object from the fromstation object.
                Step3:Get the toStation object from the  tostation code using the stations map.
        Step 4:Get the list of trainstops(toStationStops) object from the tostation object.
                step5:For each fromStationStop in fromStationStops do
            a)For each toStationsStop in the toStationsStops do
            i)if the fromsStationStop's train is equal toStationStop's train and fromsStationStop's sequenceNo is less than toStationStop's sequenceNo   then
        ii)Get the corresponding Train from the trainStopingAtFromStation  and add it to the resultsList.*/

       ArrayList<Train> resultTrains = new ArrayList<>();
        Map<String, Station> stations = timeTable.getStations();
        Station fromStation = stations.get(fromStationCode);
        ArrayList<TrainStop> fromStationStops = fromStation.getTrainStops();
        Station toStation = stations.get(toStationCode);
        ArrayList<TrainStop> toStationStops = toStation.getTrainStops();
        for(TrainStop fromStationStop : fromStationStops){
            for(TrainStop toStationStop : toStationStops){
                if(fromStationStop.isBeforeStop(toStationStop)){
                    resultTrains.add(fromStationStop.getTrain());
                }
            }
        }
         return resultTrains;
    }
}
