package de.stoll.nicolas.transport.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.time.LocalDateTime;

@Node("Departure")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Departure {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String routeId;

    private String vehicleType;

    @Setter
    @Relationship(type = "ARRIVES_AT", direction = Relationship.Direction.OUTGOING)
    private Stop nextStop;

    @Setter
    @Relationship(type = "NEXT_STOP", direction = Relationship.Direction.INCOMING)
    private Departure nextDeparture;

    public Departure(Long id, LocalDateTime departureTime, LocalDateTime arrivalTime, String routeId, String vehicleType) {
        this.id = id;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.routeId = routeId;
        this.vehicleType = vehicleType;
    }
}
