package de.stoll.nicolas.transport.importer.stops;

import de.stoll.nicolas.transport.data.Stop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface StopMapper {

    @Mapping(target="stopId", source="stopId")
    @Mapping(target = "stopName", source = "stopName")
    @Mapping(target = "stopLat", source = "stopLat")
    @Mapping(target = "stopLon", source = "stopLon")
    @Mapping(target = "locationType", source = "locationType")
    Stop toStop(StopDTO stopDTO);
}
