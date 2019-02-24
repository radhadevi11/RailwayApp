package com.radha.railway.dao;

import com.radha.railway.Station;
import com.radha.railway.Train;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TrainDaoImpl extends AbstractDao<Train> {

    private PreparedStatement saveStatement;
    private PreparedStatement getAllStatement;

    public TrainDaoImpl(){
       createSaveStatement();
       createGetAllStatement();
    }

    private void createGetAllStatement() {
        try {
            getAllStatement = connection.prepareStatement("select * from train");
        } catch (SQLException e) {
            throw new RuntimeException("Can not create PreparedStatement to get trains", e);
        }
    }

    private void createSaveStatement() {
        try {
            saveStatement = connection.prepareStatement("insert into train(train_number," +
                    "train_name," +
                    "source_station_id," +
                    "destination_station_id)" +
                    "values(?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException("Can not create PreparedStatement to create Train",e);
        }
    }


    @Override
    public PreparedStatement getSaveStatement(Train train) throws SQLException {
        saveStatement.setString(1,train.getNumber());
        saveStatement.setString(2,train.getName());
        saveStatement.setInt(3,train.getSourceStation().getId());
        saveStatement.setInt(4,train.getDestinationStation().getId());

        return saveStatement;
    }

    @Override
    public Train get(int key) {
        return null;
    }

    @Override
    public PreparedStatement getGetAllStatement() {
        return getAllStatement;
    }

    @Override
    public Train getEntity(ResultSet resultSet) throws SQLException {
        String trainNumber = resultSet.getString("train_number");
        String trainName = resultSet.getString("train_name");
        Integer sourceStationId = resultSet.getInt("source_station_id");
        Integer destinationStationId = resultSet.getInt("destination_station_id");
        return new Train(trainName,
                        trainNumber,
                        new Station(sourceStationId),
                        new Station(destinationStationId));

    }
}
