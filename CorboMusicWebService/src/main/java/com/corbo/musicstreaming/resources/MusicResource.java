package com.corbo.musicstreaming.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.corbo.musicstreaming.model.MusicManager;

/**
 * Streaming resource
 *
 */
@RestController
@RequestMapping(value = "/music")
public class MusicResource {

	@Autowired
	MusicManager musicManager;

	@RequestMapping(value = "/stream/track/{trackid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<ByteArrayResource> streamAudio(@PathVariable("trackid") String trackId) {
		return musicManager.buildStream(trackId);
	}

}