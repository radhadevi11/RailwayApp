package com.radha.railway.dao;

public class DaoFactory {
    private static TimeTableDao timeTableDao = new TimeTableDao();

    public static TimeTableDao getTimeTableDao() {
        return timeTableDao;
    }
}
