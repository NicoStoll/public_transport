package de.stoll.nicolas.transport.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;

@Node("Agency")
@AllArgsConstructor
@Getter
public class Agency {

    @Id
    private final String agencyId;

    private final String agencyName;

    private final String agencyUrl;

    private final String agencyTimezone;

    private final String agencyLang;

    private final String agencyPhone;
}
