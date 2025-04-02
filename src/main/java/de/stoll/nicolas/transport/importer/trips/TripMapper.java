package de.stoll.nicolas.transport.importer.trips;

import de.stoll.nicolas.transport.data.Direction;
import de.stoll.nicolas.transport.data.ExceptionType;
import de.stoll.nicolas.transport.data.Trip;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TripMapper {

    @Mapping(target = "tripId", source = "tripId")
    @Mapping(target="tripHeadSign", source="tripHeadSign")
    @Mapping(target="tripShortName", source="tripShortName")
    @Mapping(target = "directionId", expression = "java(mapDirection(tripDTO.getDirectionId()))")
    @Mapping(target="blockId", expression = "java(parseInteger(tripDTO.getBlockId()))")
    @Mapping(target="shapeId", expression = "java(parseInteger(tripDTO.getShapeId()))")
    Trip toTrip(TripDTO tripDTO);

    default Integer parseInteger(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0;  // Default value, or you could use `null` if needed
        }
        return Integer.parseInt(value);
    }

    default Direction mapDirection(String directionId) {

        int exceptionTypeNum = Integer.parseInt(directionId);

        return switch (exceptionTypeNum) {
            case 1 -> Direction.FORWARD;
            case 2 -> Direction.BACKWARD;
            default -> Direction.UNKNOWN;
        };


    }
}
