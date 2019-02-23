package com.radha.railway.dao;

import com.radha.railway.Train;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class TrainDaoImpl extends AbstractDao<Train> {


    @Override
    public PreparedStatement getSaveStatement(Train train) throws SQLException {
        return null;
    }

    @Override
    public Train get(int key) {
        return null;
    }

    @Override
    public List<Train> getAll() {
        return null;
    }
}
