package de.stoll.nicolas.transport.importer.routes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RouteDTO {

    @JsonProperty("route_id")
    private String routeId;

    @JsonProperty("agency_id")
    private String agencyId;

    @JsonProperty("route_short_name")
    private String routeShortName;

    @JsonProperty("route_long_name")
    private String routeLongName;

    @JsonProperty("route_desc")
    private String routeDesc;

    @JsonProperty("route_type")
    private int routeType;

    @JsonProperty("route_url")
    private String routeUrl;

    @JsonProperty("route_color")
    private String routeColor;

    @JsonProperty("route_text_color")
    private String routeTextColor;
}
