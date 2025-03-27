package de.stoll.nicolas.transport.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;
import org.springframework.data.neo4j.core.support.UUIDStringGenerator;

import java.time.LocalDate;

@Node("ScheduleException")
@Getter
@ToString
public class ScheduleException {

    @Id
    @GeneratedValue(UUIDStringGenerator.class)
    private String id;

    private final String serviceId;
    private final LocalDate date;
    private final ExceptionType exceptionType;

    @Relationship(type = "BELONGS_TO", direction = Relationship.Direction.INCOMING)
    @Setter
    private Schedule schedule;

    public ScheduleException(String serviceId, LocalDate date, ExceptionType exceptionType) {
        this.serviceId = serviceId;
        this.date = date;
        this.exceptionType = exceptionType;
    }

}

