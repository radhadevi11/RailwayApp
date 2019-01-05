package com.radha.railway.dao;

import com.radha.railway.Station;

import java.sql.*;

public class StationDaoImpl implements Dao<Station>{
    private PreparedStatement saveStatement;
    private Connection connection;

    public StationDaoImpl() {
        DbConnection dataBaseConnection = new DbConnection();
        connection = dataBaseConnection.getConnection();
        createSaveStatement();
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
}
