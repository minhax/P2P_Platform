package com.lo23.data.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerInfosTests
{
    @Test
    void shouldGetSserverName(){
        ServerInfos si = new ServerInfos("name");
        assertEquals("name", si.getName());
    }
}
