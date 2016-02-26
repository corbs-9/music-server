package com.corbo.musicstreaming.model;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

public interface MusicManager {

	public ResponseEntity<StreamingResponseBody> buildStream(final String trackId, final String range);

}
