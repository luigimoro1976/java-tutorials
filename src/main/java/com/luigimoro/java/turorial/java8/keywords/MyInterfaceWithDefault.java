package com.luigimoro.java.turorial.java8.keywords;

public interface MyInterfaceWithDefault {

    String DEFAULT_NAME = "Unassigned Name";

    default String getName(String name) {
        return DEFAULT_NAME;
    }
}
