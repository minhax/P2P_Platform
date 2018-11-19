package com.lo23.data.client;

import com.lo23.common.interfaces.data.DataClientToIhm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires du DataManagerClient.
 */
class DataManagerClientTest
{
    private DataManagerClient dm;

    /**
     * On initialise un DataManagerClient de test avant chaque test.
     */
    @BeforeEach
    void createDataManagerClient ()
    {
        this.dm = DataManagerClient.getInstance();
    }

    /**
     * On teste si une erreur est levée lors de la création de compte.
     */
    @Test
    void shouldCreateAccount ()
    {
        DataClientToIhm api = dm.getDataClientToIhmApi();
        assertDoesNotThrow(() ->
                api.createAccount("test", "test", "PrénomTest", "NomTest", 99)
        );
        // TODO: virer le fichier créé sur le disque
    }
}