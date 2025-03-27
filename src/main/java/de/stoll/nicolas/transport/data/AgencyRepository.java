package de.stoll.nicolas.transport.data;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends Neo4jRepository<Agency, String> {
}
