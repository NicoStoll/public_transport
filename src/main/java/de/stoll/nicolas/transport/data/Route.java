package de.stoll.nicolas.transport.data;

import lombok.*;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Route")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Route {

    @Id
    private String routeId;

    private String routeShortName;

    private String routeLongName;

    private String routeDesc;

    private int routeType;

    private String routeUrl;

    private String routeColor;

    private String routeTextColor;

    @Relationship(type = "OPERATED_BY", direction = Relationship.Direction.OUTGOING)
    @Setter
    private Agency agency;

    public Route(String routeId, String routeShortName, String routeLongName, String routeDesc, int routeType, String routeUrl, String routeColor, String routeTextColor) {
        this.routeId = routeId;
        this.routeShortName = routeShortName;
        this.routeLongName = routeLongName;
        this.routeDesc = routeDesc;
        this.routeType = routeType;
        this.routeUrl = routeUrl;
        this.routeColor = routeColor;
        this.routeTextColor = routeTextColor;
    }
}
