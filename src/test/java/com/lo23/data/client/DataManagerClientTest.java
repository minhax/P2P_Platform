package com.lo23.data.client;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DataManagerClientTest
{
    private DataManagerClient dm;

    /**
     * On initialise un FileHandler de test avant chaque test.
     */
    @BeforeEach
    void createDataManagerClient ()
    {
        this.dm = new DataManagerClient();
    }

    @Test
    void shouldCreateAccount ()
    {
        DataClientToIhmApi api = dm.getDataClientToIhmApi();
        api.createAccount("test", "test", "PrénomTest", "NomTest", 99);
    }
}