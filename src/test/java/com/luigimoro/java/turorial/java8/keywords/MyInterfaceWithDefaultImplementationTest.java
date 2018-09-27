package com.luigimoro.java.turorial.java8.keywords;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MyInterfaceWithDefaultImplementationTest {

    @Test
    public void testMyDefaultMethodImplementation() {

        MyInterfaceWithDefault myInterfaceWithDefault = new MyInterfaceWithDefaultImplementation();

        assertEquals(myInterfaceWithDefault.getName("234332"), MyInterfaceWithDefault.DEFAULT_NAME);


    }

}