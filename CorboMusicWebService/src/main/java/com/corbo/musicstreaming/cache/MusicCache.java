package com.corbo.musicstreaming.cache;

import java.util.ArrayList;
import java.util.List;

import com.corbo.musicstreaming.vo.Album;
import com.corbo.musicstreaming.vo.Artist;
import com.corbo.musicstreaming.vo.Track;

public class MusicCache {
	
	private MusicCache() {
	}
	
	public static MusicCache getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MusicCache();
		}
		return INSTANCE;
	}
	
	private static MusicCache INSTANCE;
	
	private List<Artist> artistList;
	private List<Album> albumList;
	private List<Track> trackList;
	private int currentArtistId = 0;
	private int currentAlbumId = 0;
	private int currentTrackId = 0;
	
	public List<Artist> getArtistList() {
		if (artistList == null) {
			artistList = new ArrayList<Artist>();
		}
		return artistList;
	}
	
	public List<Album> getAlbumList() {
		if (albumList == null) {
			albumList = new ArrayList<Album>();
		}
		return albumList;
	}
	
	public List<Track> getTrackList() {
		if (trackList == null) {
			trackList = new ArrayList<Track>();
		}
		return trackList;
	}
	
	public int getNextArtistId() {
		currentArtistId++;
		return currentArtistId;
	}
	
	public int getNextAlbumId() {
		currentAlbumId++;
		return currentAlbumId;
	}
	
	public int getNextTrackId() {
		currentTrackId++;
		return currentTrackId;
	}

}
