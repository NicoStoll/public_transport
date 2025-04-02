package de.stoll.nicolas.transport.data;

import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node("Trip")
@Data
@ToString
public class Trip {

    @Id
    private String tripId;

    private String tripHeadSign;

    private String tripShortName;

    private Direction directionId;

    private int blockId;

    private int shapeId;

    @Setter
    @Relationship(type = "SERVICES", direction = Relationship.Direction.OUTGOING)
    private Route route;

    @Setter
    @Relationship(type = "HAS_SCHEDULE", direction = Relationship.Direction.OUTGOING)
    private Schedule schedule;

    public Trip(String tripHeadSign, String tripShortName, Direction direction, int blockId, int shapeId) {
        this.tripHeadSign = tripHeadSign;
        this.tripShortName = tripShortName;
        this.directionId = direction;
        this.blockId = blockId;
        this.shapeId = shapeId;
    }
}
