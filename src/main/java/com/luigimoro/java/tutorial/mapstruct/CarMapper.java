package com.luigimoro.java.tutorial.mapstruct;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CarMapper {

    CarMapper MAPPER = Mappers.getMapper(CarMapper.class );

    @Mappings({
            @Mapping(source = "numberOfSeats", target = "seatCount"),
            @Mapping(source = "type", target = "type")
    })
    CarDto carToCarDto(Car car);
}
