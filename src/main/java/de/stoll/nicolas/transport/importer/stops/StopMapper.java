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
    @Mapping(target = "locationType", expression = "java(parseInteger(stopDTO.getLocationType()))")
    Stop toStop(StopDTO stopDTO);

    default Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;  // Default value, or you could use `null` if needed
        }
        return Integer.parseInt(value);
    }
}
