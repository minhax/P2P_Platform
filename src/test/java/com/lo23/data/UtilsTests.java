package com.lo23.data;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilsTests
{
    @Test
    void shouldThrowNullPointerException(){
        Assertions.assertThrows(NullPointerException.class, () -> {Utils.throwExceptionIfNull("null", null);});
    }
}
