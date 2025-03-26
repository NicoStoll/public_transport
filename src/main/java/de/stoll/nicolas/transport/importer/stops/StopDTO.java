package de.stoll.nicolas.transport.importer.stops;

import lombok.*;
import com.fasterxml.jackson.annotation.JsonProperty;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StopDTO {

    @JsonProperty("stop_id")
    private String stopId;

    @JsonProperty("stop_code")
    private String stopCode;

    @JsonProperty("stop_name")
    private String stopName;

    @JsonProperty("stop_desc")
    private String stopDesc;

    @JsonProperty("stop_lat")
    private Double stopLat;

    @JsonProperty("stop_lon")
    private Double stopLon;

    @JsonProperty("zone_id")
    private String zoneId;

    @JsonProperty("stop_url")
    private String stopUrl;

    @JsonProperty("location_type")
    private String locationType;

    @JsonProperty("parent_station")
    private String parentStation;

    @JsonProperty("platform_code")
    private String platformCode;
}
