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

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

	@ApiImplicitParams({
			@ApiImplicitParam(name = "trackid", allowMultiple = false, value = "The Id of the track", required = true, dataType = "string", paramType = "path"),
			@ApiImplicitParam(name = "Range", allowMultiple = false, value = "The range of the requested track. If not passed in, the whole song will be streamed in memory.", required = false, dataType = "string", paramType = "header", defaultValue = "bytes=0-") })
	@ApiResponses(value = {
			@ApiResponse(code = 206, message = "Partial-Content", response = StreamingResponseBody.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(value = "/stream/track/{trackid}", method = RequestMethod.GET, produces = "audio/mpeg")
	public ResponseEntity<StreamingResponseBody> streamAudio(
			@RequestHeader(value = "Range", required = false) String range,
			@PathVariable(value = "trackid") String trackId) {
		return musicManager.buildStream(trackId, range);
	}

}