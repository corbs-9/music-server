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

@RestController
@RequestMapping(value = "/search")
public class SearchResource {

	@Autowired
	private SearchManager searchManager;

	@RequestMapping(value = "/artist/{artist}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MusicList> searchForArtist(@PathVariable("artist") String artist) {
		CallResult<MusicList> callResult = searchManager.searchForArtist(artist);
		return new ResponseEntity<MusicList>(callResult.getResultObject(), HttpStatus.OK);
	}

	@RequestMapping(value = "/album/{album}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MusicList> searchForAlbum(@PathVariable("album") String album) {
		return null;
	}

	@RequestMapping(value = "/track/{track}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MusicList> searchForTrack(@PathVariable("track") String track) {
		return null;
	}
}
