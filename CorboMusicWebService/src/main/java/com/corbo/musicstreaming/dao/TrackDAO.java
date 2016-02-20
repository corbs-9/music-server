package com.corbo.musicstreaming.dao;

import java.util.List;

import com.corbo.musicstreaming.vo.Album;
import com.corbo.musicstreaming.vo.Artist;
import com.corbo.musicstreaming.vo.Track;

public interface TrackDAO {
	
	public Track getTrack(String trackUuid);
	public List<Track> getTracksForAlbum(Album album);
	public List<Track> getTracksForArtist(Artist artist);
	public List<Track> searchForTrack(String track);
	public void addTrack(Track track);
	public void updateTrack(Track track);
	public void deleteTrack(Track track);
}
