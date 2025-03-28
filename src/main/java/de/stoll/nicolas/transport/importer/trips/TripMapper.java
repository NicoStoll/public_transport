package de.stoll.nicolas.transport.importer.trips;

import de.stoll.nicolas.transport.data.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(target = "tripId", source = "tripId")
    @Mapping(target="tripHeadSign", source="tripHeadSign")
    @Mapping(target="tripShortName", source="tripShortName")
    @Mapping(target = "directionId", expression = "java(parseInteger(tripDTO.getDirectionId()))")
    @Mapping(target="blockId", expression = "java(parseInteger(tripDTO.getBlockId()))")
    @Mapping(target="shapeId", expression = "java(parseInteger(tripDTO.getShapeId()))")
    Trip toTrip(TripDTO tripDTO);

    default Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;  // Default value, or you could use `null` if needed
        }
        return Integer.parseInt(value);
    }
}
