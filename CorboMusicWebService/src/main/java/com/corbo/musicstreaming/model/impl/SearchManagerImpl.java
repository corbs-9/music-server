package com.corbo.musicstreaming.model.impl;

import java.util.Collection;

import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.corbo.musicstreaming.entity.Artist;
import com.corbo.musicstreaming.model.CallResult;
import com.corbo.musicstreaming.model.SearchManager;
import com.corbo.musicstreaming.model.jaxb.ArtistCplxType;
import com.corbo.musicstreaming.model.jaxb.ArtistList;
import com.corbo.musicstreaming.model.jaxb.MusicList;
import com.corbo.musicstreaming.repository.ArtistRepository;

@Controller
public class SearchManagerImpl implements SearchManager {
	
	@Autowired
	private ArtistRepository artistRepository;

	public CallResult<MusicList> searchForArtist(String artist) {
		Collection<Artist> artistCollection = artistRepository.findByNameIgnoreCase(artist);
		if (artistCollection != null) {
			System.out.println("artist iterable is not null");
			if (artistCollection.isEmpty()) {
				System.out.println("Empty collection :(");
			}
			artistCollection.forEach(artistEntry -> System.out.println(artistEntry.toString()));
		} else {
			System.out.println("artist iterable is null");
		}
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
