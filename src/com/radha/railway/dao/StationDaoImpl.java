package com.radha.railway.dao;

import com.radha.railway.Station;

import java.sql.*;

public class StationDaoImpl implements Dao<Station>{
    private PreparedStatement saveStatement;
    private PreparedStatement getStatement;
    private Connection connection;

    public StationDaoImpl() {
        DbConnection dataBaseConnection = new DbConnection();
        connection = dataBaseConnection.getConnection();
        createSaveStatement();
        createGetStatement();
    }

    public int save(Station station){
        /*Steps:
             i)Get the connection from the database
             ii)create a sql statement for insert
             iii)execute the sql statement
             1v)return the primary key

         */
        try {
            saveStatement.setString(1,station.getName());//1=>replacement position of question mark ,station.getName()=>actual station name
            saveStatement.setString(2,station.getCode());
            if(saveStatement.executeUpdate() !=1 ){//check for how many rows is inserted into thw table
                throw new RuntimeException("can not save station "+station.getName());
            }
            ResultSet resultSet = saveStatement.getGeneratedKeys();//returns the resultSet type which consist of primary key of the inserted row
            resultSet.next();// make the ResultSet object to point the next row,initially it point to row before the first row
            return resultSet.getInt(1);//getting the primary key which is in the first column
        } catch (SQLException e) {
            throw new RuntimeException("can not save station "+station.getName(),e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }



    }

    private void createSaveStatement() {
        try {
            saveStatement = connection.prepareStatement("insert into station(station_name,station_code)" +
                    "values(?,?)", Statement.RETURN_GENERATED_KEYS);
        } catch (SQLException e) {
            throw new RuntimeException("Can not create PreparedStatement to create station",e);
        }
    }

    public Station get(int id) {
        /*Steps:
        i)Declare 2 String variable called stationName and stationCode
        ii)execute a sql query for select
        iii)get the station name and store it in the stationName
        iv)get the station code and store it in the stationCode
        iv)return a new Station object with the argument of stationCode and stationName

         */

        try {


            getStatement.setInt(1, id);//set the id for the select query here 1 is the parameterIndex
            ResultSet resultSet = getStatement.executeQuery();
            if(!resultSet.next()){
                return null;
            }
            String stationName = resultSet.getString("station_name");
            String stationCode = resultSet.getString("station_code");
            return new Station(stationCode, stationName);

        } catch (SQLException e) {
            throw new RuntimeException("can not get station with id of " + id, e);
        } finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }
    }

    private  void createGetStatement(){
        try {
            getStatement = connection.prepareStatement("select * from station where station_id=?");
        } catch (SQLException e) {
            throw new RuntimeException("Can not create PreparedStatement to get station",e);
        }

    }
}
