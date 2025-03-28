package de.stoll.nicolas.transport.importer.trips;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TripDTO {

    @JsonProperty("route_id")
    private String routeId;

    @JsonProperty("service_id")
    private String serviceId;

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("trip_headsign")
    private String tripHeadSign;

    @JsonProperty("trip_short_name")
    private String tripShortName;

    @JsonProperty("direction_id")
    private String directionId;

    @JsonProperty("block_id")
    private String blockId;

    @JsonProperty("shape_id")
    private String shapeId;

    @JsonProperty("trip_type")
    private String tripType;


}
