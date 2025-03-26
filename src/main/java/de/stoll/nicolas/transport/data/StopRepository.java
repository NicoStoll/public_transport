package de.stoll.nicolas.transport.data;

import org.springframework.data.neo4j.repository.Neo4jRepository;

public interface StopRepository extends Neo4jRepository<Stop, String> {
}
