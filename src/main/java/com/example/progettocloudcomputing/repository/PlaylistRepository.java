package com.example.progettocloudcomputing.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.example.progettocloudcomputing.entity.Playlist;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlaylistRepository extends CosmosRepository<Playlist, String> {
	List<Playlist> findByEmailProprietario(String emailProprietario);
}
