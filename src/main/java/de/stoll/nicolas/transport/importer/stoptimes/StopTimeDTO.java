package de.stoll.nicolas.transport.importer.stoptimes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StopTimeDTO {

    @JsonProperty("trip_id")
    private String tripId;

    @JsonProperty("arrival_time")
    private String arrivalTime;

    @JsonProperty("departure_time")
    private String departureTime;

    @JsonProperty("stop_id")
    private String stopId;

    @JsonProperty("stop_sequence")
    private int stopSequence;

    @JsonProperty("stop_headsign")
    private String stopHeadsign;

    @JsonProperty("pickup_type")
    private int pickupType;

    @JsonProperty("drop_off_type")
    private int dropOffType;

    @JsonProperty("shape_dist_traveled")
    private double shapeDistTraveled;

}
