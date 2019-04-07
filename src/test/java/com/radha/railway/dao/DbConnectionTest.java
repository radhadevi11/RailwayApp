package com.radha.railway.dao;

import org.junit.Test;


import java.sql.Connection;

import static org.junit.Assert.assertNotNull;


public class DbConnectionTest {
    @Test
    public void testGetConnection(){
        DbConnection connection = new DbConnection();

        Connection actual = connection.getConnection();

        assertNotNull(actual);
    }

}