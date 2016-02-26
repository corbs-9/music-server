package com.corbo.musicstreaming.model.impl;

import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.model.CallResult;
import com.corbo.musicstreaming.model.SearchManager;
import com.corbo.musicstreaming.model.jaxb.ArtistCplxType;
import com.corbo.musicstreaming.model.jaxb.ArtistList;
import com.corbo.musicstreaming.model.jaxb.MusicList;

@Controller
public class SearchManagerImpl implements SearchManager {

	public CallResult<MusicList> searchForArtist(String artist) {
		artist = WordUtils.capitalize(artist.toLowerCase());
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
