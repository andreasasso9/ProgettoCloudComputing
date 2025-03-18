package com.example.progettocloudcomputing.service;

import com.example.progettocloudcomputing.entity.Playlist;
import com.example.progettocloudcomputing.repository.PlaylistRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PlaylistService {
	private final PlaylistRepository playlistRepository;

	public boolean save(Playlist playlist) {
		playlistRepository.save(playlist);
		return true;
	}

	public List<Playlist> getByEmail(String email) {
		return playlistRepository.findByEmailProprietario(email);
	}
}
