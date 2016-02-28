package com.corbo.musicstreaming.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.corbo.musicstreaming.model.CallResult;
import com.corbo.musicstreaming.model.SearchManager;
import com.corbo.musicstreaming.model.jaxb.MusicList;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(value = "/search")
public class SearchResource {

	@Autowired
	private SearchManager searchManager;

	
	@ApiOperation(value = "/artist/{artist}", nickname = "searchForArtist")
	@RequestMapping(value = "/artist/{artist}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "artist", allowMultiple = false, value = "The artist name to search", required = true, dataType = "string", paramType = "path", defaultValue = "null") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = MusicList.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	public ResponseEntity<MusicList> searchForArtist(@PathVariable("artist") String artist) {
		CallResult<MusicList> callResult = searchManager.searchForTracksByArtist(artist);
		return new ResponseEntity<MusicList>(callResult.getResultObject(), HttpStatus.valueOf(callResult.getReturnCode()));
	}

	
	@ApiImplicitParams({
			@ApiImplicitParam(name = "album", allowMultiple = false, value = "The album name to search", required = true, dataType = "string", paramType = "path", defaultValue = "null") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = MusicList.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(value = "/album/{album}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MusicList> searchForAlbum(@PathVariable("album") String album) {
		return null;
	}

	
	@ApiImplicitParams({
			@ApiImplicitParam(name = "track", allowMultiple = false, value = "The track name to search", required = true, dataType = "string", paramType = "path", defaultValue = "null") })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = MusicList.class),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Forbidden"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Failure") })
	@RequestMapping(value = "/track/{track}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MusicList> searchForTrack(@PathVariable("track") String track) {
		return null;
	}
}
