package de.stoll.nicolas.transport.data;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface AgencyRepository extends Neo4jRepository<Agency, String> {
}
