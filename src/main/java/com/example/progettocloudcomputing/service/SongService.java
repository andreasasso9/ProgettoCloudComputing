package com.example.progettocloudcomputing.service;

import com.example.progettocloudcomputing.entity.Song;
import com.example.progettocloudcomputing.repository.SongRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SongService {
	private final SongRepository songRepository;

	public boolean save(Song song) {
		songRepository.save(song);
		return true;
	}
}
