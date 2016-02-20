package com.corbo.musicstreaming.dao;

import java.util.List;

import com.corbo.musicstreaming.exceptions.AlbumNotFoundException;
import com.corbo.musicstreaming.vo.Album;
import com.corbo.musicstreaming.vo.Artist;

public interface AlbumDAO {
	
	public Album getAlbum(String albumUuid) throws AlbumNotFoundException;
	public List<Album> getAlbumsForArtist(Artist artist);
	public List<Album> getAlbumsByFirstLetter(String firstLetter);
	public List<Album> searchForAlbum(String album);
	public void addAlbum(Album album);
}
