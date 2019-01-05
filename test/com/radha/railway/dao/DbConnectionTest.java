package com.radha.railway.dao;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DbConnectionTest {
    @Test
    public void testGetConnection(){
        DbConnection connection = new DbConnection();

        Connection actual = connection.getConnection();

        assertNotNull(actual);
    }

}