package com.railway.route_management_service.persistence;

import java.util.List;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.railway.route_management_service.domain.Station;

@Repository
public interface StationRepository extends Neo4jRepository<Station, Long> {
	List<Station> findByName(@Param("name") String name);
	
	// creating a new station node with the merge clause, this will ensure that it is created only if it doesn't already exists (idempotent query)
	@Query("MERGE (n:Station {name : {name}}) RETURN n")
	Station createStation(String name);
}
