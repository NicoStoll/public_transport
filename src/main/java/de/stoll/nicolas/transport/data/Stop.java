package de.stoll.nicolas.transport.data;


import lombok.Getter;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Stop")
@Getter
public class Stop {

    @Id
    private final String stopId;

    private final String stopName;

    private final double stopLat;
    private final double stopLon;

    private final int locationType;

    @Setter
    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.OUTGOING)
    private Stop parentStop;

    public Stop(String stopId, String stopName, double stopLat, double stopLon, int locationType) {
        this.stopId = stopId;
        this.stopName = stopName;
        this.stopLat = stopLat;
        this.stopLon = stopLon;
        this.locationType = locationType;
    }
}
