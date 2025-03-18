package com.example.progettocloudcomputing.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.progettocloudcomputing.entity.Song;
import org.springframework.stereotype.Repository;

@Repository
public interface SongRepository extends CosmosRepository<Song, String> {
}
