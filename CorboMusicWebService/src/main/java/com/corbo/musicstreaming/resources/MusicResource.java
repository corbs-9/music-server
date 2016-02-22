package com.corbo.musicstreaming.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.corbo.musicstreaming.database.Tasks;
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
	
	@Autowired
	Tasks tasks;
	
	@RequestMapping(value = "/stream/track/{trackid}", method = RequestMethod.GET, produces = "audio/mpeg")
	public ResponseEntity<StreamingResponseBody> streamAudio(@RequestHeader(value="Range", required=false) String range, @PathVariable(value="trackid") String trackId) {
		return musicManager.buildStream(trackId, range);
	}
	
	@RequestMapping(value = "/test")
	public ResponseEntity<Void> test() {
		tasks.synchroniseDataStore();
		return ResponseEntity.notFound().build();
	}

}