package de.stoll.nicolas.transport.data;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Relationship;

@Node
@NoArgsConstructor
@Data
public class Transfer {

    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Relationship(type = "FROM_STOP", direction = Relationship.Direction.INCOMING)
    private Stop fromStop;  // from_stop_id from transfers.csv

    @Setter
    @Relationship(type = "TO_STOP", direction = Relationship.Direction.OUTGOING)
    private Stop toStop;  // to_stop_id from transfers.csv

    private int transferType;  // transfer_type from transfers.csv
    private int minTransferTime;  // min_transfer_time from transfers.csv

    public Transfer( int transferType, int minTransferTime) {
        this.transferType = transferType;
        this.minTransferTime = minTransferTime;
    }

}
