package com.example.progettocloudcomputing.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.example.progettocloudcomputing.entity.Song;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends CosmosRepository<Song, String> {
	@Query("SELECT * FROM s WHERE @name!='' AND  (STARTSWITH(LOWER(s.name), LOWER(@name)) OR CONTAINS(LOWER(s.singer), LOWER(@name)) OR CONTAINS(LOWER(s.name), LOWER(@name)))")
	List<Song> findByName(@Param("name") String name);
}
