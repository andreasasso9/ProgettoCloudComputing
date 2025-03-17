package com.example.progettocloudcomputing.entity;

import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.net.URL;

@Data
@NoArgsConstructor
public class Song implements Serializable {
	@Id @GeneratedValue
	private String id;
	private String name;
	private String singer;
	private URL songUrl;
}
