package com.corbo.musicstreaming.model;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;

public interface MusicManager {
	
	public ResponseEntity<ByteArrayResource> buildStream(final String trackId);

}
