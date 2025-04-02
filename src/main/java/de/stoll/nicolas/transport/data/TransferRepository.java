package de.stoll.nicolas.transport.data;

import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends Neo4jRepository<Transfer, Long> {
}
