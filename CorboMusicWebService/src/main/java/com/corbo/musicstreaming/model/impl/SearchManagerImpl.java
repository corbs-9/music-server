package com.corbo.musicstreaming.model.impl;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.dao.AlbumDAO;
import com.corbo.musicstreaming.dao.ArtistDAO;
import com.corbo.musicstreaming.dao.TrackDAO;
import com.corbo.musicstreaming.model.CallResult;
import com.corbo.musicstreaming.model.SearchManager;
import com.corbo.musicstreaming.model.jaxb.MusicList;

@Controller
public class SearchManagerImpl implements SearchManager {
	
	@Autowired
	private AlbumDAO albumDAO;
	@Autowired 
	private ArtistDAO artistDAO;
	@Autowired
	private TrackDAO trackDAO;

	public CallResult<MusicList> searchForArtist(String artist) {
		artist = WordUtils.capitalize(artist.toLowerCase());
		MusicList musicList = new MusicList();
		CallResult<MusicList> callResult = new CallResult<MusicList>(200, musicList);
		return callResult;
	}

}
