package com.radha.railway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class AbstractDao<T> implements Dao<T> {

    protected Connection connection;

    public AbstractDao() {
        DbConnection dataBaseConnection = new DbConnection();
        connection = dataBaseConnection.getConnection();
    }

    public int save(T t){

        /*Steps:
             i)Get the connection from the database
             ii)create a sql statement for insert
             iii)execute the sql statement
             1v)return the primary key

         */

        try {
            PreparedStatement saveStatement = getSaveStatement(t);
            if(saveStatement.executeUpdate() !=1 ){//check for how many rows is inserted into thw table
                throw new RuntimeException("can not save object ");
            }
            ResultSet resultSet = saveStatement.getGeneratedKeys();//returns the resultSet type which consist of primary key of the inserted row
            resultSet.next();// make the ResultSet object to point the next row,initially it point to row before the first row
            return resultSet.getInt(1);//getting the primary key which is in the first column
        } catch (SQLException e) {
            throw new RuntimeException("can not save object",e);
        }
        finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public abstract PreparedStatement getSaveStatement(T t) throws SQLException;

    public Connection getConnection() {
        return connection;
    }
}
