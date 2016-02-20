package com.corbo.musicstreaming.dao;

import com.corbo.musicstreaming.exceptions.AddArtistException;
import com.corbo.musicstreaming.exceptions.ArtistNotFoundException;
import com.corbo.musicstreaming.vo.Artist;
import java.util.List;

public interface ArtistDAO {
	
	public Artist getArtist(String artistUuid) throws ArtistNotFoundException;
	public List<Artist> getAllArtists() throws ArtistNotFoundException;
	public List<Artist> getArtistsByFirstVarChar(String firstLetter) throws ArtistNotFoundException;
	public Artist searchForArtist(String artist) throws ArtistNotFoundException;
	public void addArtist(Artist artist) throws AddArtistException;
}
