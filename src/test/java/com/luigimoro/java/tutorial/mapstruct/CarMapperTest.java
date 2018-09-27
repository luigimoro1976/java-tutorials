package com.luigimoro.java.tutorial.mapstruct;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarMapperTest {

    @Test
    public void testCarMapper_01() {

        Car car = new Car();
        car.setMake("Fiat");
        car.setNumberOfSeats("4");
        car.setType(Car.CarType.A);

        CarDto carDto = CarMapper.MAPPER.carToCarDto(car);

        assertEquals("Fiat",carDto.getMake());
        assertEquals(4, carDto.getSeatCount());
        assertEquals("A", carDto.getType());


    }

}