package com.corbo.musicstreaming.model;

import com.corbo.musicstreaming.model.jaxb.MusicList;

public interface SearchManager {

	CallResult<MusicList> searchForTracksByArtist(String artist);
	
	CallResult<MusicList> getArtistsByFirstLetter(String firstLetter);

}
