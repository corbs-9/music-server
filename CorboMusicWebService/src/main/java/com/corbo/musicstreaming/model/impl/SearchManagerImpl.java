package com.corbo.musicstreaming.model.impl;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.dao.AlbumDAO;
import com.corbo.musicstreaming.dao.ArtistDAO;
import com.corbo.musicstreaming.dao.TrackDAO;
import com.corbo.musicstreaming.exceptions.ArtistNotFoundException;
import com.corbo.musicstreaming.model.CallResult;
import com.corbo.musicstreaming.model.SearchManager;
import com.corbo.musicstreaming.model.jaxb.ArtistCplxType;
import com.corbo.musicstreaming.model.jaxb.ArtistList;
import com.corbo.musicstreaming.model.jaxb.MusicList;
import com.corbo.musicstreaming.vo.Artist;

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
		try {
			artistDAO.searchForArtist(artist);
		} catch (ArtistNotFoundException e) {
			System.err.println("test");
			e.printStackTrace();
		}
		ArtistList artistList = new ArtistList();
		ArtistCplxType artistCplx = new ArtistCplxType();
		artistCplx.setArtistName("Drake");
		artistCplx.setArtistUuid("1");
		MusicList musicList = new MusicList();
		artistList.getArtist().add(artistCplx);
		musicList.setArtistList(artistList);
		CallResult<MusicList> callResult = new CallResult<MusicList>(200, musicList);
		return callResult;
	}

}
