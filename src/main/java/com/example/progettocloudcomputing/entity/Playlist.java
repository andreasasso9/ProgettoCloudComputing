package com.example.progettocloudcomputing.entity;

import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class Playlist implements Serializable {
	@Id @GeneratedValue
	private String id;
	private List<Song> songs;
	private String name;
	private String emailProprietario;
}
