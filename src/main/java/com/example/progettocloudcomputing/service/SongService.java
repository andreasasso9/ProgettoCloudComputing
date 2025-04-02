package com.example.progettocloudcomputing.service;

import com.example.progettocloudcomputing.entity.Song;
import com.example.progettocloudcomputing.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SongService {
	private final SongRepository songRepository;

	public boolean save(Song song) {
		songRepository.save(song);
		return true;
	}

	public List<Song> getByName(String name) {
		return songRepository.findByName(name);
	}

	public Song getById(String songId) {
		return songRepository.findById(songId).orElse(null);
	}
}
